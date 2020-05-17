package com.example.knjizara.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.knjizara.fragments.Tab1;
import com.example.knjizara.fragments.Tab2;
import com.example.knjizara.fragments.Tab3;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numoftabs;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numoftabs = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new Tab1();
            case 1:
                return new Tab2();
            case 2:
                return new Tab3();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numoftabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
