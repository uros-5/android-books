package com.example.knjizara.adapter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.knjizara.fragments.Tab1;
import com.example.knjizara.fragments.Tab2;
import com.example.knjizara.fragments.Tab3;
import com.example.knjizara.viewmodel.ListenerKorpa;
import com.example.knjizara.viewmodel.ListenerMojeKnjige;

public class PagerAdapter extends FragmentPagerAdapter {
    private int numoftabs;

    public Tab1 tab1 = new Tab1();
    public Tab2 tab2 = new Tab2();
    public Tab3 tab3 = new Tab3();
    public ViewPager viewPager;

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


    public void getMojeKnjigeListener(ListenerMojeKnjige listenerMojeKnjige) {
        tab2.setListenerMojeKnjige(listenerMojeKnjige);
    }
    public void getKorpaListener(ListenerKorpa listenerKorpa) {
        tab3.setListenerKorpa(listenerKorpa);
    }
    public void updateTab2() {
        tab2.updateTab();
    }
    public void updateTab3() {

        tab3.updateTab();
    }
}
