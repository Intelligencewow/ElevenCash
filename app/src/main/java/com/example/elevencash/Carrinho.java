package com.example.elevencash;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    public static List<Product> productList = new ArrayList<>();
    public static int totalValue = 0;
    public static int totalQuantity = 0;

    public Carrinho(){}

    public void addProduct(Product product){
        productList.add(product);

    }

    public List<Product> getProductList() {
        return productList;
    }

    public void increaseTotalQuantity(int quantity){
        totalQuantity += quantity;
    }


    public void decreaseTotalQuantity(int quantity){
        totalQuantity -= quantity;
    }


    public void increaseTotalValue(int value){
        totalValue += value;
    }


    public void decreaseTotalValue(int value){
        totalValue -= value;
    }

    public static int getTotalQuantity() {
        return totalQuantity;
    }

    public static int getTotalValue() {
        return totalValue;
    }
}
