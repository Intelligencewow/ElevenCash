package com.example.elevencash;

import android.util.SparseIntArray;

public class Product {
    private String name;
    private String price;
    private int quantity;

    public Product(String name, String price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
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
