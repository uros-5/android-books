package com.example.knjizara;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class KnjigaDetail  extends AppCompatActivity {
    private static final String TAG = "KnjigaDetail";

    public static final String EXTRA_ID = "knjigaID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.knjiga_detail);





        Knjiga knjiga = (Knjiga)getIntent().getExtras().get(EXTRA_ID);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_knjiga);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        try {
            String naslov = getSupportActionBar().getTitle().toString();
            getSupportActionBar().setTitle(naslov + " "+knjiga.naslov);

        } catch (Exception e) {

            System.out.println(e.getStackTrace());
        }


        TextView naslovView = (TextView) findViewById(R.id.naslovKnjige);
        naslovView.setText(knjiga.getNaslov());

        TextView autorView = (TextView) findViewById(R.id.autorKnjige);
        autorView.setText(knjiga.getAutor());

        Button kategorijaView = (Button) findViewById(R.id.kategorijaKnjige);
        kategorijaView.setText(knjiga.kategorija);

        TextView cenaView = (TextView) findViewById(R.id.cenaKnjige);
        cenaView.setText("Cena knjige: "+knjiga.getCena());


        TextView izdavacView = (TextView) findViewById(R.id.textView4);
        izdavacView.setText("IZDAVAC: "+knjiga.izdavac);


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
                return super.onOptionsItemSelected(item);
        }
    }
}
