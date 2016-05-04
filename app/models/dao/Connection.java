package models.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Jordan on 06/04/2016.
 */
public class Connection {

    private static final boolean conexaoLocal = true;

    private static final String prefixUrl = "jdbc:postgresql://";
    private static final String dataBaseUrl = "localhost:5432/";
    private static final String dataBaseName = "LocalGetCake";
    private static final String uName = "postgres";
    private static final String uPassword = "password";

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://ec2-54-83-56-31.compute-1.amazonaws.com/d3hlo3a4v5t6ap?relaxAutoCommit=true?user=lxwywszfwwzdux&password=Ml3Y7qJBqChMNBo6ys0WeaHnGc&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
    private static final String USERNAME = "lxwywszfwwzdux";
    private static final String PASSWORD = "Ml3Y7qJBqChMNBo6ys0WeaHnGc";

    public static java.sql.Connection getConnection() throws SQLException {
        java.sql.Connection conn = null;

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
        }

        if (conexaoLocal) {
            String url = prefixUrl + dataBaseUrl + dataBaseName;
            conn = DriverManager.getConnection(url, uName, uPassword);
        } else {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }

        return conn;
    }

}
