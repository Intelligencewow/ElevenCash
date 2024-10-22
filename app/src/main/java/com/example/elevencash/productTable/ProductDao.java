package com.example.elevencash.productTable;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM product WHERE categoria = :category")
    LiveData<List<Product>> getProductsByCategory(String category);

}
