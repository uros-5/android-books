package com.example.knjizara.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.example.knjizara.R;
import com.example.knjizara.adapter.PagerAdapter;
import com.example.knjizara.viewmodel.CurrentTabSP;
import com.example.knjizara.viewmodel.ListenerKorpa;
import com.example.knjizara.viewmodel.ListenerMojeKnjige;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity2 extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem knjizaraTab,mojeKnjigeTab,korpaTab;
    public PagerAdapter pagerAdapter;
    ListenerMojeKnjige listenerMojeKnjige;
    ListenerKorpa listenerKorpa;
    CurrentTabSP currentTabSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout2);
        System.out.println("PRVI PUT");

        initData();

    }

    public void initData() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        knjizaraTab = (TabItem) findViewById(R.id.knjizaraTab);
        mojeKnjigeTab = (TabItem) findViewById(R.id.mojeKnjigeTab);
        korpaTab = (TabItem) findViewById(R.id.korpaTab);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

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
                            System.out.println("BILI SU RAZLICITI1");
                        }
                        catch (Exception e) {
                            System.out.println("BILI SU GRESKA");
                        }
                    }
                }
                else if(tab.getPosition()==2) {
                    if(!listenerKorpa.compare()) {
                        try {
                            pagerAdapter.updateTab3();
                            listenerKorpa.setCounter(listenerKorpa.getSizeOfSP());
                            System.out.println("BILI SU RAZLICITI2");
                        }
                        catch (Exception e) {
                            System.out.println("BILI SU GRESKA");
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
        System.out.println("SAMO JEDANPUT");
    }

    public void onRestart() {
        System.out.println("NOTIFIKACIJA BROJ PROVERA");
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
                    cancelNotification();
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

    public void setImportantListeners(){
        listenerMojeKnjige = new ListenerMojeKnjige(this);
        listenerMojeKnjige.setCounter(0);
        pagerAdapter.getMojeKnjigeListener(listenerMojeKnjige);

        listenerKorpa = new ListenerKorpa(this);
        listenerKorpa.setCounter(0);
        pagerAdapter.getKorpaListener(listenerKorpa);

        System.out.println("BRAVOOOO");
    }
    public void cancelNotification() {
        try {
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();

        }
        catch (Exception e) {

        }
    }




}


