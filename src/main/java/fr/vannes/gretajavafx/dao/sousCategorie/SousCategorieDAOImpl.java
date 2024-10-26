package fr.vannes.gretajavafx.dao.sousCategorie;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.Categorie;
import fr.vannes.gretajavafx.model.SousCategorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SousCategorieDAOImpl implements SousCategorieDAO<SousCategorie> {


    private static SousCategorieDAOImpl instance = null;
    private static Connection _conn = null;
    private static DAOFactory _df;

    private SousCategorieDAOImpl(DAOFactory _df) throws SQLException {
        try {
            _conn = _df.getConnection();
        } catch (SQLException e) {
            throw new SQLException("Probleme driver manager ou acces bdd !!");
        }
    }

    public static SousCategorieDAOImpl getInstance() {
        if (instance == null) {
            try {
                _df = DAOFactory.getInstance();
                instance = new SousCategorieDAOImpl(_df);
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    @Override
    public Boolean create(SousCategorie object) {
        return null;
    }

    @Override
    public SousCategorie find(String id) {
        return null;
    }

    @Override
    public ArrayList<SousCategorie> findAll() {
        ArrayList<SousCategorie> subCategoriesList = new ArrayList<>();
        String sql = "SELECT sc.sous_categorie_id, sc.label " + "FROM sous_categorie sc " + "ORDER BY sc.label";
        ResultSet rs = null;

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            rs = statement.executeQuery();

            while (rs.next()) {
                int sousCategorieId = rs.getInt("sous_categorie_id");
                String label = rs.getString("label");

                SousCategorie sousCategorie = new SousCategorie(sousCategorieId, label);
                subCategoriesList.add(sousCategorie);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(rs);
            _df.closeConnection();
        }

        return subCategoriesList;
    }

    @Override
    public ArrayList<SousCategorie> findAllByCategory(Categorie categorie) {
        ArrayList<SousCategorie> subCategoriesList = new ArrayList<>();
        String sql = "SELECT sc.sous_categorie_id, sc.label " +
                     "FROM sous_categorie sc " +
                     "INNER JOIN category_has_subcategory chs ON sc.sous_categorie_id = chs.sous_categorie_id " +
                     "INNER JOIN categorie c ON chs.categorie_id = c.categorie_id " +
                     "WHERE c.categorie_id = ? " +
                     "ORDER BY sc.label";
        ResultSet rs = null;

        try (PreparedStatement statement = _conn.prepareStatement(sql)) {

            statement.setInt(1, categorie.getIdCategorie());
            rs = statement.executeQuery();

            while (rs.next()) {
                int sousCategorieId = rs.getInt("sous_categorie_id");
                String label = rs.getString("label");

                SousCategorie sousCategorie = new SousCategorie(sousCategorieId, label);
                subCategoriesList.add(sousCategorie);

            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(rs);
            _df.closeConnection();

        }

        return subCategoriesList;
    }

    @Override
    public SousCategorie update(SousCategorie object) {
        return null;
    }

    @Override
    public Boolean delete(SousCategorie object) {
        return null;
    }
}
