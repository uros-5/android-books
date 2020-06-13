package com.example.knjizara.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.knjizara.Klijent;
import com.example.knjizara.R;
import com.example.knjizara.adapter.PagerAdapter;
import com.example.knjizara.fragments.Tab3;
import com.example.knjizara.model.Knjiga;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

public class KorpaSP {

    Gson gson;
    SharedPreferences pref;
    SharedPreferences.Editor prefsEditor;
    Context context;
    Klijent klijent;

    public KorpaSP (Activity context) {
        setAll(context);
    }
    public KorpaSP() {

    }
    public ArrayList<String> getKorpa() {
        String json = pref.getString("korpa","");
        ArrayList<String> korpa = gson.fromJson(json,new TypeToken<ArrayList<String>>(){}.getType());
        return korpa;
    }

    public int getSizeOfKorpa() {
        return getKorpa().size();
    }

    public void setAll(Context context) {
        this.context = context;

        pref = context.getSharedPreferences("com.example.knjizara", MODE_PRIVATE);

        prefsEditor = pref.edit();

        gson = new Gson();

        klijent = new Klijent();
    }

    public void dodaj(String id) {
        ArrayList<String> korpa = getKorpa();
        boolean provera = proveri(id);
        if(provera==false) {
            korpa.add(id);
            setAray(korpa);
            Toast.makeText(context,"Knjiga je dodata.",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context,"Knjiga je vec dodata u korpu.",Toast.LENGTH_LONG).show();
        }

    }

    public void obrisi(String id) {
        ArrayList<String> korpa = getKorpa();
        boolean provera = proveri(id);
        if(provera==true) {
            korpa.remove(id);
            setAray(korpa);
        }
        else {
            //toast
        }

    }

    public double getUkupnaCenaKnjiga() {
        ArrayList<String> korpa = getKorpa();
        double ukupno = 0.00;
        for (String knjiga:korpa) {
            ArrayList<ArrayList> knjigaInfo = klijent.sendM("id "+knjiga);
            String cena = knjigaInfo.get(0).get(2).toString();
            if(!cena.startsWith("Besplatno")) {
                ukupno+= Double.parseDouble(cena);
            }
        }
        DecimalFormat df = new DecimalFormat("0.00");
        return Double.parseDouble(df.format(ukupno));
    }

    public void setAray (ArrayList<String> niz) {
        String jsonKorpa = "";
        ArrayList<String> korpa;
        if(niz == null) {
            korpa = new ArrayList<String>();
            jsonKorpa = gson.toJson(korpa);

        }
        else {
            korpa = niz;
            jsonKorpa = gson.toJson(korpa);
        }
        prefsEditor.putString("korpa",jsonKorpa);
        prefsEditor.apply();
        //azuriraj adapter


    }

    public boolean proveri (String isbn) {
        ArrayList<String> korpa = getKorpa();
        if(korpa.contains(isbn)) {
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
