/*
 * Copyright (c) 2024.
 * Frédéric Le Mélianidre
 * Formation CDA
 */

package fr.vannes.gretajavafx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOFactory {

    private static Connection _connection = null;

    private static final String SQL_SERVER = "jdbc:mysql://109.234.166.12:3306/";
    private static final String SQL_DATA_BASE = "tima6358_frederic";
    private static final String SQL_USER = "tima6358_frederic";
    private static final String SQL_PASS = "alanTuring2024!";


    public static DAOFactory getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new DAOFactory();
    }

    public Connection getConnection() throws SQLException {
        String url = SQL_SERVER + SQL_DATA_BASE;
        return DriverManager.getConnection(url, SQL_USER, SQL_PASS);
    }

    public void closeConnection() {
        if(_connection != null) {
            try {
                getConnection().close();
                _connection = null;
            } catch (SQLException e) {
                _connection = null;
                System.out.println(e.getSQLState());
            }

        }
    }

    public void closeResultSet(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
                rs = null;
            } catch (SQLException e) {
                System.out.println(e.getSQLState());
            }
        }
    }
}
