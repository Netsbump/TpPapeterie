package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
    private final static String URL = Settings.getPropriete("url");

    public static Connection recupConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        return connection;
    }
}
