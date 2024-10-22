package com.example.elevencash.productTable;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.elevencash.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductRepository {
    private ProductDao productDao;
    private ExecutorService executor;

    public ProductRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        productDao = db.productDao();
        executor = Executors.newSingleThreadExecutor();
    }

    public void insert(final Product product){

        executor.execute(() -> productDao.insertProduct(product));
    }

    public LiveData<List<Product>> getAllProducts(){
        return productDao.getAllProducts();
    }

    public LiveData<List<Product>> getProductsByCategory(String category){
        return productDao.getProductsByCategory(category);
    }


}
