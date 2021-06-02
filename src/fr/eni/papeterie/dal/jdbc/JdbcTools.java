package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * CLASSE JDBCTOOLS POUR SE CONNECTER A LA BDD VIA LA CLASSE SETTINGS
 */

public class JdbcTools {
    private final static String URL = Settings.getPropriete("url");

    public static Connection recupConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        return connection;
    }
}
