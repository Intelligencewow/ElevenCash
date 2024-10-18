package com.example.elevencash;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.elevencash.fragments.LenhasFragment;
import com.example.elevencash.fragments.MineriosFragment;
import com.example.elevencash.fragments.PeixesFragment;
import com.example.elevencash.fragments.RunasFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new LenhasFragment();
            case 1:
                return new MineriosFragment();
            case 2:
                return new PeixesFragment();
            case 3:
                return new RunasFragment();
            default:
                return new LenhasFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
