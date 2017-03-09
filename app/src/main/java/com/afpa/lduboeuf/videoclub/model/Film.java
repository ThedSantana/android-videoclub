package com.afpa.lduboeuf.videoclub.model;

/**
 * Created by ldf on 13/10/2015.
 */
public class Film {

    private int idFilm;
    private String titreFilm;
    private String imageURL;


    public Film(int idFilm, String titreFilm) {
        this.idFilm = idFilm;
        this.titreFilm = titreFilm;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitreFilm() {
        return titreFilm;
    }

    public void setTitreFilm(String titreFilm) {
        this.titreFilm = titreFilm;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
