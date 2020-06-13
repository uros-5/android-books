package com.example.knjizara.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.knjizara.activity.PopUpMojeKnjige;
import com.example.knjizara.fragments.Tab1;
import com.example.knjizara.fragments.Tab2;
import com.example.knjizara.fragments.Tab3;
import com.google.android.material.tabs.TabLayout;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numoftabs;

    public Tab1 tab1 = new Tab1();
    public Tab2 tab2 = new Tab2();
    public Tab3 tab3 = new Tab3();

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.numoftabs = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return tab1;
            case 1:
                return tab2;
            case 2:
                return tab3;
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

    public void updateTab2() {
        tab2.updateTab();
    }
    public void updateTab3() {
        tab3.updateTab();
    }
    public void longListener(TabLayout tabLayout) {
        //dobij tablayout implementiraj drugi
        LinearLayout tabs = (LinearLayout) tabLayout.getChildAt(0);
        tabs.getChildAt(1).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(v.getContext(), PopUpMojeKnjige.class);
                v.getContext().startActivity(intent);
//                Toast.makeText(v.getContext(), "Tab clicked" , Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

}
