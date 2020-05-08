/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.knjizara;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
        this.context = ((AppCompatActivity) context);
    }

    public void otvoriZaCitanje () {
        try {
            this.in = new ObjectInputStream(
                    new FileInputStream("korpa.out"));
        }
        catch (Exception e) {
            System.out.println("Greska:" + e.getMessage());
        }
    }
    public ArrayList<Knjiga> getNiz () {
        try {
            otvoriZaCitanje();
            ArrayList<Knjiga> niz = (ArrayList<Knjiga>) in.readObject();
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
                    new FileOutputStream("korpa.out"));
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
                System.out.println("knjiga ne postoji i bice dodata.");
                knjige.add(knjiga);
                napraviFajl(knjige);
            }
            else {
                System.out.println("knjiga vec postoji.");
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
            }
            else {
                System.out.println("knjiga ne postoji u nizu.");
            }

        }
        catch (Exception e) {
            System.out.println("greska: " + e.getMessage());
        }
    }

}
