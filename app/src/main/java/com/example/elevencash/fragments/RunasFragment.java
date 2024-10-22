package com.example.elevencash.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elevencash.Carrinho;
import com.example.elevencash.productTable.Product;
import com.example.elevencash.productTable.ProductAdapter;
import com.example.elevencash.productTable.ProductViewModel;
import com.example.elevencash.R;

import java.util.ArrayList;


public class RunasFragment extends Fragment implements ProductAdapter.onItemClickListener, Carrinho.CarrinhoListener {
    private Carrinho carrinho = Carrinho.getINSTANCE();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    ProductViewModel productViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_runas, container, false);


        recyclerView = view.findViewById(R.id.runasRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        ArrayList<Product> productList = new ArrayList<>();

        carrinho.addListener(this);


        populateTable();
        productAdapter = new ProductAdapter(getActivity(), productList, this);
        recyclerView.setAdapter(productAdapter);

        productViewModel.getProductsByCategory("runes").observe(getViewLifecycleOwner(), products -> {

            productAdapter.setProductList(products);

        });
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

    public void populateTable(){
        productViewModel.insert(new Product("Runa do ar", "24.54", "runes", "https://runescape.wiki/images/thumb/Air_rune_detail.png/200px-Air_rune_detail.png?86f40"));
        productViewModel.insert(new Product("Runa da mente", "41.8", "runes", "https://runescape.wiki/images/thumb/Mind_rune_detail.png/200px-Mind_rune_detail.png?425e6"));
        productViewModel.insert(new Product("Runa da água", "32.25", "runes", "https://runescape.wiki/images/thumb/Water_rune_detail.png/200px-Water_rune_detail.png?e14c8"));
        productViewModel.insert(new Product("Runa da terra", "26.99", "runes", "https://runescape.wiki/images/thumb/Earth_rune_detail.png/200px-Earth_rune_detail.png?fa918"));
        productViewModel.insert(new Product("Runa do fogo", "17", "runes", "https://runescape.wiki/images/thumb/Fire_rune_detail.png/200px-Fire_rune_detail.png?fa918"));
    }
}