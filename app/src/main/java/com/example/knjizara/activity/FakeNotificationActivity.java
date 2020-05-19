package com.example.knjizara.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.knjizara.R;
import com.example.knjizara.viewmodel.CurrentTabSP;

public class FakeNotificationActivity extends Activity {
    public int active = 0;

    CurrentTabSP currentTabSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pseudo_notification);
        currentTabSP = new CurrentTabSP(this);

        Handler handlesCT = new Handler();
        handlesCT.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentTabSP.setCT(1);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;
                int height = displayMetrics.heightPixels;
                getWindow().setLayout((int)(width*.01),(int)(height*.01));
                finish();
            }
        }, 1500);

    }
    public void onStart(){
        super.onStart();
        active++;
    }
    public void onResume(){
        super.onResume();
        active++;
    }
    public void onPause(){
        super.onPause();
    }
    public void onStop(){
        super.onStop();
    }
    public void onDestroy(){
        super.onDestroy();
    }
    public void onRestart() {
        super.onRestart();
        finish();
    }

}