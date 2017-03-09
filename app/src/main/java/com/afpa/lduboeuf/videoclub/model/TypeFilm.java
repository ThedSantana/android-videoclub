package com.afpa.lduboeuf.videoclub.model;

/**
 * Created by ldf on 14/10/2015.
 */
public class TypeFilm {

    private String $code;
    private String $libelle;

    public TypeFilm(String $code, String $libelle) {
        this.$code = $code;
        this.$libelle = $libelle;
    }

    public String getCode() {
        return $code;
    }

    public void setCode(String $code) {
        this.$code = $code;
    }

    public String getLibelle() {
        return $libelle;
    }

    public void setLibelle(String $libelle) {
        this.$libelle = $libelle;
    }
}
