package com.example.knjizara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.knjizara.adapter.DetailKorpaRVAdapter;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.KorisnikInfo;
import com.example.knjizara.viewmodel.Korpa;

import java.io.File;

public class KorpaActivity extends AppCompatActivity {
    Korpa korpa;
    KorisnikInfo korisnikInfo;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korpa);

        korpa = new Korpa(this);
        System.out.println("LOKACIJA: "+getFilesDir().getAbsolutePath());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_glavni);
        setSupportActionBar(toolbar);

        ToolbarActivityListener toolbarActivityListener = new ToolbarActivityListener(R.id.kolicaLayoutTopLevel,KorpaActivity.this);

        toolbarActivityListener.dodajListener(R.id.homeLayoutTopLevel);
        toolbarActivityListener.dodajListener(R.id.mojeKnjigeLayoutTopLevel);

//        Log.i("DUZINA::: ",this.korpa.getNiz().size()+"");

        initRecyclerView();
        korpa.setStanjeKorpa();
        setListenerZavrsi();
        korisnikInfo = new KorisnikInfo(this);
    }
    public void initRecyclerView () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.knjige_iz_korpe0);

        recyclerView.setLayoutManager(layoutManager);
        DetailKorpaRVAdapter adapter = new DetailKorpaRVAdapter(this,korpa.getNiz());
        recyclerView.setAdapter(adapter);

    }
    public void obrisiIzKorpe (Knjiga knjiga) {
        korpa.obrisiKnjigu(knjiga);
        Parcelable recyclerViewState;
        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
    }
    public void azurirajStanjeKorpe () {
        korpa.setStanjeKorpa();
    }
    public void zavrsiKupovinu() {
        if(korpa.getNiz().size()>0) {
            if(!korisnikInfo.checkFile()) {
                Intent intent = new Intent(KorpaActivity.this,KorisnikInfoActivity.class);
                startActivity(intent);
            }
            else {

                Intent intent = new Intent(KorpaActivity.this,PlacanjeActivity.class);
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(this,"Niste dodali knjige u korpu.",Toast.LENGTH_LONG).show();
        }

    }

    public void setListenerZavrsi() {
        Button zkBtn = findViewById(R.id.zkBtn);
        zkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zavrsiKupovinu();
            }
        });

    }

}
