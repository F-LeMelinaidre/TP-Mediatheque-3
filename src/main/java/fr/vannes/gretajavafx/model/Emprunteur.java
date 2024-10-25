package fr.vannes.gretajavafx.model;

import javax.print.attribute.standard.Media;
import java.time.LocalDate;
import java.util.ArrayList;

public class Emprunteur {
    private int emprunteur_id;
    private String nom;
    private String prenom;
    private LocalDate date_naissance;
    private ArrayList<Media> medias = new ArrayList<>();

    public Emprunteur(int emprunteur_id, String nom, String prenom, LocalDate date_naissance, ArrayList<Media> medias) {
        this.emprunteur_id = emprunteur_id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.medias = medias;
    }

    public Emprunteur(String nom, String prenom, LocalDate date_naissance, ArrayList<Media> medias) {
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.medias = medias;
    }

    public int getEmprunteur_id() {
        return emprunteur_id;
    }

    public void setEmprunteur_id(int emprunteur_id) {
        this.emprunteur_id = emprunteur_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public ArrayList<Media> getMedias() {
        return medias;
    }

    public void setMedias(ArrayList<Media> medias) {
        this.medias = medias;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Emprunteur{");
        sb.append("emprunteur_id=").append(emprunteur_id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", date_naissance=").append(date_naissance);
        sb.append(", medias=").append(medias);
        sb.append('}');
        return sb.toString();
    }
}
