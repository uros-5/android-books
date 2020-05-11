package com.example.knjizara;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class KorisnikInfoActivity extends AppCompatActivity {

    private static final String TAG = "KorisnikInfoActivity";

    public static final String EXTRA_ID = "IDD";

    public KorisnikInfo korisnikInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korisnik_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_knjiga);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        korisnikInfo = new KorisnikInfo(this);

        setListenerPotvrdi();
    }

    public void checkFields() {

        String ime = ((EditText)findViewById(R.id.imeEdit)).getText().toString();
        String prezime = ((EditText)findViewById(R.id.prezimeEdit)).getText().toString();
        String ulicaIBroj = ((EditText)findViewById(R.id.ulicaIBrojEdit)).getText().toString();
        String grad = ((EditText)findViewById(R.id.gradEdit)).getText().toString();
        String brojPoste = ((EditText)findViewById(R.id.brojPosteEdit)).getText().toString();
        String kartica = ((EditText)findViewById(R.id.karticaEdit)).getText().toString();
        String email = ((EditText)findViewById(R.id.emailEdit)).getText().toString();

        ArrayList<String> niz = new ArrayList<String>();
        niz.add(ime);
        niz.add(prezime);
        niz.add(ulicaIBroj);
        niz.add(grad);
        niz.add(brojPoste);
        niz.add(kartica);
        niz.add(email);


        for(String field:niz) {

            if(field.length() == 0) {
                System.out.println("Morate popuniti sva polja."+ field.length());
                Toast.makeText(this,"Morate popuniti sva polja.",Toast.LENGTH_LONG).show();
            }
        }
        Korisnik korisnik = new Korisnik(ime,prezime,ulicaIBroj,email,kartica,brojPoste,grad);
        korisnikInfo.napraviFajl(korisnik);

        Toast.makeText(this,"Uspesno ste se registrovali.",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(KorisnikInfoActivity.this,PlacanjeActivity.class);
        startActivity(intent);
    }

    public void setListenerPotvrdi() {
        Button korRegBtn = findViewById(R.id.korRegBtn);
        korRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               checkFields();
            }
        });

    }

}
