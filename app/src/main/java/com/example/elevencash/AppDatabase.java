package com.example.elevencash;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.elevencash.compraTable.Compra;
import com.example.elevencash.compraTable.CompraDao;
import com.example.elevencash.itemCompraTable.ItemCompra;
import com.example.elevencash.itemCompraTable.ItemCompraDao;
import com.example.elevencash.productTable.Product;
import com.example.elevencash.productTable.ProductDao;

@Database(entities = {Product.class, Compra.class, ItemCompra.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract CompraDao compraDao();
    public abstract ItemCompraDao itemCompraDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "elevencash_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
