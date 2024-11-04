package fr.vannes.gretajavafx.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprunt
{
    private int emprunteurId;
    private String mediaId;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
    private Emprunteur emprunteur;
    private Media media;

    public Emprunt(int emprunteur_id, String mediaId, LocalDate dateEmprunt)
    {
        this.emprunteurId = emprunteur_id;
        this.mediaId = mediaId;
        this.dateEmprunt = dateEmprunt;
    }

    public Emprunt(int emprunteur_id, String mediaId, LocalDate dateEmprunt, LocalDate dateRetour)
    {
        this.emprunteurId = emprunteur_id;
        this.mediaId = mediaId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Emprunt(int emprunteurId, String mediaId, LocalDate dateEmprunt, LocalDate dateRetour, Media media)
    {
        this.emprunteurId = emprunteurId;
        this.mediaId = mediaId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.emprunteur = emprunteur;
        this.media = media;
    }

    public Emprunt(int emprunteurId, String mediaId, LocalDate dateEmprunt, LocalDate dateRetour, Emprunteur emprunteur,
                   Media media)
    {
        this.emprunteurId = emprunteurId;
        this.mediaId = mediaId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.emprunteur = emprunteur;
        this.media = media;
    }

    public int getEmprunteurId()
    {
        return emprunteurId;
    }

    public void setEmprunteurId(int emprunteurId)
    {
        this.emprunteurId = emprunteurId;
    }

    public Emprunteur getEmprunteur()
    {
        return this.emprunteur;
    }

    public void setEmprunteur(Emprunteur emprunteur)
    {
        this.emprunteur = emprunteur;
    }

    public String getMediaId()
    {
        return mediaId;
    }

    public void setMediaId(String mediaId)
    {
        this.mediaId = mediaId;
    }

    public Media getMedia()
    {
        return this.media;
    }

    public void setMedia(Media media)
    {
        this.media = media;
    }

    public LocalDate getDateEmprunt()
    {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt)
    {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour()
    {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour)
    {
        this.dateRetour = dateRetour;
    }

    private String convertDateFr(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer(media.getMediaId());
        sb.append(" - ").append(media.getCategorie().getLabel());
        sb.append(" - Date d'emprunt : ").append(this.convertDateFr(dateEmprunt));
        if (dateRetour != null) {
            sb.append(" - Date de retour : ").append(this.convertDateFr(dateRetour));
        }
        return sb.toString();
    }
}
