package com.example.knjizara;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.KnjizaraInfo;

import java.util.ArrayList;

public class KategorijaActivity extends AppCompatActivity {

    public KnjizaraInfo knjizaraInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kategorija_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_knjiga);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        knjizaraInfo = new KnjizaraInfo(this);

        Intent intent = getIntent();
        String kategorija = intent.getStringExtra("Kategorija");

        ArrayList<Knjiga> niz = knjizaraInfo.getBooksCategory(kategorija);
        knjizaraInfo.initKategorijaRV(niz);

    }
}
