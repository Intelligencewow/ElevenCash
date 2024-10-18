package com.example.elevencash;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
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