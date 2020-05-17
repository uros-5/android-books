package com.example.knjizara.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.knjizara.model.Knjiga;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class MojeKnjigeSP {

    Gson gson;
    SharedPreferences pref;
    SharedPreferences.Editor prefsEditor;
    Context context;

    public MojeKnjigeSP (Context context) {
        this.context = context;

        pref = context.getSharedPreferences("com.example.knjizara", MODE_PRIVATE);

        prefsEditor = pref.edit();

        gson = new Gson();
    }

    public ArrayList<Knjiga> getMojeKnjige() {
        String json = pref.getString("mojeKnjige","");
        ArrayList<Knjiga> korpa = gson.fromJson(json,new TypeToken<ArrayList<Knjiga>>(){}.getType());
        return korpa;
    }

    public void dodaj(Knjiga knjiga) {
        ArrayList<Knjiga> mojeKnjige = getMojeKnjige();
        boolean provera = proveri(knjiga);
        if(provera==false) {
            mojeKnjige.add(knjiga);
            setAray(mojeKnjige);
        }
        else {
            //toast
        }

    }

    public boolean proveri (Knjiga knjiga) {
        ArrayList<Knjiga> korpa = getMojeKnjige();
        if(korpa.contains(knjiga)) {
            return true;
        }
        return false;
    }

    public void setAray (ArrayList<Knjiga> niz) {
        String jsonKorpa = "";
        ArrayList<Knjiga> mojeKnjige;
        if(niz == null) {
            mojeKnjige = new ArrayList<Knjiga>();
            jsonKorpa = gson.toJson(mojeKnjige);

        }
        else {
            mojeKnjige = niz;
            jsonKorpa = gson.toJson(mojeKnjige);
        }
        prefsEditor.putString("mojeKnjige",jsonKorpa);
        prefsEditor.apply();
    }

}
