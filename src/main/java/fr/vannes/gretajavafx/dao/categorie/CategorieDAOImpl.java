package fr.vannes.gretajavafx.dao.categorie;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.Categorie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static fr.vannes.gretajavafx.dao.Config.*;

public class CategorieDAOImpl implements CategorieDAO<Categorie> {

    private static CategorieDAOImpl _instance = null;
    private static Connection _conn = null;
    private static DAOFactory _df;

    private CategorieDAOImpl(DAOFactory df) throws SQLException {
        try {
            _conn = df.getConnection();
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
