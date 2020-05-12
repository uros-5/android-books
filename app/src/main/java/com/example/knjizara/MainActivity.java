package com.example.knjizara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.KnjizaraInfo;
import com.example.knjizara.adapter.TopLevelRVAdapter;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    KnjizaraInfo knjizaraInfo;
    ArrayList<Knjiga> niz0 = new ArrayList<>();
    ArrayList<Knjiga> niz1 = new ArrayList<>();
    ArrayList<Knjiga> hronoloskaLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_glavni);
        setSupportActionBar(toolbar);

        File testt = new File("C:/Users/Uros/Documents/NetBeansProjects/TestZaKnjizaru/test");
        if (testt.exists() && testt.isDirectory()) {
            Log.i("poruka"," jeste");
        }
        else {
            Log.i("poruka"," nije");
        }

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


}

