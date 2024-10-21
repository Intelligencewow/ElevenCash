package com.example.elevencash.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elevencash.Carrinho;
import com.example.elevencash.Product;
import com.example.elevencash.ProductAdapter;
import com.example.elevencash.R;

import java.util.ArrayList;


public class PeixesFragment extends Fragment implements ProductAdapter.onItemClickListener, Carrinho.CarrinhoListener {
    Carrinho carrinho = Carrinho.getINSTANCE();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_peixes, container, false);

        recyclerView = view.findViewById(R.id.peixesRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product("Camarão", "10"));
        productList.add(new Product("Lagostim", "15"));
        productList.add(new Product("Vairão", "20"));
        productList.add(new Product("Karambwanji", "20"));
        productList.add(new Product("Sardinha", "20"));
        productList.add(new Product("Arenque", "20"));
        carrinho.addListener(this);



        productAdapter = new ProductAdapter(getActivity(), productList, this);
        recyclerView.setAdapter(productAdapter);
        return  view;
    }

    @Override
    public void onItemClick(String price, int quantity, int choice) {


    }

    @Override
    public void onItemLongClick(int position) {

    }

    @Override
    public void onTotalQuantityChanged(int totalQuantity, double value) {

    }

    @Override
    public void onClearCarrinho() {
        productAdapter.clearCarrinho();
    }
}