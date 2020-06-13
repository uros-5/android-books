package com.example.knjizara.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.knjizara.model.Korisnik;
import com.google.gson.Gson;

import java.security.spec.ECField;

import static android.content.Context.MODE_PRIVATE;

public class KorisnikSP {
   Gson gson;
   SharedPreferences pref;
   SharedPreferences.Editor prefsEditor;
   Context context;
   public boolean back;

   public KorisnikSP (Context context) {

       this.context = context;

       pref = context.getSharedPreferences("com.example.knjizara", MODE_PRIVATE);

       prefsEditor = pref.edit();

       gson = new Gson();

       back = false;
   }

   public boolean isNull() {

       Korisnik korisnik = getKorisnik();
       try{
           if(korisnik.id == null || korisnik.id == "") {
               return false;
           }
           else {
               return true;
           }
       }
       catch (Exception e) {
           setKorisnik(korisnik);
           return false;
       }

   }
   public Korisnik getKorisnik () {
       String json = pref.getString("korisnik","");
       Korisnik obj = gson.fromJson(json,Korisnik.class);
       return obj;
   }
   public void setKorisnik(Korisnik korisnik) {
       String jsonKorisnik = gson.toJson(korisnik);
       prefsEditor.putString("korisnik",jsonKorisnik);
       prefsEditor.apply();

   }
   public boolean getBack() {
       boolean back = pref.getBoolean("korisnikBack",false);
       return back;
   }
   public void setBack(boolean back) {
       prefsEditor.putBoolean("korisnikBack",back);
       prefsEditor.apply();
   }


}
