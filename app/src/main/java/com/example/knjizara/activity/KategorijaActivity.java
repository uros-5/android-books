package com.example.knjizara.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.Klijent;
import com.example.knjizara.R;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.KnjizaraInfo;

import org.json.JSONArray;

import java.util.ArrayList;

public class KategorijaActivity extends AppCompatActivity {

    public KnjizaraInfo knjizaraInfo;

    public Klijent klijent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategorija_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_knjiga);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        knjizaraInfo = new KnjizaraInfo(this);

        klijent = new Klijent();

        Intent intent = getIntent();
        String kategorija = intent.getStringExtra("Kategorija");
        this.setTitle("KNJIZARA - "+ kategorija);

        JSONArray niz = klijent.sendM("kategorija "+kategorija);
        knjizaraInfo.initKategorijaRV(niz);

    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        onBackPressed();
        return true;
    }

    public void onBackPressed() {
        finish();
        Animatoo.animateSwipeRight(this);
    }
    public void onPause() {
        finish();
        super.onPause();
    }
}
