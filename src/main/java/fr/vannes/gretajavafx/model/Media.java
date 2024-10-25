/*
 * Copyright (c) 2024.
 * Frédéric Le Mélianidre
 * Formation CDA
 */

package fr.vannes.gretajavafx.model;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.dao.media.MediaDAOImpl;

import java.sql.SQLException;
import java.util.Map;

import static fr.vannes.gretajavafx.controller.Configuration.CURRENT_MONTH;
import static fr.vannes.gretajavafx.controller.Configuration.CURRENT_YEAR;

public class Media {

    private String mediaId;
    private String titre;
    private String description;
    private Categorie categorie;
    private SousCategorie sousCategorie;

    public static int totalMedia;
    public static Map<Integer, Integer> totalMediaByYear;


    /**
     * Constructeur pour l'ajout de média <br>
     * {@code id} généré avec {@link #generateId()}
     * @param titre {@code String} titre
     * @param description {@code String} description
     * @param categorie {@link Categorie} Model Categorie
     * @param sousCategorie {@link SousCategorie} Model Sous-Categorie
     */
    public Media(String titre, String description, Categorie categorie, SousCategorie sousCategorie) {
        this.titre       = titre;
        this.description = description;
        this.categorie     = categorie;
        this.sousCategorie = sousCategorie;
        this.initCounter();
    }

    /**
     * Constructeur pour les données issu de la base de données
     * @param idMedia {@code String} {@code media_id}
     * @param titre {@code String} {@code titre}
     * @param description {@code String} {@code description}
     */
    public Media(String idMedia, String titre, String description) {
        this.mediaId = idMedia;
        this.titre   = titre;
        this.description = description;
    }

    /**
     * Constructeur pour les données issu de la base de données avec l'objet {@link Categorie}
     * @param idMedia {@code String} {@code media_id}
     * @param titre {@code String} {@code titre}
     * @param description {@code String} {@code description}
     * @param categorie {@link Categorie}
     */
    public Media(String idMedia, String titre, String description, Categorie categorie) {
        this.mediaId = idMedia;
        this.titre   = titre;
        this.categorie   = categorie;
    }

    /**
     * Constructeur pour les données issu de la base de données avec l'objet {@link Categorie} {@link SousCategorie}
     * @param idMedia {@code String} {@code media_id}
     * @param titre {@code String} {@code titre}
     * @param description {@code String} {@code description}
     * @param categorie {@link Categorie}
     * @param sousCategorie {@link SousCategorie}
     */
    public Media(String idMedia, String titre, String description, Categorie categorie, SousCategorie sousCategorie) {
        this.mediaId = idMedia;
        this.titre   = titre;
        this.description = description;
        this.categorie     = categorie;
        this.sousCategorie = sousCategorie;
    }

    /**
     * Initialise le compteur de média <br>
     * {@code totalMedia}: total de l'essemeble des média en bd.
     * {@code totalMediaByYear}: {@code Map} des totaux des medias classés par années.
     */
    public void initCounter()
    {
        DAOFactory daoFactory = DAOFactory.getInstance();
        MediaDAOImpl dao = MediaDAOImpl.get_instance(daoFactory);
        totalMedia = dao.count();
        totalMediaByYear = dao.countByYear();
    }

    public String getMediaId() {
        return this.mediaId;
    }

    /**
     * Créé un id basé sur l année {@code CURRENT_YEAR}, le mois courant {@code CURRENT_MONTH}, et une reference
     * representant le nombre de media de l'année d'ajout.
     */
    public void generateId() {
        int currentYearCounter = (totalMediaByYear.get(CURRENT_YEAR) != null) ? totalMediaByYear.get(CURRENT_YEAR) + 1 : 1;
        String ref = String.format("%05d", currentYearCounter);

        this.mediaId = CURRENT_YEAR + "-" + CURRENT_MONTH + "-" + ref;
    }


    public static void updateCounters() {
        totalMedia++;
        int currentYearCounter = (totalMediaByYear.get(CURRENT_YEAR) != null) ? totalMediaByYear.get(CURRENT_YEAR) + 1 : 0;
        totalMediaByYear.put(CURRENT_YEAR, currentYearCounter + 1);
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getCategorieLabel() {
        return categorie.getLabel();
    }

    public SousCategorie getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(SousCategorie sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public String getSousCategorieLabel() {
        return sousCategorie.getLabel();
    }

    @Override
    public String toString() {
        return "id='" + mediaId + '\'' +
               "\n Titre:'" + titre + '\'' +
               "\n Description:'" + description + '\'' +
               "\n ___________________________________________________________________________" +
               "\n Categorie:" + categorie.getLabel() +
               "\n Sous-category:" + sousCategorie.getLabel() +
               "\n ___________________________________________________________________________\n";
    }
}