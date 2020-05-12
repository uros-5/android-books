/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.knjizara.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knjizara.R;
import com.example.knjizara.model.Knjiga;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uros
 */

public class Korpa implements Serializable {
    private static final long serialVersionUID = 1L;;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Context context;
    public Korpa (Context context) {
        this.context = ((Context) context);
        File file = new File(context.getFilesDir().getAbsolutePath()+"/korpa.out");
        if(!file.exists()){
            napraviFajl(null);
            System.out.println("napravljen");
        }
    }

    public void otvoriZaCitanje () {
        try {
            this.in = new ObjectInputStream(
                    new FileInputStream(context.getFilesDir().getAbsolutePath()+"/korpa.out"));
        }
        catch (Exception e) {
            System.out.println("Greska:" + e.getMessage());
        }
    }
    public ArrayList<Knjiga> getNiz () {
        try {
            otvoriZaCitanje();
            ArrayList<Knjiga> niz = (ArrayList<Knjiga>) in.readObject();
            Log.i("POSTOJI ",niz.size()+"");
            zatvoriZaCitanje();
            return niz;
        } catch (IOException ex) {
            Logger.getLogger(Korpa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Korpa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void zatvoriZaCitanje () {
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Korpa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void napraviFajl (ArrayList<Knjiga> knjige0) {
        try {
            this.out = new ObjectOutputStream(
                    new FileOutputStream(context.getFilesDir().getAbsolutePath()+"/korpa.out"));
            if(knjige0 == null) {
                ArrayList<Knjiga> knjige = new ArrayList<Knjiga>();
                out.writeObject(knjige);
                this.out.close();
            }
            else {
                out.writeObject(knjige0);
                this.out.close();
            }

        }
        catch (Exception e) {
            System.out.println("Greska:" + e.getMessage());
        }

    }
    public void otvoriZaDodavanje () {
        try {
            this.out = new ObjectOutputStream(
                    new FileOutputStream("korpa.out"));
        }
        catch (Exception e) {
            System.out.println("Greska:" + e.getMessage());
        }

    }
    public void zatvoriZaDodavanje () {
        try {
            out.close();
        }
        catch (Exception e) {
            System.out.println("Greska:" + e.getMessage());
        }

    }
    public boolean proveri (Knjiga knjiga) throws IOException {


        while(true) {
            try {
                //                in.readObject();
                ArrayList<Knjiga> knjige = getNiz();
                if(knjige.contains(knjiga)) {
                    return true;
                }
                else {
                    return false;
                }
            }
            catch (Exception e) {
                napraviFajl(null);
                System.out.println("Greska:" + e.getMessage());
                continue;
            }
        }
    }

    public void dodajKnjigu(Knjiga knjiga) {
        try {
            ArrayList<Knjiga> knjige = getNiz();
            boolean provera = proveri(knjiga);
            if (provera == false) {
                System.out.println("");
                knjige.add(knjiga);
                napraviFajl(knjige);
                Toast.makeText(context,"Knjiga je dodata u korpu.",Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(context,"Knjiga je vec dodata u korpu.",Toast.LENGTH_LONG).show();
            }

        } catch (IOException ex) {
            Logger.getLogger(Korpa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void obrisiKnjigu(Knjiga knjiga) {
        try {
            ArrayList<Knjiga> knjige = getNiz();
            boolean provera = proveri(knjiga);

            if (provera == true) {
                knjige.remove(knjiga);
                napraviFajl(knjige);
                System.out.println("knjiga je obrisana.");
                Toast.makeText(context,"Knjiga je obrisana.",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(context,"Knjiga ne postoji u korpi.",Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception e) {
            System.out.println("greska: " + e.getMessage());
        }
    }
    public int getSizeOfKorpa () {
        int duzina = getNiz().size();
        return duzina;
    }
    public double getUkupnaCenaKnjiga() {
        ArrayList<Knjiga> knjige = getNiz();
        double ukupno = 0.00;
        for (Knjiga knjiga:knjige) {
            if(!knjiga.getCena().startsWith("Besplatno")) {
                ukupno+= Double.parseDouble(knjiga.getCena());
            }
        }

        return ukupno;
    }
    public void setStanjeKorpa () {

        TextView stanjeKorpaView = (TextView) ((Activity) context).findViewById(R.id.stanjeKorpa);
        String stanjeKorpa = String.format(Locale.US,"Korpa(%s): %s rsd.",String.valueOf(getSizeOfKorpa()),String.valueOf(getUkupnaCenaKnjiga()));
        stanjeKorpaView.setText(stanjeKorpa);
    }

    public void setJustCena () {
        TextView justCenaView = (TextView) ((Activity) context).findViewById(R.id.justCena);
        String stanjeKorpa = String.format(Locale.US,"%s rsd.",String.valueOf(getUkupnaCenaKnjiga()));
        justCenaView.setText(stanjeKorpa);
    }


}