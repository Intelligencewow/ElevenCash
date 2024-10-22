package com.example.elevencash.itemCompraTable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.elevencash.compraTable.Compra;

@Entity(tableName = "itens_compra",
foreignKeys = @ForeignKey(entity = Compra.class, parentColumns = "id",
        childColumns = "compra_id",
        onDelete = ForeignKey.CASCADE))
public class ItemCompra {

    @PrimaryKey(autoGenerate = false)
    private int id;

    private String compra_id;

    @ColumnInfo(name = "produto_id")
    private String produto_id;

    @ColumnInfo(name = "quantidade")
    private int quantidade;

    @ColumnInfo(name = "valor")
    private double preco_unitario;

    public ItemCompra(String compra_id, String produto_id ,int quantidade, double preco_unitario) {
        this.compra_id = compra_id;
        this.produto_id = produto_id;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompra_id() {
        return compra_id;
    }

    public void setCompra_id(String compra_id) {
        this.compra_id = compra_id;
    }

    public String getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(String produto_id) {
        this.produto_id = produto_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }
}

