package com.example.elevencash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.NumberFormat;
import java.util.Locale;

public class VendaActivity extends AppCompatActivity implements Carrinho.CarrinhoListener{
    LinearLayout totalVendas;
    private String TAG = "MyActivity";

    private final ActivityResultLauncher<Intent> makePaymentLaunchee = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result ->{
        if (result.getResultCode() == RESULT_OK){
            Log.i(TAG, "DEu certo: ");
        }
            });
    private final ActivityResultLauncher<Intent> makePaymentLauncher =
            registerForActivityResult(new ActivityResultContract<Intent, String>() {

                @NonNull
                @Override
                public Intent createIntent(@NonNull Context context, Intent intent) {
                    Log.i(TAG, "Criou a intent: ");
                    return intent;
                }

                @Override
                public String parseResult(int resultCode, @Nullable Intent intent) {

                    if(resultCode == RESULT_OK && intent != null){
                        Log.i(TAG, "result code foi daora");
                        return "OK";
                    }
                    Log.i(TAG, "result code não foi daora");
                    return "Not Ok";
                }


            }, new ActivityResultCallback<String>() {

                public void onActivityResult(String result){
                    if (result.equals("OK")){
                        Intent intent = new Intent(VendaActivity.this, SucessoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                };
            });
    private TextView tvtotalQuantity;
    private TextView tvTotalValue;
    private int selectedChipId;
    private Carrinho carrinho = Carrinho.getINSTANCE();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_venda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        totalVendas = findViewById(R.id.totalVendas);
        totalVendas.setVisibility(View.INVISIBLE);
        carrinho.addListener(this);
        tvtotalQuantity = findViewById(R.id.totalQuantity);
        tvTotalValue = findViewById(R.id.totalValue);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        ChipGroup chipGroup = findViewById(R.id.chipGroup);

        ImageButton buttonVoltar = findViewById(R.id.buttonVoltar);
        ImageButton buttonDeletar = findViewById(R.id.buttonDeletar);
        ImageButton buttonDebito = findViewById(R.id.buttonDebito);
        ImageButton buttonCredito = findViewById(R.id.buttonCredito);
        ImageButton buttonPix = findViewById(R.id.buttonPix);

        buttonVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        buttonDebito.setOnClickListener(v -> {
            Intent intent = new Intent("br.com.bencke.pagamento.PAGAMENTO");
            intent.putExtra("valor", Carrinho.getTotalValueCents());
            intent.putExtra("forma_pagamento", 1);
            makePaymentLauncher.launch(intent);
        });

        buttonCredito.setOnClickListener(v -> {
            Intent intent = new Intent("br.com.bencke.pagamento.PAGAMENTO");
            intent.putExtra("valor", Carrinho.getTotalValueCents());
            intent.putExtra("forma_pagamento", 2);
            makePaymentLauncher.launch(intent);
        });

        buttonPix.setOnClickListener(v -> {
            Intent intent = new Intent("br.com.bencke.pagamento.PAGAMENTO");
            intent.putExtra("valor", Carrinho.getTotalValueCents());
            intent.putExtra("forma_pagamento", 3);
            Log.i(TAG, "Fomos de pix: ");
            makePaymentLauncher.launch(intent);
        });

        buttonDeletar.setOnClickListener(v -> {
            createAlertDialogRemoveAll().show();
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Chip chip = (Chip) chipGroup.getChildAt(position);
                chipGroup.check(chip.getId());

                Log.i(TAG, "pageId: " + position);
            }
        });

        chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            selectedChipId = checkedIds.get(0);
            Log.i(TAG, "ChipId: " + selectedChipId);
            Chip selectedChip = findViewById(chipGroup.getCheckedChipId());

            if (selectedChip.getText().equals("Lenhas")){
                viewPager2.setCurrentItem(0);
            } else if (selectedChip.getText().equals("Minérios")){
                viewPager2.setCurrentItem(1);
            } else if (selectedChip.getText().equals("Peixes")){
                viewPager2.setCurrentItem(2);
            }  else if (selectedChip.getText().equals("Runas")){
                viewPager2.setCurrentItem(3);
            }
        });
    }

    @Override
    public void onTotalQuantityChanged(int totalQuantity, double value) {
        totalVendas.setVisibility(View.VISIBLE);
        Log.i(TAG, "Quantidade no carrinho venda activity: " + carrinho.getTotalQuantity());
        Log.i(TAG, "VEnda activity carrinho: " + totalQuantity);


        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedPrice = currencyFormat.format(value)
                .replace(",", "TEMP")
                .replace(".", ",")
                .replace("TEMP", ".");
        String formattedQuantity = "(" + String.valueOf(totalQuantity) + " itens)";
        tvtotalQuantity.setText(formattedQuantity);
        tvTotalValue.setText(String.valueOf(formattedPrice));

    }

    @Override
    public void onClearCarrinho() {
        tvtotalQuantity.setText("0");
        tvTotalValue.setText("0");
        totalVendas.setVisibility(View.INVISIBLE);
    }

    public AlertDialog createAlertDialogRemoveAll(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informação");
        builder.setMessage("Tem certeza que deseja limpar o carrinho?");

        builder.setNegativeButton("Não", ((dialog, which) -> {
            dialog.dismiss();
        }));
        builder.setPositiveButton("Sim", ((dialog, which) -> {

            carrinho.clearCarrinho();

        }));

        return builder.create();
    }
}