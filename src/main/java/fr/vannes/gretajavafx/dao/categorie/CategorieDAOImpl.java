package fr.vannes.gretajavafx.dao.categorie;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.Categorie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieDAOImpl implements CategorieDAO<Categorie> {

    private static CategorieDAOImpl _instance = null;
    private static Connection _conn = null;
    private static DAOFactory _df;
    private static final String CATEGORY_ID = "category_id";
    private static final String LABEL = "label";

    private String INSERT = "INSERT INTO category (label) VALUES (?)";
    private String READONE = "SELECT c.category_id, c.label, " +
                             "FROM category c " +
                             "WHERE category_id = ?";
    private String READALL = "SELECT c.category_id, c.label " +
                             "FROM category c " +
                             "ORDER BY c.label ASC";

    private String UPDATE = "UPDATE category SET label = ? WHERE category_id = ?";
    ;
    private String DELETE = "DELETE FROM category WHERE category_id = ?";

    private CategorieDAOImpl(DAOFactory df) throws SQLException {
        try {
            this._conn = df.getConnection();
        } catch (SQLException e) {
            throw new SQLException("Probleme driver manager ou acces bdd !!");
        }

    }

    public static CategorieDAOImpl get_instance() {
        if (_instance == null) {
            try {

                _df       = DAOFactory.getInstance();
                _instance = new CategorieDAOImpl(_df);

            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return _instance;
    }

    @Override
    public Boolean create(Categorie categorie) {
        return null;
    }

    @Override
    public Categorie findByID(int id) {
        return null;
    }

    @Override
    public ArrayList findAll() {
        return null;
    }

    @Override
    public Categorie update(Categorie categorie) {
        return null;
    }

    @Override
    public Boolean delete(Categorie categorie) {
        return null;
    }
}
