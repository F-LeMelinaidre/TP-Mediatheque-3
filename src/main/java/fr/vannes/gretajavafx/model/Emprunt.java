package fr.vannes.gretajavafx.model;

import java.time.LocalDate;

public class Emprunt {
    private int emprunteur_id;
    private int media_id;
    private LocalDate date_emprunt;
    private LocalDate date_retour;

    public Emprunt(int emprunteur_id, int media_id, LocalDate date_emprunt, LocalDate date_retour) {
        this.emprunteur_id = emprunteur_id;
        this.media_id = media_id;
        this.date_emprunt = date_emprunt;
        this.date_retour = date_retour;
    }

    public int getEmprunteur_id() {
        return emprunteur_id;
    }

    public void setEmprunteur_id(int emprunteur_id) {
        this.emprunteur_id = emprunteur_id;
    }

    public int getMedia_id() {
        return media_id;
    }

    public void setMedia_id(int media_id) {
        this.media_id = media_id;
    }

    public LocalDate getDate_emprunt() {
        return date_emprunt;
    }

    public void setDate_emprunt(LocalDate date_emprunt) {
        this.date_emprunt = date_emprunt;
    }

    public LocalDate getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(LocalDate date_retour) {
        this.date_retour = date_retour;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Emprunt{");
        sb.append("emprunteur_id=").append(emprunteur_id);
        sb.append(", media_id=").append(media_id);
        sb.append(", date_emprunt=").append(date_emprunt);
        sb.append(", date_retour=").append(date_retour);
        sb.append('}');
        return sb.toString();
    }
}
