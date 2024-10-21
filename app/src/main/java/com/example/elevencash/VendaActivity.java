package com.example.elevencash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class VendaActivity extends AppCompatActivity {
    private String TAG = "MyActivity";
    private int selectedChipId;

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
                    Log.i(TAG, "Chegou no parseResult ");
                    if(resultCode == RESULT_OK && intent != null){
                        return "OK";
                    }
                    Log.i(TAG, "result code não foi daora");
                    return "Not Ok";
                }



            }, new ActivityResultCallback<String>() {

                public void onActivityResult(String result){
                    Log.i(TAG, "onActivityResult: " + result);
                };
            });
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

        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(adapter);

        ChipGroup chipGroup = findViewById(R.id.chipGroup);

        ImageButton buttonVoltar = findViewById(R.id.buttonVoltar);
        ImageButton buttonDeletar = findViewById(R.id.buttonDeletar);
        ImageButton buttonDebito = findViewById(R.id.buttonDebito);


        buttonVoltar.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        buttonDebito.setOnClickListener(v -> {
            Intent intent = new Intent("br.com.bencke.pagamento.PAGAMENTO");
            intent.putExtra("valor", 500);
            intent.putExtra("forma_pagamento", 1);
            makePaymentLauncher.launch(intent);
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
}