package com.example.elevencash.productTable;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private final ProductRepository productRepository;
    private LiveData<List<Product>> productList;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        productList = productRepository.getAllProducts();
    }

    public void insert(Product product){
        productRepository.insert(product);
    }

    public LiveData<List<Product>> getProductsByCategory(String category){
        return productRepository.getProductsByCategory(category);
    }
}
