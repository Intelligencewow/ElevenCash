package com.example.elevencash.productTable;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.elevencash.Carrinho;
import com.example.elevencash.CarrinhoItem;
import com.example.elevencash.R;
import com.google.android.material.card.MaterialCardView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements View.OnClickListener {
    private final onItemClickListener listener;
    private  final Carrinho carrinho = Carrinho.getINSTANCE();
    private List<Product> productList;
    private Context context;

    public ProductAdapter(Context context, List<Product> productList, onItemClickListener listener){
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        Log.i("MyActivity", "onBindViewHolder: " + product.getId());
        CarrinhoItem carrinhoItem = carrinho.getProductByItem(product.getId());

        Log.i("MyActivity", "Quantidade de produtos no carrinho: " + product.getQuantity());
        holder.productName.setText(product.getName());

        if (carrinhoItem != null){
            holder.quantity.setText(String.valueOf(carrinhoItem.getQuantity()));

        } else{
            holder.quantity.setText("0");

        }
        double price = Double.parseDouble(product.getPrice());
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedPrice = currencyFormat.format(price)
                .replace(",", "TEMP")
                .replace(".", ",")
                .replace("TEMP", ".");
        holder.productPrice.setText(formattedPrice);

        String imageUrl = product.getImageUrl();

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.imageView);
        }



        holder.buttonIncrease.setOnClickListener(v -> {
            Log.i("MyActivity", "Clicou no botão: ");
            carrinho.addProduct(product,1);
            notifyItemChanged(position);
            if(listener != null){
                listener.onItemClick(product.getPrice(), product.getQuantity(), 1);
            }
        });

        holder.buttonDecrease.setOnClickListener(v -> {
                carrinho.decreaseProductQuantity(product, 1);
                notifyItemChanged(position);
                if (listener != null) {
                    listener.onItemClick(product.getPrice(), product.getQuantity(), 0);
                }
        });


        if (carrinhoItem != null){
            if (carrinhoItem.getQuantity() > 0) {
                holder.cardView.setStrokeColor(Color.parseColor("#572C88"));
                holder.cardView.setStrokeWidth(2);
            }
        } else{
            holder.cardView.setStrokeWidth(0);
        }

    }

    public void setProductList(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    public void clearCarrinho(){
        Log.i("MyActivity", "clearCarrinho: ");
        for (Product product : productList){
            product.setQuantity(0);
            Log.i("MyActivity", "Product name e quantidade: " + product.getName() + " " + product.getQuantity());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onClick(View v) {

    }

    public interface onItemClickListener{
        void onItemClick(String price, int quantity, int choice);
        void onItemLongClick(int position);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        public TextView productName;
        public TextView productPrice;
        public TextView quantity;
        public ImageView imageView;
        public ImageButton buttonIncrease;
        public ImageButton buttonDecrease;
        public MaterialCardView cardView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            quantity = itemView.findViewById(R.id.quantity);
            imageView = itemView.findViewById(R.id.product_image);
            buttonIncrease = itemView.findViewById(R.id.button_increase);
            buttonDecrease = itemView.findViewById(R.id.button_decrease);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
