package com.example.knjizara.viewmodel;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.knjizara.model.Korisnik;
import com.google.gson.Gson;

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
       if(korisnik.getKartica() == null || korisnik.getKartica() == "") {
           return false;
       }
       else {
           return true;
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
//       System.out.println("DA VIDIMO: "+back);
       return back;
   }
   public void setBack(boolean back) {
       prefsEditor.putBoolean("korisnikBack",back);
       prefsEditor.apply();
   }


}
