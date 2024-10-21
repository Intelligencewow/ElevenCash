package com.example.elevencash.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.elevencash.Product;
import com.example.elevencash.ProductAdapter;
import com.example.elevencash.R;

import java.util.ArrayList;

public class MineriosFragment extends Fragment implements ProductAdapter.onItemClickListener {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_minerios, container, false);
        recyclerView = view.findViewById(R.id.mineriosRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product("Minério de cobre", "10"));
        productList.add(new Product("Minério de estanho", "15"));
        productList.add(new Product("Minério de ferro", "20"));
        productList.add(new Product("Carvão mineral", "20"));
        productList.add(new Product("Minério de mithril", "20"));
        productList.add(new Product("Minério de adamantio", "20"));




        productAdapter = new ProductAdapter(getActivity(), productList, this);
        recyclerView.setAdapter(productAdapter);

        return view;
    }

    @Override
    public void onItemClick(String price, int quantity, int choice) {

    }

    @Override
    public void onItemLongClick(int position) {

    }
}