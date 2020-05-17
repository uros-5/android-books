package com.example.knjizara.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.knjizara.R;
import com.example.knjizara.ToolbarActivityListener;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.model.Korisnik;
import com.example.knjizara.viewmodel.KnjizaraInfo;
import com.example.knjizara.adapter.TopLevelRVAdapter;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    KnjizaraInfo knjizaraInfo;
    ArrayList<Knjiga> niz0 = new ArrayList<>();
    ArrayList<Knjiga> niz1 = new ArrayList<>();
    ArrayList<Knjiga> hronoloskaLista;
    Gson gson;
    SharedPreferences pref;
    SharedPreferences.Editor prefsEditor;
    public final String CHANNEL_ID = "PlacanjeActivity";
    public int NOTIFICATION_ID = 001;

    @BindView(R.id.toolbar_glavni)
    Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ToolbarActivityListener toolbarActivityListener = new ToolbarActivityListener(R.id.homeLayoutTopLevel,MainActivity.this);

        toolbarActivityListener.dodajListener(R.id.mojeKnjigeLayoutTopLevel);
        toolbarActivityListener.dodajListener(R.id.kolicaLayoutTopLevel);

        knjizaraInfo = new KnjizaraInfo(this);

        niz0 = knjizaraInfo.getNiz(0);
        niz1 = knjizaraInfo.getNiz(1);

        initRecyclerView("popularne");
        initRecyclerView("besplatne");

        hronoloskaLista = knjizaraInfo.mixBooks();
        initRecyclerView("hronoloskaLista");

        pref = getSharedPreferences("com.example.knjizara", MODE_PRIVATE);

        prefsEditor = pref.edit();

        gson = new Gson();

        if(!isInitAppDataDone()) {
            initAppData();
            Log.i("MainActivity ","Setup appdata upravo gotov.");
        }
        else {
            Log.i("MainActivity ","Setup appdata uradjen ranije. ");
        }

        try {
            knjizaraInfo.unzipAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRecyclerView (String kat) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        if(kat == "popularne") {
            RecyclerView recyclerView = findViewById(R.id.najpopularnijeKnjige0);

            recyclerView.setLayoutManager(layoutManager);
            TopLevelRVAdapter adapter = new TopLevelRVAdapter(this,niz0);
            recyclerView.setAdapter(adapter);
        }
        else if (kat == "besplatne") {

            RecyclerView recyclerView = findViewById(R.id.besplatneKnjige0);
            recyclerView.setLayoutManager(layoutManager);
            TopLevelRVAdapter adapter = new TopLevelRVAdapter(this,niz1);
            recyclerView.setAdapter(adapter);
        }
        else if (kat == "hronoloskaLista") {

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
            RecyclerView recyclerView = findViewById(R.id.hronoloskaLista0);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(gridLayoutManager);
            TopLevelRVAdapter adapter = new TopLevelRVAdapter(this,hronoloskaLista);
            adapter.setDuzina(hronoloskaLista.size());
            recyclerView.setAdapter(adapter);
            recyclerView.setNestedScrollingEnabled(false);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        LinearLayout layout = (LinearLayout) findViewById(R.id.homeLayoutTopLevel);
        layout.setBackgroundResource(0);
    }
    public boolean daLiSlikePostoje() {
        File putanja = new File(getFilesDir().getAbsolutePath()+"/slike_knjiga");
        if(!putanja.exists()) {
            putanja.mkdirs();
        }
        return false;
    }

    public void initAppData() {

        Korisnik korisnik = new Korisnik();
        ArrayList<Knjiga> korpa = new ArrayList<Knjiga>();
        ArrayList<Knjiga> mojeKnjige = new ArrayList<Knjiga>();

        String jsonKorisnik = gson.toJson(korisnik);
        String jsonKorpa = gson.toJson(korpa);
        String jsonMojeKnjige = gson.toJson(mojeKnjige);

        prefsEditor.putString("korisnik",jsonKorisnik);
        prefsEditor.putString("korpa",jsonKorpa);
        prefsEditor.putString("mojeKnjige",jsonMojeKnjige);


        prefsEditor.apply();

    }

    public boolean isInitAppDataDone () {
        if(pref.contains("korisnik") && pref.contains("korpa") && pref.contains("mojeKnjige")) {
            return true;
        }
        return false;
    }

}

