package com.example.knjizara;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.knjizara.adapter.PlacanjeRVAdapter;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.Korpa;
import com.example.knjizara.viewmodel.MojeKnjige;

import java.util.ArrayList;

public class PlacanjeActivity extends AppCompatActivity {

    Korpa korpa;
    MojeKnjige mojeKnjige;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placanje);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_knjiga);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        korpa = new Korpa(this);
        korpa.setStanjeKorpa();
        korpa.setJustCena();

        mojeKnjige = new MojeKnjige(this);
        initRecyclerView();
    }

    public void initRecyclerView () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.placanjeRV);

        recyclerView.setLayoutManager(layoutManager);
        PlacanjeRVAdapter adapter = new PlacanjeRVAdapter(this,korpa.getNiz());
        recyclerView.setAdapter(adapter);

    }
    public void dodajUMojeKnjige (View view) {
        ArrayList<Knjiga> niz0 = korpa.getNiz();
        for (Knjiga knjiga:niz0) {
            mojeKnjige.dodajKnjigu(knjiga);
            System.out.println("NASLOVVV: "+knjiga.getNaslov());
        }
        korpa.napraviFajl(null);

        Intent intent = new Intent(PlacanjeActivity.this,MojeKnjigeActivity.class);
        startActivity(intent);
    }
}
