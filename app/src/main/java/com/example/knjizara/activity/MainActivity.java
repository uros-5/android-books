package com.example.knjizara.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.knjizara.R;
import com.example.knjizara.adapter.PagerAdapter;
import com.example.knjizara.viewmodel.CurrentTabSP;
import com.example.knjizara.viewmodel.ListenerKorpa;
import com.example.knjizara.viewmodel.ListenerMojeKnjige;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    private ViewDataBinding binding ;


    @Nullable @BindView(R.id.tabLayout) TabLayout tabLayout;
    @Nullable @BindView(R.id.viewPager) ViewPager viewPager;
    @Nullable @BindView(R.id.knjizaraTab) TabItem knjizaraTab;
    @Nullable @BindView(R.id.mojeKnjigeTab) TabItem mojeKnjigeTab;
    @Nullable @BindView(R.id.korpaTab) TabItem korpaTab;

    public PagerAdapter pagerAdapter;
    ListenerMojeKnjige listenerMojeKnjige;
    ListenerKorpa listenerKorpa;
    CurrentTabSP currentTabSP;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.main_layout);
        Toast.makeText(this,"Ucitavanje resursa..",Toast.LENGTH_LONG).show();
        initData();

    }

    public void initData() {

        ButterKnife.bind(this);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==1){
                    if(!listenerMojeKnjige.compare()) {
                        try {
                            pagerAdapter.updateTab2();
                            listenerMojeKnjige.setCounter(listenerMojeKnjige.getSizeOfSP());
                        }
                        catch (Exception e) {
                            System.out.println("greska");
                        }
                    }
                }
                else if(tab.getPosition()==2) {
                    if(!listenerKorpa.compare()) {
                        try {
                            pagerAdapter.updateTab3();
                            listenerKorpa.setCounter(listenerKorpa.getSizeOfSP());
                        }
                        catch (Exception e) {
                            System.out.println("greska");
                        }
                    }
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        setImportantListeners();
        viewPager.setOffscreenPageLimit(3);
        currentTabSP = new CurrentTabSP(this);
        currentTabSP.setCT(0);
        setupTabIcons();
        pagerAdapter.longListener(tabLayout);
    }

    public void onRestart() {
        super.onRestart();
        if(currentTabSP.getCT()==2) {
            Handler handlerUpdate = new Handler();
            handlerUpdate.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pagerAdapter.updateTab3();
                    viewPager.setCurrentItem(0);
                    currentTabSP.setCT(0);
                }
            }, 600);

        }
        else if(currentTabSP.getCT()==1) {

            Handler handlerUpdate = new Handler();
            handlerUpdate.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pagerAdapter.updateTab2();
                    viewPager.setCurrentItem(1);
                    currentTabSP.setCT(0);

                }
            }, 600);

        }
    }

    public void onPause() {
        super.onPause();
        if(currentTabSP.getCT()==1) {
            onRestart();
        }
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.baseline_account_balance_black_48dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.moje_knjige);
        tabLayout.getTabAt(2).setIcon(R.drawable.kolica);
    }

    public void setImportantListeners(){
        listenerMojeKnjige = new ListenerMojeKnjige(this);
        listenerMojeKnjige.setCounter(0);
        pagerAdapter.getMojeKnjigeListener(listenerMojeKnjige);

        listenerKorpa = new ListenerKorpa(this);
        listenerKorpa.setCounter(0);
        pagerAdapter.getKorpaListener(listenerKorpa);
    }





}


