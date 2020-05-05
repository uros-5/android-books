package com.example.knjizara;

import java.io.Serializable;

/**
 *
 * @author Uros
 */
public class Knjiga  implements Serializable{
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


    public String getNaslov() {
        return naslov;
    }

    public String getAutor() {
        return autor;
    }

    public String getCena() {
        return cena;
    }
}
