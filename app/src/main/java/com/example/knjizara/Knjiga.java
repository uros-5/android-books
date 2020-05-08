package com.example.knjizara;

import java.io.Serializable;

/**
 *
 * @author Uros
 */
public class Knjiga  implements Serializable{
    static final long serialVersionUID =-1868148438490984265L;
    String isbn;
    String strana;
    String godinaIzdanja;
    String cena;
    String naslov;
    String kategorija;

    String autor;
    String izdavac;


    public Knjiga(String isbn, String strana, String godinaIzdanja, String cena, String naslov, String kategorija, String autor, String izdavac) {
        this.isbn = isbn;
        this.strana = strana;
        this.godinaIzdanja = godinaIzdanja;
        this.cena = cena;
        this.naslov = naslov;
        this.kategorija = kategorija;

        this.autor = autor;
        this.izdavac = izdavac;
    }
    public Knjiga(String naslov,String autor,String cena) {
        this.naslov = naslov;
        this.autor = autor;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return isbn;
//        return "Knjiga{" + "isbn=" + isbn + '}';
    }


    public String getIsbn() {
        return isbn;
    }

    public String getStrana() {
        return strana;
    }

    public String getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public String getCena() {
        return cena;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getKategorija() {
        return kategorija;
    }

    public String getAutor() {
        return autor;
    }

    public String getIzdavac() {
        return izdavac;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Knjiga) {

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



//    public String getStrana() { return strana; }
//
//    public String getGodinaIzdanja() {
//        return godinaIzdanja;
//    }
//
//    public String getKategorija() {
//        return kategorija;
//    }
//
//    public String getIzdavac() {
//        return izdavac;
//    }
}
