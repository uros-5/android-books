package com.example.knjizara.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.Menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.R;
import com.example.knjizara.model.Knjiga;
import com.example.knjizara.viewmodel.KorpaSP;
import com.squareup.picasso.Picasso;

import java.io.File;

public class KnjigaDetail  extends AppCompatActivity {
    private static final String TAG = "KnjigaDetail";

    public static final String EXTRA_ID = "knjigaID";

    public Knjiga knjiga;

    public KorpaSP korpaSP;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knjiga_detail);
        korpaSP = new KorpaSP(this);

        Knjiga knjiga = (Knjiga)getIntent().getExtras().get(EXTRA_ID);
        this.knjiga = knjiga;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_knjiga);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        try {
            String naslov = getSupportActionBar().getTitle().toString();
            getSupportActionBar().setTitle(naslov + " "+knjiga.getNaslov());

        } catch (Exception e) {

            System.out.println(e.getStackTrace());
        }


        TextView naslovView = (TextView) findViewById(R.id.naslovKnjige);
        naslovView.setText(knjiga.getNaslov());

        TextView autorView = (TextView) findViewById(R.id.autorKnjige);
        autorView.setText(knjiga.getAutor());

        Button kategorijaView = (Button) findViewById(R.id.kategorijaKnjige);
        kategorijaView.setText(knjiga.getKategorija());

        TextView cenaView = (TextView) findViewById(R.id.cenaKnjige);
        cenaView.setText("Cena knjige: "+knjiga.getCena());


        TextView izdavacView = (TextView) findViewById(R.id.textView4);
        izdavacView.setText("IZDAVAC: "+knjiga.getIzdavac());

        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        String fajl = knjiga.getIsbn()+".jpg";
        setSlika(imageView,fajl);
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_top_level,menu);
//        return true;
        return super.onCreateOptionsMenu(menu);
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

    public void setSlika(ImageView slika,String fajl) {
        File fileName = new File(getFilesDir().getAbsolutePath()+"/slike_knjiga/"+fajl);
        Picasso.get().load(fileName).into(slika);
    }

    public void dodajUKorpu(View view) {

        System.out.println("KNJIGA: "+knjiga);

        korpaSP.dodaj(knjiga);

    }

    public void vidiKategoriju (View view) {
        Intent intent = new Intent(KnjigaDetail.this,KategorijaActivity.class);
        intent.putExtra("Kategorija",knjiga.getKategorija());
        startActivity(intent);
    }
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeRight(this);
    }
}
