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


public class PeixesFragment extends Fragment implements ProductAdapter.onItemClickListener, Carrinho.CarrinhoListener {
    Carrinho carrinho = Carrinho.getINSTANCE();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    ProductViewModel productViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_peixes, container, false);

        recyclerView = view.findViewById(R.id.peixesRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        ArrayList<Product> productList = new ArrayList<>();

        carrinho.addListener(this);

        populateTable();

        productAdapter = new ProductAdapter(getActivity(), productList, this);
        recyclerView.setAdapter(productAdapter);

        productViewModel.getProductsByCategory("fish").observe(getViewLifecycleOwner(), products -> {

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
        productViewModel.insert(new Product("Camarão", "15.90", "fish", "https://runescape.wiki/images/thumb/Shrimps_detail.png/200px-Shrimps_detail.png?d476b"));
        productViewModel.insert(new Product("Lagostim", "32", "fish", "https://runescape.wiki/images/thumb/Crayfish_detail.png/200px-Crayfish_detail.png?3794c"));
        productViewModel.insert(new Product("Vairão", "123.25", "fish", "https://runescape.wiki/images/thumb/Minnow_detail.png/200px-Minnow_detail.png?a145b"));
    }
}