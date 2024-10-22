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

public class MineriosFragment extends Fragment implements ProductAdapter.onItemClickListener, Carrinho.CarrinhoListener {
    Carrinho carrinho = Carrinho.getINSTANCE();
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    ProductViewModel productViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_minerios, container, false);
        recyclerView = view.findViewById(R.id.mineriosRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        carrinho.addListener(this);
        
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);



        populateTable();
        productViewModel.getProductsByCategory("ores").observe(getViewLifecycleOwner(), products -> {

            productAdapter.setProductList(products);

        });

        ArrayList<Product> productList = new ArrayList<>();
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

    @Override
    public void onTotalQuantityChanged(int totalQuantity, double value) {

    }

    @Override
    public void onClearCarrinho() {

        productAdapter.clearCarrinho();

    }

    public void populateTable(){
        productViewModel.insert(new Product("Minério de cobre", "777.90", "ores", "https://runescape.wiki/images/Copper_ore_detail.png?2c4f9"));
        productViewModel.insert(new Product("Minério de estanho", "752", "ores", "https://runescape.wiki/images/thumb/Tin_ore_detail.png/242px-Tin_ore_detail.png?82a5a"));
        productViewModel.insert(new Product("Minério de ferro", "135.25", "ores", "https://runescape.wiki/images/thumb/Iron_ore_detail.png/200px-Iron_ore_detail.png?2d6f5"));
        productViewModel.insert(new Product("Carvão mineral", "89.99", "ores", "https://runescape.wiki/images/thumb/Coal_detail.png/200px-Coal_detail.png?332f3"));
        productViewModel.insert(new Product("Minério de mithril", "499.90", "ores", "https://runescape.wiki/images/thumb/Mithril_ore_detail.png/200px-Mithril_ore_detail.png?2d6f5"));
        productViewModel.insert(new Product("Luminita", "2605", "ores", "https://runescape.wiki/images/thumb/Luminite_detail.png/200px-Luminite_detail.png?2d6f5"));
    }
}