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

    private static final String SUBCATEGORY_ID = "subcategory_id";
    private static final String LABEL = "label";

    private String INSERT = "INSERT INTO SousCategorie (label) VALUES (?)";
    private String READONE =
            "SELECT sc.subcategory_id, sc.label, " + "FROM subcategory sc " + "WHERE subcategory_id = ?";
    private String READALL = "SELECT sc.subcategory_id, sc.label " + "FROM subcategory sc " + "ORDER BY sc.label ASC";

    private String READALLBYCATEGORY = "SELECT sc.subcategory_id, sc.label " +
                                       "FROM subcategory sc " +
                                       "INNER JOIN category_has_subcategory chs ON sc.subcategory_id = chs.subcategory_id " +
                                       "INNER JOIN category c ON chs.category_id = c.category_id " +
                                       "WHERE c.category_id = ? " +
                                       "ORDER BY sc.label ASC";

    private String UPDATE = "UPDATE subcategory SET label = ? WHERE subcategory_id = ?";
    ;
    private String DELETE = "DELETE FROM subcategory WHERE subcategory_id = ?";

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

        try {
            PreparedStatement ps = _conn.prepareStatement(READALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int sousCategorieId = rs.getInt(SUBCATEGORY_ID);
                String label = rs.getString(LABEL);

                SousCategorie sousCategorie = new SousCategorie(sousCategorieId, label);


                subCategoriesList.add(sousCategorie);

            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subCategoriesList;
    }

    @Override
    public ArrayList<SousCategorie> findAllByCategory(Categorie categorie) {
        ArrayList<SousCategorie> subCategoriesList = new ArrayList<>();

        try {
            System.out.println("try SubCategoryDAO.findAllByCategoryId(int)");
            PreparedStatement ps = _conn.prepareStatement(READALLBYCATEGORY);
            ps.setInt(1, categorie.getIdCategorie());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int sousCategorieId = rs.getInt(SUBCATEGORY_ID);
                String label = rs.getString(LABEL);

                SousCategorie sousCategorie = new SousCategorie(sousCategorieId, label);


                subCategoriesList.add(sousCategorie);

            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
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
