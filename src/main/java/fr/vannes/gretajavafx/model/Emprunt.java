package fr.vannes.gretajavafx.model;

import java.time.LocalDate;

public class Emprunt {
    private int emprunteurId;
    private int mediaId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(int emprunteur_id, int mediaId, LocalDate dateEmprunt, LocalDate dateRetour) {
        this.emprunteurId = emprunteur_id;
        this.mediaId = mediaId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public int getEmprunteurId() {
        return emprunteurId;
    }

    public void setEmprunteurId(int emprunteurId) {
        this.emprunteurId = emprunteurId;
    }

    public int getMediaId() {
        return mediaId;
    }

    public void setMediaId(int mediaId) {
        this.mediaId = mediaId;
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
        sb.append("emprunteur_id=").append(emprunteurId);
        sb.append(", mediaId=").append(mediaId);
        sb.append(", dateEmprunt=").append(dateEmprunt);
        sb.append(", dateRetour=").append(dateRetour);
        sb.append('}');
        return sb.toString();
    }
}
