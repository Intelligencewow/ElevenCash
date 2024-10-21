package com.example.elevencash;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements View.OnClickListener {
    private List<Product> productList;
    private Context context;
    private final onItemClickListener listener;

    public ProductAdapter(Context context, List<Product> productList, onItemClickListener listener){
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    public interface onItemClickListener{
        void onItemClick(String price, int quantity, int choice);
        void onItemLongClick(int position);
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
        Log.i("MyActivity", "Quantidade de produtos no carrinho: " + product.getQuantity());
        holder.productName.setText(product.getName());
        holder.quantity.setText(product.getQuantityString());
        double price = Double.parseDouble(product.getPrice());
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedPrice = currencyFormat.format(price)
                .replace(",", "TEMP")
                .replace(".", ",")
                .replace("TEMP", ".");
        holder.productPrice.setText(formattedPrice);

        holder.buttonIncrease.setOnClickListener(v -> {
            Log.i("MyActivity", "Clicou no botão: ");
            product.increaseQuantity();
            notifyItemChanged(position);
            if(listener != null){
                listener.onItemClick(product.getPrice(), product.getQuantity(), 1);
            }
        });

        holder.buttonDecrease.setOnClickListener(v -> {
            product.decreaseQuantity();
            notifyItemChanged(position);
            if(listener != null){
                listener.onItemClick(product.getPrice(), product.getQuantity(),0 );
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    @Override
    public void onClick(View v) {

    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{
        public TextView productName;
        public TextView productPrice;
        public TextView quantity;
        public ImageButton buttonIncrease;
        public ImageButton buttonDecrease;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            quantity = itemView.findViewById(R.id.quantity);
            buttonIncrease = itemView.findViewById(R.id.button_increase);
            buttonDecrease = itemView.findViewById(R.id.button_decrease);
        }
    }
}
