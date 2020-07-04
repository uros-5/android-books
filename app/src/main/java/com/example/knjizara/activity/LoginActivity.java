package com.example.knjizara.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.Klijent;
import com.example.knjizara.R;
import com.example.knjizara.model.Korisnik;
import com.example.knjizara.viewmodel.CurrentTabSP;
import com.example.knjizara.viewmodel.KorisnikSP;

import org.json.JSONArray;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    KorisnikSP korisnikSP;

    public Klijent klijent;

    CurrentTabSP currentTabSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_knjiga);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        korisnikSP = new KorisnikSP(this);

        klijent = new Klijent();

        currentTabSP = new CurrentTabSP(this);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void onRegister(View view) {
        KorisnikInfoActivity korisnikInfoActivity = new KorisnikInfoActivity();
        Intent intent = new Intent(LoginActivity.this,KorisnikInfoActivity.class);
        intent.putExtra("success","home");
        startActivity(intent);
        Animatoo.animateDiagonal(this);
    }
    public void onLogin(View view) {
        String username = ((EditText)findViewById(R.id.username2)).getText().toString();
        String sifra = ((EditText)findViewById(R.id.password2)).getText().toString();

        if(username!="" && sifra!="") {
            JSONArray lista = klijent.sendM("getUser " +username+" " +sifra);
            try{
                String provera = lista.getJSONArray(0).get(0).toString();

                if(provera.equals("OK")) {
                    String id = String.valueOf(Integer.parseInt(lista.getJSONArray(0).get(1).toString()));

                    Korisnik korisnik = new Korisnik();
                    korisnik.id = id;

                    korisnikSP.setKorisnik(korisnik);
                    onRestart();
                    Toast.makeText(this,"Dobrodosli.",Toast.LENGTH_LONG).show();
                }
                else {

                    Toast.makeText(this,"Pogresni parametri..",Toast.LENGTH_LONG).show();
                }


            }
            catch (Exception e) {
                Toast.makeText(this,"Pogresni parametri..",Toast.LENGTH_LONG).show();
            }
        }


    }
    public void onRestart() {
        super.onRestart();
        // ako je true onda je postavljen i postoji i njegov id i sve
        if(korisnikSP.isNull()) {
            currentTabSP.setCT(4);
           onBackPressed();
            Animatoo.animateSlideUp(this);
        }
//        else{
//            currentTabSP.setCT(44);
//        }

    }
}
