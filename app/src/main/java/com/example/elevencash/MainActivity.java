package com.example.elevencash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity{
    Carrinho carrinho;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton vendaButton = findViewById(R.id.vendaButton);
        ImageButton relatorioButton = findViewById(R.id.relatorioButton);

        vendaButton.setOnClickListener(v -> {
            Intent intent = new Intent(this,VendaActivity.class);
            startActivity(intent);
        });

        relatorioButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RelatorioActivity.class);
            startActivity(intent);
        });
    }
}
