package fr.vannes.gretajavafx.model;

import java.time.LocalDate;

public class Emprunt {
    private int emprunteurId;
    private String mediaId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    private Emprunteur emprunteur;
    private Media media;

    public Emprunt(int emprunteur_id, String mediaId, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.emprunteurId = emprunteur_id;
        this.mediaId = mediaId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Emprunt(int emprunteurId, String mediaId, LocalDate dateEmprunt, LocalDate dateRetour, Emprunteur emprunteur, Media media) {
        this.emprunteurId = emprunteurId;
        this.mediaId = mediaId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.emprunteur = emprunteur;
        this.media = media;
    }

    public int getEmprunteurId() {
        return emprunteurId;
    }

    public void setEmprunteurId(int emprunteurId) {
        this.emprunteurId = emprunteurId;
    }

    public Emprunteur getEmprunteur() {
        return this.emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur) {
        this.emprunteur = emprunteur;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public Media getMedia() {
        return this.media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Emprunt{");
        sb.append("emprunteurId=").append(emprunteurId);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", dateEmprunt=").append(dateEmprunt);
        sb.append(", dateRetour=").append(dateRetour);
        sb.append('}');
        return sb.toString();
    }
}
