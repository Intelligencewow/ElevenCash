package com.example.elevencash.itemCompraTable;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface ItemCompraDao {

    @Insert
    void insertItemCompra(ItemCompra itemCompra);
}
