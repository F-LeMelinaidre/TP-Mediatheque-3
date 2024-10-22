package fr.vannes.gretajavafx.dao.emprunteur;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.Emprunteur;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmprunteurDAOImpl implements EmprunteurDAO {

    private DAOFactory daoFactory;

    public EmprunteurDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // Ajouter un nouvel emprunteur
    public void ajouterEmprunteur(Emprunteur emprunteur) {
        String sql = "INSERT INTO emprunteur (nom, prenom, date_naissance) VALUES (?, ?, ?)";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, emprunteur.getNom());
            statement.setString(2, emprunteur.getPrenom());
            statement.setDate(3, Date.valueOf(emprunteur.getDate_naissance()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        emprunteur.setEmprunteur_id(generatedKeys.getInt(1));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer un emprunteur par ID
    public Emprunteur getEmprunteurById(int id) {
        String sql = "SELECT * FROM emprunteur WHERE emprunteur_id = ?";
        Emprunteur emprunteur = null;

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");
                    LocalDate dateNaissance = resultSet.getDate("date_naissance").toLocalDate();

                    // On ne récupère pas encore les medias ici car il faudra une jointure avec la table Media.
                    emprunteur = new Emprunteur(id, nom, prenom, dateNaissance, new ArrayList<>());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunteur;
    }

    // Récupérer tous les emprunteurs
    public List<Emprunteur> getAllEmprunteurs() {
        List<Emprunteur> emprunteurs = new ArrayList<>();
        String sql = "SELECT * FROM emprunteur";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("emprunteur_id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                LocalDate dateNaissance = resultSet.getDate("date_naissance").toLocalDate();

                Emprunteur emprunteur = new Emprunteur(id, nom, prenom, dateNaissance, new ArrayList<>());
                emprunteurs.add(emprunteur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunteurs;
    }

    // Modifier un emprunteur
    public void updateEmprunteur(Emprunteur emprunteur) {
        String sql = "UPDATE emprunteur SET nom = ?, prenom = ?, date_naissance = ? WHERE emprunteur_id = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, emprunteur.getNom());
            statement.setString(2, emprunteur.getPrenom());
            statement.setDate(3, Date.valueOf(emprunteur.getDate_naissance()));
            statement.setInt(4, emprunteur.getEmprunteur_id());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un emprunteur par ID
    public void supprimerEmprunteur(int id) {
        String sql = "DELETE FROM emprunteur WHERE emprunteur_id = ?";

        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}