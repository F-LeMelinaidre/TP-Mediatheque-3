package fr.vannes.gretajavafx.dao;

/**
 * Cette Class contient plusieurs constantes / paramètres liés aux tables :
 * <ul>
 *     <li>Nom des Tables</li>
 *     <li>Nom des colonnes</li>
 * </ul>
 */
public class Config
{



    /**
     * {@code categorie} - Nom de la table categorie.
     */
    public static final String CATEGORIE = "categorie";

    /* Représente les colonnes de la table categorie : */
    /**
     * {@code categorie_id} - Index de la catégorie.
     */
    public static final String CATEGORIE_ID = "categorie_id";


    /**
     * {@code sous-categorie} - Nom de la table sous-categorie.
     */
    public static final String SOUS_CATEGORIE = "sous_categorie";

    /* Représente les colonnes de la table sous-categorie : */
    /**
     * {@code sous_categorie_id} - Index de la sous-catégorie.
     */
    public static final String SOUS_CATEGORIE_ID = "sous_categorie_id";
    /**
     * {@code label} - Label de la catégorie et de la sous-catégorie.
     */
    public static final String LABEL = "label";


    /**
     * {@code emprunteur} - Nom de la table emprunteur.
     */
    public static final String EMPRUNTEUR = "emprunteur";

    /* Représente les colonnes de la table emprunteur : */
    /**
     * {@code emprunteur_id} - Identifiant emprunteur.
     */
    public static final String EMPRUNTEUR_ID = "emprunteur_id";
    /**
     * {@code nom} - Nom de l'emprunteur.
     */
    public static final String NOM = "nom";
    /**
     * {@code prenom} - Prenom de l'emprunteur.
     */
    public static final String PRENOM = "prenom";
    /**
     * {@code date_naissance} - Date de naissance de l'emprunteur.
     */
    public static final String DATE_NAISSANCE = "date_naissance";



}
