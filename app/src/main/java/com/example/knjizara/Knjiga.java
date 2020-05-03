package com.example.knjizara;

public class Knjiga {
    private String naslov;
    private String autor;
    private String cena;

    public Knjiga(String naslov, String autor, String cena) {
        this.naslov = naslov;
        this.autor = autor;
        this.cena = cena;
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
