package com.example.knjizara.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.R;
import com.example.knjizara.ToolbarActivityListener;
import com.example.knjizara.adapter.DetailKorpaRVAdapter;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.CurrentTabSP;
import com.example.knjizara.viewmodel.KorisnikSP;
import com.example.knjizara.viewmodel.KorpaSP;
import com.squareup.picasso.Picasso;

import java.io.File;

public class KorpaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    public KorpaSP korpaSP;
    public KorisnikSP korisnikSP;
    public CurrentTabSP currentTabSP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korpa);

        korpaSP = new KorpaSP(this);


        System.out.println("LOKACIJA: "+getFilesDir().getAbsolutePath());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_glavni);
        setSupportActionBar(toolbar);

        ToolbarActivityListener toolbarActivityListener = new ToolbarActivityListener(R.id.kolicaLayoutTopLevel,KorpaActivity.this);

        toolbarActivityListener.dodajListener(R.id.homeLayoutTopLevel);
        toolbarActivityListener.dodajListener(R.id.mojeKnjigeLayoutTopLevel);

//        Log.i("DUZINA::: ",this.korpa.getNiz().size()+"");

        initRecyclerView();
        korpaSP.setStanje(null);

        setListenerZavrsi();
        korisnikSP = new KorisnikSP(this);
        currentTabSP = new CurrentTabSP(this);
    }
    public void initRecyclerView () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView = findViewById(R.id.knjige_iz_korpe0);

//        recyclerView.setLayoutManager(layoutManager);
//        DetailKorpaRVAdapter adapter = new DetailKorpaRVAdapter(getac,korpaSP.getKorpa());
//        recyclerView.setAdapter(adapter);

    }
    public void obrisiIzKorpe (Knjiga knjiga) {
        korpaSP.obrisi(knjiga);
        Parcelable recyclerViewState;
        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
    }
    public void azurirajStanjeKorpe () {
        korpaSP.setStanje(null);
    }

    public void zavrsiKupovinu() {

        if(korpaSP.getSizeOfKorpa()>0) {
            if(korisnikSP.isNull() == false) {
                currentTabSP.setCT(33);
                Intent intent = new Intent(KorpaActivity.this,KorisnikInfoActivity.class);
                startActivity(intent);
                Animatoo.animateDiagonal(this);
            }
            else {

                Intent intent = new Intent(KorpaActivity.this,PlacanjeActivity.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(this);
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

    public void setSlika(ImageView slika, String fajl) {
        File fileName = new File(getFilesDir().getAbsolutePath()+"/slike_knjiga/"+fajl);
        Picasso.get().load(fileName).into(slika);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeRight(this);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch(item.getItemId()) {
//            case R.id.idi_nazad:
//
//                Intent intent = new Intent(this,MainActivity.class);
//                startActivity(intent);
//                return true;
            default:
                onBackPressed();
                return super.onOptionsItemSelected(item);
        }
    }

}
