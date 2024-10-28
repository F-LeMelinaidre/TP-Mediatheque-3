package fr.vannes.gretajavafx.dao.emprunt;


import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAOImpl implements EmpruntDAO
{

    private static EmpruntDAOImpl _instance = null;
    private static Connection _conn = null;
    private static DAOFactory _df;

    private EmpruntDAOImpl(DAOFactory df) throws SQLException
    {
        try {
            _conn = df.getConnection();
        } catch (SQLException e) {
            throw new SQLException("Probleme driver manager ou acces bdd !!");
        }

    }

    public static EmpruntDAOImpl get_instance(DAOFactory df)
    {
        if (_instance == null) {
            try {
                _df = df;
                _instance = new EmpruntDAOImpl(_df);
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                throw new RuntimeException("Erreur lors de l'initialisation de MediaDAOImpl", e);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur inconnue lors de l'initialisation de MediaDAOImpl", e);
            }
        }
        return _instance;
    }

    @Override
    public void ajouterEmprunt(Emprunt emprunt) throws Exception
    {
        String sql = "INSERT INTO emprunt (emprunteur_id, media_id, date_emprunt, date_retour) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            statement.setInt(1, emprunt.getEmprunteurId());
            statement.setString(2, emprunt.getMediaId());
            statement.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            statement.setDate(4, Date.valueOf(emprunt.getDateRetour()));
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Erreur lors de l'ajout de l'emprunt : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeConnection();
        }
    }

    @Override
    public Emprunt getEmpruntById(int emprunteurId, String mediaId) throws Exception
    {
        String sql = "SELECT * FROM emprunt WHERE emprunteur_id = ? AND media_id = ?";
        ResultSet rs = null;

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            statement.setInt(1, emprunteurId);
            statement.setString(2, mediaId);
            rs = statement.executeQuery();

            if (rs.next()) {
                LocalDate dateEmprunt = rs.getDate("date_emprunt").toLocalDate();
                LocalDate dateRetour = rs.getDate("date_retour").toLocalDate();
                return new Emprunt(emprunteurId, mediaId, dateEmprunt, dateRetour);
            }

        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération de l'emprunt : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(rs);
            _df.closeConnection();
        }
        return null;
    }

    @Override
    public List<Emprunt> getTousLesEmprunts() throws Exception
    {
        List<Emprunt> emprunts = new ArrayList<>();
        String sql = "SELECT e.emprunteur_id, e.media_id, e.date_emprunt, e.date_retour, " +
                     "ep.nom, ep.prenom, ep.date_naissance, " +
                     "m.titre, m.description, " +
                     "c.categorie_id as categorieId, c.label as categorieLabel, " +
                     "sc.sous_categorie_id as sousCategorieId, sc.label as sousCategorieLabel " +
                     "FROM emprunt e " +
                     "INNER JOIN emprunteur ep on e.emprunteur_id = ep.emprunteur_id " +
                     "INNER JOIN media m on e.media_id = m.media_id " +
                     "INNER JOIN categorie c on m.categorie_id = c.categorie_id " +
                     "INNER JOIN sous_categorie sc on m.sous_categorie_id = sc.sous_categorie_id " +
                     "ORDER BY e.date_retour ASC";
        ResultSet rs = null;

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            rs = statement.executeQuery();
            while (rs.next()) {
                int emprunteurId = rs.getInt("emprunteur_id");
                String mediaId = rs.getString("media_id");
                LocalDate dateEmprunt = rs.getDate("date_emprunt").toLocalDate();
                LocalDate dateRetour = (rs.getDate("date_retour") != null)? rs.getDate("date_retour").toLocalDate() : null;
                Emprunteur emprunteur = this.getEmprunteur(rs);
                Media media = this.getMedia(rs, this.getCategorie(rs), this.getSousCategorie(rs));
                Emprunt emprunt = new Emprunt(emprunteurId, mediaId, dateEmprunt, dateRetour, emprunteur, media);
                emprunts.add(emprunt);
            }

        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération des emprunts : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(rs);
            _df.closeConnection();
        }
        return emprunts;
    }

    @Override
    public void mettreAJourEmprunt(Emprunt emprunt) throws Exception
    {
        String sql = "UPDATE emprunt SET date_emprunt = ?, date_retour = ? WHERE emprunteur_id = ? AND media_id = ?";

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            statement.setDate(1, Date.valueOf(emprunt.getDateEmprunt()));
            statement.setDate(2, Date.valueOf(emprunt.getDateRetour()));
            statement.setInt(3, emprunt.getEmprunteurId());
            statement.setString(4, emprunt.getMediaId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Erreur lors de la mise à jour de l'emprunt : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeConnection();
        }
    }

    @Override
    public void supprimerEmprunt(int emprunteurId, String mediaId) throws Exception
    {
        String sql = "DELETE FROM emprunt WHERE emprunteur_id = ? AND media_id = ?";

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            statement.setInt(1, emprunteurId);
            statement.setString(2, mediaId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Erreur lors de la suppression de l'emprunt : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeConnection();
        }
    }

    @Override
    public Emprunteur getEmprunteur(ResultSet rs) throws SQLException
    {
        Emprunteur emprunteur = null;

        if (rs.getInt("emprunteur_id") > 0) {
            int emprunteurId = rs.getInt("emprunteur_id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            LocalDate dateNaissance = rs.getDate("date_naissance").toLocalDate();

            emprunteur = new Emprunteur(emprunteurId, nom, prenom, dateNaissance);
        }

        return emprunteur;
    }

    @Override
    public Media getMedia(ResultSet rs, Categorie categorie, SousCategorie sousCategorie) throws SQLException
    {
        Media media = null;

        if (!rs.getString("media_id").isEmpty()) {
            String mediaId = rs.getString("media_id");
            String titre = rs.getString("titre");
            String description = rs.getString("description");

            media = new Media(mediaId, titre, description, categorie, sousCategorie);
        }


        return media;
    }
    @Override
    public Categorie getCategorie(ResultSet rs) throws SQLException
    {
        Categorie categorie = null;

        if (rs.getInt("categorieId") > 0) {
            int categorieId = rs.getInt("categorieId");
            String categorieLabel = rs.getString("categorieLabel");

            categorie = new Categorie(categorieId, categorieLabel);
        }

        return categorie;
    }

    @Override
    public SousCategorie getSousCategorie(ResultSet rs) throws SQLException
    {
        SousCategorie sousCategorie = null;

        if (rs.getInt("sousCategorieId") > 0) {
            int sousCategorieId = rs.getInt("sousCategorieId");
            String sousCategorieLabel = rs.getString("sousCategorieLabel");

            sousCategorie = new SousCategorie(sousCategorieId, sousCategorieLabel);
        }

        return sousCategorie;
    }

}
