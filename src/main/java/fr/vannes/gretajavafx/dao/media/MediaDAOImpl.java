package fr.vannes.gretajavafx.dao.media;

import fr.vannes.gretajavafx.dao.DAOFactory;
import fr.vannes.gretajavafx.model.Categorie;
import fr.vannes.gretajavafx.model.Media;
import fr.vannes.gretajavafx.model.SousCategorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MediaDAOImpl implements MediaDAO<Media>
{

    private static MediaDAOImpl _instance = null;
    private static Connection _conn = null;
    private static DAOFactory _df;

    private static final String INSERT = "INSERT INTO media (media_id, titre, description, categorie_id, sous_categorie_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String READONE = "SELECT m.media_id, m.titre, m.description, " +
                                          "c.categorie_id AS categorie_id, c.label AS categorieLabel, " +
                                          "sc.sous_categorie_id AS sous_categorie_id, sc.label AS sousCategorieLabel " +
                                          "FROM media AS m " +
                                          "LEFT JOIN categorie c ON m.categorie_id = c.categorie_id " +
                                          "LEFT JOIN sous_categorie sc ON m.sous_categorie_id = sc.sous_categorie_id  " +
                                          "WHERE m.media_id = ?";

    private static final String READALL = "SELECT m.media_id, m.titre, m.description, " +
                                          "c.categorie_id AS categorie_id, c.label AS categorieLabel, " +
                                          "sc.sous_categorie_id AS sous_categorie_id, sc.label AS sousCategorieLabel " +
                                          "FROM media AS m " +
                                          "LEFT JOIN categorie c ON m.categorie_id = c.categorie_id " +
                                          "LEFT JOIN sous_categorie sc ON m.sous_categorie_id = sc.sous_categorie_id " +
                                          "ORDER BY m.media_id DESC";

    private static final String UPDATE = "UPDATE media SET titre = ?, description = ?, categorie_id = ?, sous_categorie_id = ? WHERE media_id = ?";
    ;
    private static final String DELETE = "DELETE FROM media WHERE media_id = ?";

    private MediaDAOImpl(DAOFactory df) throws SQLException
    {
        try {
            _conn = df.getConnection();
        } catch (SQLException e) {
            throw new SQLException("Probleme driver manager ou acces bdd !!");
        }
    }

    public static MediaDAOImpl get_instance(DAOFactory df)
    {
        if (_instance == null) {
            try {
                _df = df;
                _instance = new MediaDAOImpl(_df);
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
    public int count()
    {
        String sql = "SELECT COUNT(*) FROM media";
        int count = 0;
        ResultSet result = null;

        try (PreparedStatement ps = _conn.prepareStatement(sql)) {

            result = ps.executeQuery();

            if (result.next()) {
                count = result.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(result);
            _df.closeConnection();
        }

        return count;
    }

    /**
     * Retourne un tableau de compteur de media par année
     *
     * @return
     */
    public Map<Integer, Integer> countByYear()
    {
        Map<Integer, Integer> counters = new HashMap<>();
        String sql = "SELECT SUBSTRING(media_id, 1, 4) AS year, COUNT(*) AS nb_media FROM media GROUP BY year ORDER BY year";
        ResultSet result = null;

        try (PreparedStatement ps = _conn.prepareStatement(sql)) {

            result = ps.executeQuery();

            while (result.next()) {
                int year = Integer.parseInt(result.getString("year"));
                int nombreDeMedia = result.getInt("nb_media");
                counters.put(year, nombreDeMedia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(result);
            _df.closeConnection();
        }

        return counters;
    }

    @Override
    public Boolean create(Media media)
    {
        int result = 0;

        try (PreparedStatement ps = _conn.prepareStatement(INSERT)) {

            ps.setString(1, media.getMediaId());
            ps.setString(2, media.getTitre());
            ps.setString(3, media.getDescription());
            ps.setInt(4, media.getCategorie().getIdCategorie());
            ps.setInt(5, media.getSousCategorie().getIdSousCategorie());

            result = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s\n", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeConnection();
        }

        return (result > 0);
    }

    @Override
    public Media findById(String id) {
        Media media = null;
        ResultSet rs = null;

        try (PreparedStatement ps = _conn.prepareStatement(READONE)) {

            ps.setString(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                String media_id = rs.getString("media_id");
                String title = rs.getString("titre");
                String description = rs.getString("description");

                media = new Media(media_id, title, description);
                media.setCategorie(this.getCategorie(rs));
                media.setSousCategorie(this.getSousCategorie(rs));
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(rs);
            _df.closeConnection();
        }

        return media;
    }

    @Override
    public ArrayList<Media> findAll() {
        ArrayList<Media> mediaList = new ArrayList<>();
        ResultSet rs = null;

        try (PreparedStatement ps = _conn.prepareStatement(READALL)) {

            rs = ps.executeQuery();

            while (rs.next()) {
                String media_id = rs.getString("media_id");
                String title = rs.getString("titre");
                String description = rs.getString("description");

                Media media = new Media(media_id, title, description);
                media.setCategorie(this.getCategorie(rs));
                media.setSousCategorie(this.getSousCategorie(rs));
                mediaList.add(media);
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeResultSet(rs);
            _df.closeConnection();

        }

        return mediaList;
    }

    @Override
    public Media update(Media media) {

        try (PreparedStatement ps = _conn.prepareStatement(UPDATE)) {

            ps.setString(1, media.getTitre());
            ps.setString(2, media.getDescription());
            ps.setInt(3, media.getCategorie().getIdCategorie());
            ps.setInt(4, media.getSousCategorie().getIdSousCategorie());
            ps.setString(5, media.getMediaId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                return media;
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            _df.closeConnection();
        }

        return null;
    }

    @Override
    public Boolean delete(String mediaId) {
        Boolean result = false;

        try (PreparedStatement ps = _conn.prepareStatement(DELETE)) {

            ps.setString(1, mediaId);

            int rowsAffected = ps.executeUpdate();
            result = rowsAffected > 0;

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
                _df.closeConnection();
        }

        return result;
    }

    @Override
    public Categorie getCategorie(ResultSet rs) throws SQLException
    {
        Categorie categorie = null;

        if (rs.getInt("categorie_id") > 0) {
            int categorie_id = rs.getInt("categorie_id");
            String categorieLabel = rs.getString("label");

            categorie = new Categorie(categorie_id, categorieLabel);
        }

        return categorie;
    }

    @Override
    public SousCategorie getSousCategorie(ResultSet rs) throws SQLException
    {
        SousCategorie sousCategorie = null;

        if (rs.getInt("sous_categorie_id") > 0) {
            int sousCategorieId = rs.getInt("sous_categorie_id");
            String sousCategorieLabel = rs.getString("label");

            sousCategorie = new SousCategorie(sousCategorieId, sousCategorieLabel);
        }

        return sousCategorie;
    }
}
