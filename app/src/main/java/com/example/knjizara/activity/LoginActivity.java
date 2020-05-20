package com.example.knjizara.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.knjizara.R;
import com.example.knjizara.model.Korisnik;
import com.example.knjizara.viewmodel.KorisnikSP;

public class LoginActivity extends AppCompatActivity {
    KorisnikSP korisnikSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_knjiga);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        korisnikSP = new KorisnikSP(this);
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
        startActivity(intent);
        Animatoo.animateDiagonal(this);
    }
    public void onLogin(View view) {
        Toast.makeText(this,"Pogresni parametri..",Toast.LENGTH_LONG).show();
    }
    public void onRestart() {
        super.onRestart();
        if(korisnikSP.isNull()) {
           onBackPressed();
            Animatoo.animateSlideUp(this);
        }

    }
}
