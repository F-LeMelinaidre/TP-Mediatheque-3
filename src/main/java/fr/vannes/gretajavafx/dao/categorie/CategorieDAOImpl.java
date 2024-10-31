package fr.vannes.gretajavafx.dao.categorie;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.Categorie;
import fr.vannes.gretajavafx.model.SousCategorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static CategorieDAOImpl get_instance(DAOFactory df) {
        if (_instance == null) {
            try {
                _df       = df;
                _instance = new CategorieDAOImpl(_df);
            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                // Lancer une RuntimeException
                throw new RuntimeException("Erreur lors de l'initialisation de MediaDAOImpl", e);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur inconnue lors de l'initialisation de MediaDAOImpl", e);
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
    public ArrayList findAll(boolean sousCategories) {
        ArrayList<Categorie> categorieList = new ArrayList<>();
        ArrayList<Integer> categorieIds = new ArrayList<>();
        Categorie categorie = null;
        ResultSet rs = null;

        String select = "SELECT c.categorie_id as categorieId, c.label as categorieLabel";
        String from = " FROM categorie as c ";

        if (sousCategories) {
            select += ", sc.sous_categorie_id as sousCategorieId, sc.label as sousCategorieLabel ";
            from += " INNER JOIN category_has_subcategory cs ON cs.categorie_id = c.categorie_id ";
            from += " INNER JOIN sous_categorie as sc ON sc.sous_categorie_id = cs.sous_categorie_id ";
        }

        String sql = select + from;

        try (PreparedStatement ps = _conn.prepareStatement(sql)) {

            rs = ps.executeQuery();

            while (rs.next()) {
                int categorieId = rs.getInt("categorieId");
                String categorieLabel = rs.getString("categorieLabel");

                if(!categorieIds.contains(categorieId)) {
                    categorie = new Categorie(categorieId, categorieLabel);
                    categorieList.add(categorie);
                    categorieIds.add(categorieId);
                }

                if(sousCategories) {
                    int sousCategorieId = rs.getInt("sousCategorieId");
                    String sousCategorieLabel = rs.getString("sousCategorieLabel");
                    SousCategorie sousCategorie = new SousCategorie(sousCategorieId, sousCategorieLabel);
                    categorie.addSousCategorie(sousCategorie);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(rs);
            _df.closeConnection();
        }

        return categorieList;
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