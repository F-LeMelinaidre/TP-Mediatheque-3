package fr.vannes.gretajavafx.dao.emprunt;


import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.Emprunt;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAOImpl implements EmpruntDAO {

    private DAOFactory daoFactory;

    public EmpruntDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public void ajouterEmprunt(Emprunt emprunt) throws Exception {
        String query = "INSERT INTO emprunt (emprunteur_id, media_id, date_emprunt, date_retour) VALUES (?, ?, ?, ?)";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, emprunt.getEmprunteur_id());
            statement.setInt(2, emprunt.getMedia_id());
            statement.setDate(3, Date.valueOf(emprunt.getDate_emprunt()));
            statement.setDate(4, Date.valueOf(emprunt.getDate_retour()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erreur lors de l'ajout de l'emprunt : " + e.getMessage());
        }
    }

    @Override
    public void mettreAJourEmprunt(Emprunt emprunt) throws Exception {
        String query = "UPDATE emprunt SET date_emprunt = ?, date_retour = ? WHERE emprunteur_id = ? AND media_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDate(1, Date.valueOf(emprunt.getDate_emprunt()));
            statement.setDate(2, Date.valueOf(emprunt.getDate_retour()));
            statement.setInt(3, emprunt.getEmprunteur_id());
            statement.setInt(4, emprunt.getMedia_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la mise à jour de l'emprunt : " + e.getMessage());
        }
    }

    @Override
    public void supprimerEmprunt(int emprunteur_id, int media_id) throws Exception {
        String query = "DELETE FROM emprunt WHERE emprunteur_id = ? AND media_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, emprunteur_id);
            statement.setInt(2, media_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la suppression de l'emprunt : " + e.getMessage());
        }
    }

    @Override
    public Emprunt getEmpruntById(int emprunteur_id, int media_id) throws Exception {
        String query = "SELECT * FROM emprunt WHERE emprunteur_id = ? AND media_id = ?";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, emprunteur_id);
            statement.setInt(2, media_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LocalDate date_emprunt = resultSet.getDate("date_emprunt").toLocalDate();
                    LocalDate date_retour = resultSet.getDate("date_retour").toLocalDate();
                    return new Emprunt(emprunteur_id, media_id, date_emprunt, date_retour);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération de l'emprunt : " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Emprunt> getTousLesEmprunts() throws Exception {
        List<Emprunt> emprunts = new ArrayList<>();
        String query = "SELECT * FROM emprunt";
        try (Connection connection = daoFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int emprunteur_id = resultSet.getInt("emprunteur_id");
                int media_id = resultSet.getInt("media_id");
                LocalDate date_emprunt = resultSet.getDate("date_emprunt").toLocalDate();
                LocalDate date_retour = resultSet.getDate("date_retour").toLocalDate();

                Emprunt emprunt = new Emprunt(emprunteur_id, media_id, date_emprunt, date_retour);
                emprunts.add(emprunt);
            }
        } catch (SQLException e) {
            throw new Exception("Erreur lors de la récupération des emprunts : " + e.getMessage());
        }
        return emprunts;
    }
}
