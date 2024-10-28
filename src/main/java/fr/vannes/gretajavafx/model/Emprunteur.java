package fr.vannes.gretajavafx.model;

import javax.print.attribute.standard.Media;
import java.time.LocalDate;
import java.util.ArrayList;

public class Emprunteur {
    private int emprunteurId;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private ArrayList<Media> medias = new ArrayList<>();


    public Emprunteur(int emprunteurId, String nom, String prenom, LocalDate dateNaissance) {
        this.emprunteurId = emprunteurId;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
    }

    public Emprunteur(int emprunteur_id, String nom, String prenom, LocalDate date_naissance, ArrayList<Media> medias) {
        this.emprunteurId = emprunteur_id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = date_naissance;
        this.medias = medias;
    }

    public Emprunteur(String nom, String prenom, LocalDate date_naissance, ArrayList<Media> medias) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = date_naissance;
        this.medias = medias;
    }

    public int getEmprunteurId() {
        return this.emprunteurId;
    }

    public void setEmprunteurId(int emprunteurId) {
        this.emprunteurId = emprunteurId;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public ArrayList<Media> getMedias() {
        return this.medias;
    }

    public void setMedias(ArrayList<Media> medias) {
        this.medias = medias;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Emprunteur{");
        sb.append("emprunteur_id=").append(this.emprunteurId);
        sb.append(", nom='").append(this.nom).append('\'');
        sb.append(", prenom='").append(this.prenom).append('\'');
        sb.append(", date_naissance=").append(this.dateNaissance);
        sb.append(", medias=").append(this.medias);
        sb.append('}');
        return sb.toString();
    }
}
