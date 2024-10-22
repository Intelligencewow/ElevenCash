package com.example.elevencash.productTable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "product")
public class Product {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    public String id;
    @ColumnInfo(name = "nome")
    private String name;

    @ColumnInfo(name = "preco")
    private String price;

    @ColumnInfo(name = "quantidade")
    private int quantity;

    @ColumnInfo(name = "imageUrl")
    public String imageUrl;

    @ColumnInfo(name = "categoria")
    public String category;

    public Product(String name, String price,String category,String imageUrl) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.quantity = 0;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getQuantityString(){
        return String.valueOf(getQuantity());
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(){
        this.quantity = getQuantity() + 1;
    }

    public void decreaseQuantity(){
        int actual_quantity = getQuantity();
        if (actual_quantity != 0){
            this.quantity = getQuantity() + - 1;
        }
    }
}
