/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.knjizara.viewmodel;

import android.content.Context;

import com.example.knjizara.model.Korisnik;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uros
 */
public class KorisnikInfo  {
    private static final long serialVersionUID = 911175;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    public Context context;
    
    public KorisnikInfo(Context context) {
        this.context = ((Context) context);

//        File file = new File("korisnik.out");
//        if(!file.exists()){
//            napraviFajl(null);
//        }
    }

    public boolean checkFile() {
        File file = new File(context.getFilesDir().getAbsolutePath()+"/korisnik.out");
        if(!file.exists()){
            System.out.println("NE POSTOJI");
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkKorisnik () {
        Korisnik korisnik = getKorisnik();
        if(korisnik.toString() != null || korisnik.toString() != "") {
            return true;
        }
        else {
            return false;
        }

    }
            
            
    public void otvoriZaCitanje () {
        try {
            this.in = new ObjectInputStream(
                    new FileInputStream(context.getFilesDir().getAbsolutePath()+"/korisnik.out"));
        }
        catch (Exception e) {
            System.out.println("Greska:" + e.getMessage());
        }
    }
    public void zatvoriZaCitanje () {
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Korpa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Korisnik getKorisnik () {
        try {
            otvoriZaCitanje();
            Korisnik korisnik = (Korisnik) in.readObject();
            zatvoriZaCitanje();
            return korisnik;
        } catch (IOException ex) {
            Logger.getLogger(Korpa.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Korpa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean proveri (Korisnik korisnik0) throws IOException {


        while(true) {
            try {
                //                in.readObject();
                Korisnik korisnik = getKorisnik();
                if(korisnik0.equals(korisnik)) {
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
    
    public void dodajKorisnika(Korisnik korisnik0) {
        try {
            Korisnik korisnik = getKorisnik();
            boolean provera = proveri(korisnik0);
            if (provera == false) {
                System.out.println("");
                napraviFajl(korisnik0);
                System.out.println("Korisnik je dodat out fajl.");
//                Toast.makeText(context,"Knjiga je dodata u korpu.",Toast.LENGTH_LONG).show();

            }
            else {
                System.out.println("Korisnik je vec odavno dodat.");
//                Toast.makeText(context,"Knjiga je vec dodata u korpu.",Toast.LENGTH_LONG).show();
            }

        } catch (IOException ex) {
            Logger.getLogger(Korpa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void napraviFajl (Korisnik korisnik0) {
        try {
            this.out = new ObjectOutputStream(
                    new FileOutputStream(context.getFilesDir().getAbsolutePath()+"/korisnik.out"));
            if(korisnik0 == null) {
               Korisnik korisnik = new Korisnik();
                out.writeObject(korisnik);
                this.out.close();
            }
            else {
                out.writeObject(korisnik0);
                this.out.close();
            }

        }
        catch (Exception e) {
            System.out.println("Greska:" + e.getMessage());
        }

    }

    public void setKorisnik(ArrayList<String> niz) {
        String ime = niz.get(0);
        String prezime = niz.get(1);
        String ulicaIBroj = niz.get(2);
        String grad = niz.get(3);
        String brojPoste = niz.get(4);
        String kartica = niz.get(5);
        String email = niz.get(6);

        Korisnik korisnik = new Korisnik(ime,prezime,ulicaIBroj,email,kartica,brojPoste,grad);
        napraviFajl(korisnik);
    }
}
