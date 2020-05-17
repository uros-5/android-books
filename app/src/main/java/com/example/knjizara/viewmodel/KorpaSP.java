package com.example.knjizara.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.knjizara.R;
import com.example.knjizara.fragments.Tab3;
import com.example.knjizara.model.Knjiga;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class KorpaSP {

    Gson gson;
    SharedPreferences pref;
    SharedPreferences.Editor prefsEditor;
    Context context;

    public KorpaSP (Context context) {
        this.context = context;
        pref = context.getSharedPreferences("com.example.knjizara", MODE_PRIVATE);

        prefsEditor = pref.edit();

        gson = new Gson();
    }
    public ArrayList<Knjiga> getKorpa() {
        String json = pref.getString("korpa","");
        ArrayList<Knjiga> korpa = gson.fromJson(json,new TypeToken<ArrayList<Knjiga>>(){}.getType());
        return korpa;
    }

    public int getSizeOfKorpa() {
        return getKorpa().size();
    }

    public void dodaj(Knjiga knjiga) {
        ArrayList<Knjiga> korpa = getKorpa();
        boolean provera = proveri(knjiga);
        if(provera==false) {
            korpa.add(knjiga);
            setAray(korpa);
            Toast.makeText(context,"Knjiga je dodata.",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context,"Knjiga je vec dodata u korpu.",Toast.LENGTH_LONG).show();
        }

    }

    public void obrisi(Knjiga knjiga) {
        ArrayList<Knjiga> korpa = getKorpa();
        boolean provera = proveri(knjiga);
        if(provera==true) {
            korpa.remove(knjiga);
            setAray(korpa);
        }
        else {
            //toast
        }

    }

    public double getUkupnaCenaKnjiga() {
        ArrayList<Knjiga> korpa = getKorpa();
        double ukupno = 0.00;
        for (Knjiga knjiga:korpa) {
            if(!knjiga.getCena().startsWith("Besplatno")) {
                ukupno+= Double.parseDouble(knjiga.getCena());
            }
        }

        return ukupno;
    }

    public void setAray (ArrayList<Knjiga> niz) {
        String jsonKorpa = "";
        ArrayList<Knjiga> korpa;
        if(niz == null) {
            korpa = new ArrayList<Knjiga>();
            jsonKorpa = gson.toJson(korpa);

        }
        else {
            korpa = niz;
            jsonKorpa = gson.toJson(korpa);
        }
        prefsEditor.putString("korpa",jsonKorpa);
        prefsEditor.apply();
    }

    public boolean proveri (Knjiga knjiga) {
        ArrayList<Knjiga> korpa = getKorpa();
        if(korpa.contains(knjiga)) {
            return true;
        }
        return false;
    }

    public void setStanje (View view) {
        TextView stanjeKorpaView;
        TextView cenaKorpa0;
        if(view != null) {
            stanjeKorpaView = (TextView) view.findViewById(R.id.stanjeKorpa);
            cenaKorpa0 = (TextView) view.findViewById(R.id.cenaKorpa);
        }
        else {
            stanjeKorpaView = (TextView) ((Activity) context).findViewById(R.id.stanjeKorpa);
            cenaKorpa0 = (TextView) ((Activity) context).findViewById(R.id.cenaKorpa);
        }
        String stanjeKorpa = String.format(Locale.US,"Korpa(%s):",String.valueOf(getSizeOfKorpa()));
        stanjeKorpaView.setText(stanjeKorpa);

        String cenaKorpa = String.format(Locale.US,"%s rsd.",String.valueOf(getUkupnaCenaKnjiga()));
        cenaKorpa0.setText(cenaKorpa);
    }

    public void setUkupnaCena() {
        TextView justCenaView = ((Activity) context).findViewById(R.id.justCena);
        String stanjeKorpa = String.format(Locale.US,"%s rsd.",String.valueOf(getUkupnaCenaKnjiga()));
        justCenaView.setText(stanjeKorpa);
    }


}
