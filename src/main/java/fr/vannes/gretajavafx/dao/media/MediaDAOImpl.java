package fr.vannes.gretajavafx.dao.media;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.Media;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MediaDAOImpl implements MediaDAO {

    private static final String MEDIA_ID = "media_id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String CATEGORY_ID = "category_id";
    private static final String CATEGORY_LABEL = "category_label";
    private static final String SUBCATEGORY_ID = "subcategory_id";
    private static final String SUBCATEGORY_LABEL = "subcategory_label";

    private static MediaDAOImpl _instance = null;
    private static Connection _conn = null;
    private static DAOFactory _df;

    private static final String INSERT = "INSERT INTO media (media_id, title, description, category_id, subcategory_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String READONE = "SELECT m.media_id, m.title, m.description, " +
            "c.category_id AS category_id, c.label AS category_label, " +
            "sc.subcategory_id AS subcategory_id, sc.label AS subcategory_label " +
            "FROM media AS m " +
            "LEFT JOIN category c ON m.category_id = c.category_id " +
            "LEFT JOIN subcategory sc ON m.subcategory_id = sc.subcategory_id  " +
            "WHERE m.media_id = ?";

    private static final String READALL = "SELECT m.media_id, m.title, m.description, " +
            "c.category_id AS category_id, c.label AS category_label, " +
            "sc.subcategory_id AS subcategory_id, sc.label AS subcategory_label " +
            "LEFT JOIN category c ON m.category_id = c.category_id " +
            "LEFT JOIN subcategory sc ON m.subcategory_id = sc.subcategory_id " +
            "ORDER BY m.media_id DESC";

    private static final String UPDATE = "UPDATE media SET title = ?, description = ?, category_id = ?, subcategory_id = ? WHERE media_id = ?";
    ;
    private static final String DELETE = "DELETE FROM media WHERE media_id = ?";

    private MediaDAOImpl(DAOFactory df) throws SQLException {
        try {
            _conn = df.getConnection();
        } catch (SQLException e) {
            throw new SQLException("Probleme driver manager ou acces bdd !!");
        }

    }

    public static MediaDAOImpl get_instance() {
        if (_instance == null) {
            try {

                _df       = DAOFactory.getInstance();
                _instance = new MediaDAOImpl(_df);

            } catch (SQLException e) {
                System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return _instance;
    }

    @Override
    public int count() {
        String sql = "SELECT COUNT(*) FROM media";
        int count = 0;
        try {
            PreparedStatement ps = _conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Boolean create(Object o) {
        int result = 0;
        PreparedStatement ps = null;

        try {
            ps = _conn.prepareStatement(INSERT);
            ps.setString(1, media.getMedia_id());
            ps.setString(2, media.getTitre());
            ps.setString(3, media.getDescription());
            ps.setInt(4, media.getCategorie().getId());
            ps.setInt(5, media.getSousCategorie().getId());

            result = ps.executeUpdate();

            ps.close();
            _df.closeConnection();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            _df.closeConnection();
        }
        return (result > 0);
    }

    /**
     * Retourne un tableau de compteur de media par année
     *
     * @return
     */
    public Map<Integer, Integer> countByYear() {
        Map<Integer, Integer> counters = new HashMap<Integer, Integer>();

        String sql = "SELECT SUBSTRING(media_id, 1, 4) AS year, COUNT(*) AS nb_media " +
                "FROM media " +
                "GROUP BY year " +
                "ORDER BY year";

        try {
            PreparedStatement ps = _conn.prepareStatement(sql);
            ResultSet result = ps.executeQuery();

            while (result.next()) {
                int year = Integer.parseInt(result.getString("year"));
                int nombreDeMedia = result.getInt("nb_media");
                counters.put(year, nombreDeMedia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counters;
    }

    @Override
    public Media findById(String id) {
        Media media = null;
        try {
            PreparedStatement ps = _conn.prepareStatement(READONE);
            ps.setString(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String media_id = rs.getString(MEDIA_ID);
                String title = rs.getString(TITLE);
                String description = rs.getString(DESCRIPTION);

                media = new Media(media_id, title, description);
                media.setCategorie(this.getCategorie(rs));
                media.setSousCategorie(this.getSousCategorie(rs));
            }


            ps.close();
            _df.closeConnection();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return media;
    }

    @Override
    public ArrayList<Media> findAll() {
        ArrayList<Media> mediaList = new ArrayList<>();

        try {
            PreparedStatement ps = _conn.prepareStatement(READALL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String media_id = rs.getString(MEDIA_ID);
                String title = rs.getString(TITLE);
                String description = rs.getString(DESCRIPTION);

                Media media = new Media(media_id, title, description);

                media.setCategorie(this.getCategorie(rs));
                media.setSousCategorie(this.getSousCategorie(rs));

                mediaList.add(media);

            }

            ps.close();
            _df.closeConnection();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediaList;
    }

    @Override
    public Object update(Object media) {
        try {

            PreparedStatement ps = _conn.prepareStatement(UPDATE);
            ps.setString(1, media.getTitre());
            ps.setString(2, media.getDescription());
            ps.setInt(3, media.getCategorie().getIdCategory());
            ps.setInt(4, media.getSubcategory().getIdSubcategory());
            ps.setString(5, media.getMedia_id());

            ps.close();
            _df.closeConnection();

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return media;
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(String media_id) {
        Boolean result = false;
        try {

            PreparedStatement ps = _conn.prepareStatement(DELETE);
            ps.setString(1, media_id);

            int rowsAffected = ps.executeUpdate();

            result = rowsAffected > 0;

            ps.close();
            _df.closeConnection();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Categorie getCategorie(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public SousCategorie getSousCategorie(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public Category getCategory(ResultSet rs) throws SQLException {
        Category category = null;
        if (rs.getInt(CATEGORY_ID) > 0) {
            int category_id = rs.getInt(CATEGORY_ID);
            String category_label = rs.getString(CATEGORY_LABEL);

            category = new Category(category_id, category_label);

        }
        return category;
    }

    @Override
    public SousCategory getSubCategory(ResultSet rs) throws SQLException {
        SubCategory sub_category = null;

        if (rs.getInt(SUBCATEGORY_ID) > 0) {
            int subcategory_id = rs.getInt(SUBCATEGORY_ID);
            String subcategory_label = rs.getString(SUBCATEGORY_LABEL);
            sub_category = new SubCategory(subcategory_id, subcategory_label);
        }
        return sub_category;
    }
}

