package com.example.knjizara.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.Klijent;
import com.example.knjizara.R;
import com.example.knjizara.adapter.KomentariAdapter;
import com.example.knjizara.adapter.TopLevelRVAdapter;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.KorpaSP;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class KnjigaDetail  extends AppCompatActivity {
    private static final String TAG = "KnjigaDetail";

    public static final String EXTRA_ID = "knjigaID";

    public String isbn;

    public String id;
    public Klijent klijent;

    ArrayList<ArrayList> knjiga;

    public KorpaSP korpaSP;

    ArrayList<ArrayList> niz00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knjiga_detail);

        String info = (String)getIntent().getExtras().get(EXTRA_ID);

        this.id = info;

        klijent = new Klijent();

        korpaSP = new KorpaSP(this);

        knjiga = klijent.sendM("id "+this.id);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_knjiga);
        setSupportActionBar(toolbar);

        niz00 = klijent.sendM("komentari " + this.id);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.isbn = knjiga.get(0).get(0).toString();

        try {
            String naslov = getSupportActionBar().getTitle().toString();
            getSupportActionBar().setTitle(naslov + " "+knjiga.get(0).get(1).toString());

        } catch (Exception e) {

            System.out.println(e.getStackTrace());
        }


        TextView naslovView = (TextView) findViewById(R.id.naslovKnjige);
        naslovView.setText(knjiga.get(0).get(1).toString());

        TextView autorView = (TextView) findViewById(R.id.autorKnjige);
        autorView.setText(knjiga.get(0).get(3).toString());

        Button kategorijaView = (Button) findViewById(R.id.kategorijaKnjige);
        kategorijaView.setText(knjiga.get(0).get(6).toString());

        TextView cenaView = (TextView) findViewById(R.id.cenaKnjige);
        cenaView.setText("Cena knjige: "+knjiga.get(0).get(2).toString());


        TextView izdavacView = (TextView) findViewById(R.id.textView4);
        izdavacView.setText("IZDAVAC: "+knjiga.get(0).get(7).toString());

        ImageView imageView = (ImageView) findViewById(R.id.imageView);



        String fajl = this.isbn+".jpg";
        setSlika(imageView,fajl);

        initRVKomentari();
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_top_level,menu);
//        return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        onBackPressed();
        return true;
    }

    public void setSlika(ImageView slika,String fajl) {
        File fileName = new File(getFilesDir().getAbsolutePath()+"/slike_knjiga/"+fajl);
        Picasso.get().load(fileName).into(slika);
    }

    public void dodajUKorpu(View view) {

        korpaSP.dodaj(id);

    }

    public void vidiKategoriju (View view) {
        Intent intent = new Intent(KnjigaDetail.this,KategorijaActivity.class);
        intent.putExtra("Kategorija", knjiga.get(0).get(6).toString());
        startActivity(intent);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeRight(this);
    }
    public void onPause() {
        finish();
        super.onPause();
    }

    public void postComment(View view) {
        Toast.makeText(this,"U sledecoj verziji.",Toast.LENGTH_LONG).show();
    }

    public void initRVKomentari() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = this.findViewById(R.id.komentariZaKnjige);
        recyclerView.setLayoutManager(layoutManager);
        KomentariAdapter adapter = new KomentariAdapter(this,niz00);
        recyclerView.setAdapter(adapter);
        layoutManager.smoothScrollToPosition(recyclerView,new RecyclerView.State(), recyclerView.getAdapter().getItemCount());
    }
}
