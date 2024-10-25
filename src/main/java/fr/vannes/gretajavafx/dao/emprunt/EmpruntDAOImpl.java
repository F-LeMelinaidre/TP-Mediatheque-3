package fr.vannes.gretajavafx.dao.emprunt;


import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.Emprunt;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static fr.vannes.gretajavafx.dao.Config.*;

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
            statement.setInt(2, emprunt.getMediaId());
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
    public Emprunt getEmpruntById(int emprunteurId, int mediaId) throws Exception
    {
        String sql = "SELECT * FROM emprunt WHERE emprunteur_id = ? AND media_id = ?";
        ResultSet rs = null;

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            statement.setInt(1, emprunteurId);
            statement.setInt(2, mediaId);
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
        String sql = "SELECT * FROM emprunt";
        ResultSet rs = null;

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            rs = statement.executeQuery();

            while (rs.next()) {
                int emprunteurId = rs.getInt("emprunteur_id");
                int mediaId = rs.getInt("media_id");
                LocalDate dateEmprunt = rs.getDate("date_emprunt").toLocalDate();
                LocalDate dateRetour = rs.getDate("date_retour").toLocalDate();

                Emprunt emprunt = new Emprunt(emprunteurId, mediaId, dateEmprunt, dateRetour);
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
            statement.setInt(4, emprunt.getMediaId());
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
    public void supprimerEmprunt(int emprunteurId, int mediaId) throws Exception
    {
        String sql = "DELETE FROM emprunt WHERE emprunteur_id = ? AND media_id = ?";

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            statement.setInt(1, emprunteurId);
            statement.setInt(2, mediaId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Erreur lors de la suppression de l'emprunt : " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeConnection();
        }
    }

}
