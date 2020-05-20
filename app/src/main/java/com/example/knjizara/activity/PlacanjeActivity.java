package com.example.knjizara.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.NotificationHelper;
import com.example.knjizara.R;
import com.example.knjizara.adapter.PlacanjeRVAdapter;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.CurrentTabSP;
import com.example.knjizara.viewmodel.KorisnikSP;
import com.example.knjizara.viewmodel.KorpaSP;
import com.example.knjizara.viewmodel.MojeKnjigeSP;

import java.util.ArrayList;

public class PlacanjeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public KorpaSP korpaSP;
    public KorisnikSP korisnikSP;
    public MojeKnjigeSP mojeKnjigeSP;
    private final String CHANNEL_ID = "PlacanjeActivity";
    NotificationHelper notificationHelper;
    public ViewPager viewPager;
    CurrentTabSP currentTabSP;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placanje);

        notificationHelper = new NotificationHelper(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_knjiga);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        korisnikSP = new KorisnikSP(this);
        proveraKorisnika();
        currentTabSP = new CurrentTabSP(this);
    }

    public void initRecyclerView () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.placanjeRV);

        recyclerView.setLayoutManager(layoutManager);
        PlacanjeRVAdapter adapter = new PlacanjeRVAdapter(this,korpaSP.getKorpa());
        recyclerView.setAdapter(adapter);

    }
    public void dodajUMojeKnjige (View view) {
//        Intent intent = new Intent(PlacanjeActivity.this,MainActivity2.class);
//        startActivity(intent);
        try {
            finish();
            currentTabSP.setCT(2);
            Animatoo.animateSwipeRight(this);
        }
        catch (Exception e) {
            System.out.println("greskkaaaa;;; "+ e.getMessage());
        }


        final Toast toastProc = Toast.makeText(this,"Transakcija se obradjuje..",Toast.LENGTH_LONG);
        toastProc.show();
        Handler handlerProc = new Handler();
        handlerProc.postDelayed(new Runnable() {
            @Override
            public void run() {
                toastProc.cancel();
            }
        }, 2000);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Knjiga> niz0 = korpaSP.getKorpa();
                for (Knjiga knjiga:niz0) {
                    mojeKnjigeSP.dodaj(knjiga);
                }
                korpaSP.setAray(null);
                notificationHelper.createNotification("Uplata za knjige","Transakcija je uspesna.","MojeKnjigeActivity");


            }
        }, 5000);
        runOnUiThread(new Runnable() {
            public void run() {
            }
        });


    }

    public void nazad(View view) {
        finish();
        Animatoo.animateSwipeRight(this);
    }

    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeRight(this);
//        this.viewPager.setCurrentItem(0);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        onBackPressed();
        return true;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;

    }

    public void setAll() {
        korpaSP = new KorpaSP(this);
        korpaSP.setStanje(null);
        korpaSP.setUkupnaCena();

        mojeKnjigeSP = new MojeKnjigeSP(this);
        initRecyclerView();
    }


    @Override
    public void onRestart() {
        super.onRestart();
        proveraKorisnika();
    }
    public void proveraKorisnika() {
        if(korisnikSP.isNull() == false) {
            while(true) {
                if(korisnikSP.getBack() == true) {
                    finish();
                    korisnikSP.setBack(false);
                    break;
                }
                else {
//                    onPause();
                    Intent intent = new Intent(this, KorisnikInfoActivity.class);
                    startActivity(intent);
                    Animatoo.animateDiagonal(this);
                    korisnikSP.setBack(false);
                    break;
                }
            }
        }
        else{
            setAll();
        }
    }





}
