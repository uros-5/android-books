package com.example.knjizara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import java.io.File;

public class KorpaActivity extends AppCompatActivity {
    Korpa korpa;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korpa);

        korpa = new Korpa(this);
        File file = new File(getFilesDir().getAbsolutePath()+"/korpa.out");
        if(!file.exists()){
            korpa.napraviFajl(null);
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_glavni);
        setSupportActionBar(toolbar);

        ToolbarActivityListener toolbarActivityListener = new ToolbarActivityListener(R.id.kolicaLayoutTopLevel,KorpaActivity.this);

        toolbarActivityListener.dodajListener(R.id.homeLayoutTopLevel);
        toolbarActivityListener.dodajListener(R.id.mojeKnjigeLayoutTopLevel);


        initRecyclerView();
        korpa.setStanjeKorpa();
        System.out.println("BROJ KNJIGA U KORPI: " + korpa.getSizeOfKorpa());
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

}
