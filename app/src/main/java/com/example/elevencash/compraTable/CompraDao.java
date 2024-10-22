package com.example.elevencash.compraTable;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface CompraDao {

    @Insert
    void insertCompra(Compra compra);

}
