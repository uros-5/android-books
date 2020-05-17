/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.knjizara.model;

import java.io.Serializable;

/**
 *
 * @author Uros
 */
public class Korisnik  implements Serializable {
    private static final long serialVersionUID = 911175;
    public String ime;
    public String prezime;
    public String ulicaIBroj;
    public String email;
    public String kartica;
    public String brojPoste;
    public String grad;

    public Korisnik(String ime, String prezime, String ulicaIBroj, String email, String kartica, String brojPoste,String grad) {
        this.ime = ime;
        this.prezime = prezime;
        this.ulicaIBroj = ulicaIBroj;
        this.email = email;
        this.kartica = kartica;
        this.brojPoste = brojPoste;
        this.grad = grad;
    }

    public Korisnik() {
    }
    
    @Override
    public boolean equals(Object o) {
        if(o instanceof Korisnik) {

            if(o.toString().equals(toString())) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getUlicaIBroj() {
        return ulicaIBroj;
    }

    public String getEmail() {
        return email;
    }

    public String getKartica() {
        return kartica;
    }

    public String getBrojPoste() {
        return brojPoste;
    }

    public String toString () {
        return ime;
    }
    
}
