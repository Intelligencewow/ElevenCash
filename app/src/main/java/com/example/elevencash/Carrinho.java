    package com.example.elevencash;

    import android.util.Log;

    import com.example.elevencash.compraTable.Compra;
    import com.example.elevencash.itemCompraTable.ItemCompra;
    import com.example.elevencash.productTable.Product;

    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
    import java.util.UUID;

    public class Carrinho{
        public static List<CarrinhoItem> carrinhoItems = new ArrayList<>();
        public static double totalValue = 0;
        public static int totalQuantity = 0;
        private static Carrinho INSTANCE;
        private final List<CarrinhoListener> listeners = new ArrayList<>();

        public Carrinho(){

        }

        public static synchronized Carrinho getINSTANCE(){
            if (INSTANCE == null){
                INSTANCE = new Carrinho();
            }

            return INSTANCE;
        }

        public static int getTotalQuantity() {
            return totalQuantity;
        }

        public static int getTotalValueCents(){
            Log.i("MyActivity", "getTotalValueCents: ");
            return (int) (getTotalValue() * 100);
        }

        public static double getTotalValue() {
            return totalValue;
        }

        public void addListener(CarrinhoListener listener) {
            if (!listeners.contains(listener)) {
                listeners.add(listener);
            }
        }

        public void removeListener(CarrinhoListener listener) {
            listeners.remove(listener);
        }

        private void notifyListenerstoQuantityChanged() {
            for (CarrinhoListener listener : listeners) {
                listener.onTotalQuantityChanged(totalQuantity, totalValue);
            }
        }

        private void notifyListenerstoClearCarrinho() {
            for (CarrinhoListener listener : listeners) {
                listener.onClearCarrinho();
            }
        }

        public void addProduct(Product product, int quantity){
            boolean found = false;
            for (CarrinhoItem item : carrinhoItems){
                if (item.getProduct().getId() == product.getId()){
                    item.increaseQuantity(quantity);
                    found = true;
                    break;
                }
            }

            if (!found){
                carrinhoItems.add(new CarrinhoItem(product));
            }

            totalQuantity += quantity;
            totalValue += Double.parseDouble(product.getPrice()) * quantity;
            notifyListenerstoQuantityChanged();
        }

        public void decreaseProductQuantity(Product product, int quantityToDecrease){
            for (CarrinhoItem item : carrinhoItems){
                if (item.getProduct().getId() == product.getId()){
                    int currentQuantity = item.getQuantity();

                    if (currentQuantity >= quantityToDecrease){
                        item.decreaseQuantity(quantityToDecrease);
                        totalQuantity -= quantityToDecrease;
                        totalValue -= Double.parseDouble(product.getPrice()) * quantityToDecrease;
                        notifyListenerstoQuantityChanged();

                    } else {
                        totalQuantity -= item.getQuantity();
                        totalValue -= item.getTotalValue();
                        carrinhoItems.remove(item);
                        notifyListenerstoQuantityChanged();
                    }
                    break;
                }
            }
        }

        public void removeAllProducts() {
            Iterator<CarrinhoItem> iterator = carrinhoItems.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
            totalQuantity = 0;
            totalValue = 0;
            notifyListenerstoClearCarrinho();
        }

        public void clearCarrinho(){
            removeAllProducts();
            notifyListenerstoClearCarrinho();
        }

        public List<CarrinhoItem> getProductList() {
            return carrinhoItems;
        }

        public void increaseTotalQuantity(int quantity, double value) {
            totalQuantity += quantity;
            increaseTotalValue(value);
            notifyListenerstoQuantityChanged();
        }
        public void decreaseTotalQuantity(int quantity,double value){
            if (totalQuantity > 0){
                totalQuantity -= quantity;
                decreaseTotalValue(value);
            }
            notifyListenerstoQuantityChanged();
        }


        public void increaseTotalValue(double value){
            totalValue += value;
            notifyListenerstoQuantityChanged();

        }

        public void decreaseTotalValue(double value){
            totalValue -= value;
            notifyListenerstoQuantityChanged();

        }

        public CarrinhoItem getProductByItem(String productId) {
            for (CarrinhoItem item : carrinhoItems) {
                if (item.getProduct().getId().equals(productId)){
                    return item;
                }
            }
            return null;
        }

        public void finalizarCompra(AppDatabase db){
            Compra compra = new Compra(getTotalValue(),getTotalQuantity());
            String compraId = UUID.randomUUID().toString();
            compra.setId(compraId);

            new Thread(() ->{


                db.compraDao().insertCompra(compra);

                for (CarrinhoItem item : carrinhoItems){
                    ItemCompra itemCompra = new ItemCompra(compraId, item.getProduct().getId(),item.getQuantity(),Double.parseDouble(item.getProduct().getPrice()));
                    db.itemCompraDao().insertItemCompra(itemCompra);
                }

                clearCarrinho();
            }).start();
        }


        public interface CarrinhoListener{
            void onTotalQuantityChanged(int totalQuantity, double value);
            void onClearCarrinho();
        }
    }
