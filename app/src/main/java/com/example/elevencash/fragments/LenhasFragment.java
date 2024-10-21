package com.example.elevencash.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elevencash.Product;
import com.example.elevencash.ProductAdapter;
import com.example.elevencash.R;

import java.util.ArrayList;

public class LenhasFragment extends Fragment implements ProductAdapter.onItemClickListener {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_lenhas,container,false);

        recyclerView = view.findViewById(R.id.lenhasRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product("Lenha de bordo", "7.90"));
        productList.add(new Product("Lenha de carvalho", "1000"));
        productList.add(new Product("Lenha de eucalipto", "175.25"));
        productList.add(new Product("Lenha de mogno", "89.99"));
        productList.add(new Product("Lenha de teixo", "499.90"));
        productList.add(new Product("Lenha mágica", "3.5"));




        productAdapter = new ProductAdapter(getActivity(), productList, this);
        recyclerView.setAdapter(productAdapter);

        return view;
    }

    @Override
    public void onItemClick(String price, int quantity, int choice) {
        Log.i("MyActivity", "Estou no lenhas Cliquei e" + choice);

    }

    @Override
    public void onItemLongClick(int position) {

    }
}
