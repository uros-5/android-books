package com.example.knjizara.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.R;
import com.example.knjizara.viewmodel.CurrentTabSP;

public class PseudoNotificationActivity extends AppCompatActivity {

    CurrentTabSP currentTabSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pseudo_notification);
        currentTabSP = new CurrentTabSP(this);

        currentTabSP.setCT(1);
        Handler handlesCT = new Handler();
        handlesCT.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 600);
    }
}
