package fr.vannes.gretajavafx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private static DAOFactory instance;
    private String url;
    private String username;
    private String password;

    private DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                instance = new DAOFactory("jdbc:mysql://109.234.166.12:3306/tima6358_amina",
                        "tima6358_amina", "alanTuring2024!");
            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC non trouvé : " + e.getMessage());
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}