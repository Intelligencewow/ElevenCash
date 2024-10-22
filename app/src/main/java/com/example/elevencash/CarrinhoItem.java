package com.example.elevencash;

import com.example.elevencash.productTable.Product;

public class CarrinhoItem {
    Product product;
    private int quantity;
    private double totalValue;

    public CarrinhoItem(Product product){
        this.product = product;
        this.quantity = 1;
        this.totalValue = calculateTotalValue();

    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public double calculateTotalValue(){
        return  Double.parseDouble(product.getPrice()) * getQuantity();
    }

    public void increaseQuantity(int quantityToAdd){
        setQuantity(getQuantity() + quantityToAdd);
        setTotalValue(calculateTotalValue());

    }

    public void decreaseQuantity(int quantityToDecrease){
        setQuantity(getQuantity() - quantityToDecrease);
        if(getQuantity() < 0){
            setQuantity(0);
        }
        setTotalValue(calculateTotalValue());
    }

}
