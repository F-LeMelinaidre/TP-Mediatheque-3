package fr.vannes.gretajavafx.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Emprunteur
{
    private int emprunteurId;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private Map<String, List<Emprunt>> empruntsMap;

    public Emprunteur(int emprunteurId, String nom, String prenom, LocalDate dateNaissance)
    {
        this.emprunteurId = emprunteurId;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;

        this.initEmpruntMap();
    }

    public Emprunteur(String nom, String prenom, LocalDate date_naissance)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = date_naissance;

        this.initEmpruntMap();
    }

    public int getEmprunteurId()
    {
        return this.emprunteurId;
    }

    public void setEmprunteurId(int emprunteurId)
    {
        this.emprunteurId = emprunteurId;
    }

    public String getNom()
    {
        return this.nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return this.prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance()
    {
        return this.dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance)
    {
        this.dateNaissance = dateNaissance;
    }

    private void initEmpruntMap()
    {
        this.empruntsMap = new HashMap<>();
        this.empruntsMap.put("nonRendus", new ArrayList<>());
        this.empruntsMap.put("rendus", new ArrayList<>());
    }

    public void addEmprunt(Emprunt emprunt) {
        if (emprunt.getDateRetour() == null) {
            this.empruntsMap.get("nonRendus").add(emprunt);
        } else {
            this.empruntsMap.get("rendus").add(emprunt);
        }
    }

    public List<Emprunt> getEmpruntsNonRendus() {
        return empruntsMap.get("nonRendus");
    }

    public List<Emprunt> getEmpruntsRendus() {
        return empruntsMap.get("rendus");
    }

    public List<Emprunt> getTousLesEmprunts() {
        List<Emprunt> tousLesEmprunts = new ArrayList<>(empruntsMap.get("nonRendus"));
        tousLesEmprunts.addAll(empruntsMap.get("rendus"));
        return tousLesEmprunts;
    }

    @Override
    public String toString()
    {
        final StringBuffer sb = new StringBuffer("Emprunteur{");
        sb.append("emprunteur_id=").append(this.emprunteurId);
        sb.append(", nom='").append(this.nom).append('\'');
        sb.append(", prenom='").append(this.prenom).append('\'');
        sb.append(", date_naissance=").append(this.dateNaissance);
        sb.append(", medias=").append(this.empruntsMap);
        sb.append('}');
        return sb.toString();
    }
}
