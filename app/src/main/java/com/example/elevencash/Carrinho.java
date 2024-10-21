    package com.example.elevencash;

    import android.util.Log;

    import java.util.ArrayList;
    import java.util.List;

    public class Carrinho{
        public static List<Product> productList = new ArrayList<>();
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

        public void addProduct(Product product){
            productList.add(product);

        }

        public void clearCarrinho(){
            totalQuantity = 0;
            totalValue = 0;
            notifyListenerstoClearCarrinho();
        }

        public List<Product> getProductList() {
            return productList;
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


        public interface CarrinhoListener{
            void onTotalQuantityChanged(int totalQuantity, double value);
            void onClearCarrinho();
        }
    }
