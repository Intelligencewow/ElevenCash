    package com.example.elevencash.fragments;

    import android.os.Bundle;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
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

    public class LenhasFragment extends Fragment implements ProductAdapter.onItemClickListener, Carrinho.CarrinhoListener{
        Carrinho carrinho = Carrinho.getINSTANCE();
        private RecyclerView recyclerView;
        private ProductAdapter productAdapter;
        ProductViewModel  productViewModel;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view =  inflater.inflate(R.layout.fragment_lenhas,container,false);

            carrinho.addListener(this);

            recyclerView = view.findViewById(R.id.lenhasRecyclerView);
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
            productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);


            populateTable();
            productViewModel.getProductsByCategory("logs").observe(getViewLifecycleOwner(), products -> {

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

            Log.i("MyActivity", "onClearCarrinho: ");
            productAdapter.clearCarrinho();

        }



        public void populateTable(){
            productViewModel.insert(new Product("Lenha de bordo", "7.90", "logs", "https://runescape.wiki/images/thumb/Logs_detail.png/200px-Logs_detail.png?44e42"));
            productViewModel.insert(new Product("Lenha de carvalho", "1000", "logs", "https://runescape.wiki/images/Oak_logs_detail.png?73bc9"));
            productViewModel.insert(new Product("Lenha de eucalipto", "175.25", "logs", "https://runescape.wiki/images/thumb/Eucalyptus_logs_detail.png/200px-Eucalyptus_logs_detail.png?ab86a"));
            productViewModel.insert(new Product("Lenha de mogno", "89.99", "logs", "https://runescape.wiki/images/Mahogany_logs_detail.png?dbcec"));
            productViewModel.insert(new Product("Lenha de teixo", "499.90", "logs", "https://pt.runescape.wiki/images/thumb/Lenha_de_teixo_detalhe.png/800px-Lenha_de_teixo_detalhe.png?cd12d"));
            productViewModel.insert(new Product("Lenha mágica", "3.5", "logs", "https://pt.runescape.wiki/images/Lenha_m%C3%A1gica_detalhe.png?f8a2f"));
        }
    }
