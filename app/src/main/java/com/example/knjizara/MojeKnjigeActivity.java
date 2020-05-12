package com.example.knjizara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.knjizara.adapter.MojeKnjigeRVAdapter;
import com.example.knjizara.viewmodel.MojeKnjige;

import java.io.File;

public class MojeKnjigeActivity extends AppCompatActivity {

    MojeKnjige mojeKnjige;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moje_knjige);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_glavni);
        setSupportActionBar(toolbar);

        ToolbarActivityListener toolbarActivityListener = new ToolbarActivityListener(R.id.mojeKnjigeLayoutTopLevel,MojeKnjigeActivity.this);

        toolbarActivityListener.dodajListener(R.id.homeLayoutTopLevel);
        toolbarActivityListener.dodajListener(R.id.kolicaLayoutTopLevel);

        mojeKnjige = new MojeKnjige(this);

        initRecyclerView();



    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        LinearLayout layout = (LinearLayout) findViewById(R.id.mojeKnjigeLayoutTopLevel);
        layout.setBackgroundResource(0);
    }

    public void initRecyclerView () {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        RecyclerView recyclerView = findViewById(R.id.mojeKnjige0);
        recyclerView.setLayoutManager(gridLayoutManager);
        MojeKnjigeRVAdapter adapter = new MojeKnjigeRVAdapter(this,mojeKnjige.getNiz());
        recyclerView.setAdapter(adapter);
    }

}
