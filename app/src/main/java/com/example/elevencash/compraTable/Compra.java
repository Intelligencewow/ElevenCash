package com.example.elevencash.compraTable;

import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "compra")
public class Compra {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String id;

    @ColumnInfo(name = "valor_da_compra")
    private double totalValue;

    @ColumnInfo(name = "quantidade_de_itens_comprados")
    private int totalQuantidade;

    @ColumnInfo(name = "data_da_compra")
    private long dataCompra;

    public Compra(double totalValue, int totalQuantidade) {
        this.totalValue = totalValue;
        this.totalQuantidade = totalQuantidade;
        this.dataCompra = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public int getTotalQuantidade() {
        return totalQuantidade;
    }

    public void setTotalQuantidade(int totalQuantidade) {
        this.totalQuantidade = totalQuantidade;
    }

    public long getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(long dataCompra) {
        this.dataCompra = dataCompra;
    }
}
