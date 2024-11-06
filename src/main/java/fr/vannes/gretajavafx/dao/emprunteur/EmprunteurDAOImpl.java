package fr.vannes.gretajavafx.dao.emprunteur;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprunteurDAOImpl implements EmprunteurDAO
{

    private static EmprunteurDAOImpl _instance = null;
    private static Connection _conn = null;
    private static DAOFactory _df;

    private EmprunteurDAOImpl(DAOFactory df) throws SQLException
    {
        try {
            _conn = df.getConnection();
        } catch (SQLException e) {
            throw new SQLException("Probleme driver manager ou acces bdd !!");
        }
    }

    public static EmprunteurDAOImpl get_instance(DAOFactory df)
    {
        if (_instance == null) {
            try {
                _df = df;
                _instance = new EmprunteurDAOImpl(_df);
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

    public void ajouterEmprunteur(Emprunteur emprunteur)
    {
        String sql = "INSERT INTO emprunteur (nom, prenom, date_naissance) VALUES (?, ?, ?)";
        ResultSet generatedKeys = null;

        try (PreparedStatement statement = _conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, emprunteur.getNom());
            statement.setString(2, emprunteur.getPrenom());
            statement.setDate(3, Date.valueOf(emprunteur.getDateNaissance()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try {
                    generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        emprunteur.setEmprunteurId(generatedKeys.getInt(1));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(generatedKeys);
            _df.closeConnection();
        }
    }

    //TODO faire un getter Emprunteur => Emprunt (Media Categorie Sous-Catégorie) non retourné
    public Emprunteur getEmprunteurById(int id, boolean emprunt)
    {
        Emprunteur emprunteur = null;
        ResultSet resultSet = null;

        String select = "SELECT e.emprunteur_id, e.nom, e.prenom, e.date_naissance ";
        String from = "FROM emprunteur e ";
        String where = "WHERE e.emprunteur_id = ?";

        if (emprunt) {
            select += ", ep.media_id, ep.date_emprunt, ep.date_retour, " +
                      "m.titre, m.description, " +
                      "m.categorie_id, m.sous_categorie_id, c.label as categorieLabel, sc.label as sousCategorieLabel ";
            from += "INNER JOIN emprunt ep ON e.emprunteur_id = ep.emprunteur_id ";
            from += "INNER JOIN media m ON ep.media_id = m.media_id ";
            from += "INNER JOIN categorie c ON m.categorie_id = c.categorie_id ";
            from += "INNER JOIN sous_categorie sc ON m.sous_categorie_id = sc.sous_categorie_id ";
        }

        String sql = select + from + where;

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                LocalDate dateNaissance = resultSet.getDate("date_naissance").toLocalDate();

                emprunteur = new Emprunteur(id, nom, prenom, dateNaissance);

                if (emprunt) {
                    do {
                        this.getResultSetEmprunt(emprunteur, resultSet);
                    } while (resultSet.next());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(resultSet);
            _df.closeConnection();
        }

        return emprunteur;
    }

    public List<Emprunteur> getAllEmprunteurs()
    {
        List<Emprunteur> emprunteurs = new ArrayList<>();
        String sql = "SELECT * FROM emprunteur";
        ResultSet resultSet = null;

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("emprunteur_id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                LocalDate dateNaissance = resultSet.getDate("date_naissance").toLocalDate();

                Emprunteur emprunteur = new Emprunteur(id, nom, prenom, dateNaissance);
                emprunteurs.add(emprunteur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(resultSet);
            _df.closeConnection();
        }

        return emprunteurs;
    }

    public void updateEmprunteur(Emprunteur emprunteur)
    {
        String sql = "UPDATE emprunteur SET nom = ?, prenom = ?, date_naissance = ? WHERE emprunteur_id = ?";

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            statement.setString(1, emprunteur.getNom());
            statement.setString(2, emprunteur.getPrenom());
            statement.setDate(3, Date.valueOf(emprunteur.getDateNaissance()));
            statement.setInt(4, emprunteur.getEmprunteurId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeConnection();
        }
    }

    public void supprimerEmprunteur(int id)
    {
        String sql = "DELETE FROM emprunteur WHERE emprunteur_id = ?";

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeConnection();
        }
    }

    private void getResultSetEmprunt(Emprunteur emprunteur, ResultSet resultSet) throws SQLException
    {
        String mediaId = resultSet.getString("media_id");
        String titre = resultSet.getString("titre");
        String description = resultSet.getString("description");

        LocalDate dateEmprunt = resultSet.getDate("date_emprunt").toLocalDate();
        LocalDate dateRetour = (resultSet.getDate("date_retour") != null)? resultSet.getDate("date_retour").toLocalDate() : null;

        int categorieId = resultSet.getInt("categorie_id");
        String categorieLabel = resultSet.getString("categorieLabel");
        int sousCategorieId = resultSet.getInt("sous_categorie_id");
        String sousCategorieLabel = resultSet.getString("sousCategorieLabel");

        Categorie categorie = new Categorie(categorieId, categorieLabel);
        SousCategorie sousCategorie = new SousCategorie(sousCategorieId, sousCategorieLabel);

        Media media = new Media(mediaId, titre, description, categorie, sousCategorie);

        Emprunt emprunt = new Emprunt(emprunteur.getEmprunteurId(), mediaId, dateEmprunt, dateRetour, media);

        emprunteur.addEmprunt(emprunt);

    }
}