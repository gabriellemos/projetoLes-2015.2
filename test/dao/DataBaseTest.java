package dao;

import java.sql.*;
import models.dao.Connection;

import org.junit.Test;
import org.junit.Assert;
import org.postgresql.util.PSQLException;

/**
 * Created by Jordan on 07/04/2016.
 */
public class DataBaseTest {

    @Test
    public void TesteConnection() {

        try {
            Connection.getConnection();
        } catch (SQLException ex) {
            Assert.fail("Falha na conexão com o Banco de Dados");
        }
    }

    @Test
    public void TesteConexaoNaRede() throws SQLException {

        java.sql.Connection dataSource = Connection.getConnection();
        Statement stmt = dataSource.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS ticks");

        try {
            stmt.executeQuery("SELECT tick FROM ticks");
            //Assert.fail("Deveria ter lançado um: " + PSQLException.class );
        } catch (PSQLException exception) {
            // Ok blz
        } catch (Exception exception) {
            Assert.assertEquals(PSQLException.class, exception.getClass());
        }

        stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet query = stmt.executeQuery("SELECT tick FROM ticks");

        query.next();
        Assert.assertNotNull(query.getTimestamp("tick"));
        stmt.close();
    }

    @Test
    public void TesteTemp() throws SQLException {
        java.sql.Connection connection = null;

        try {
            connection = Connection.getConnection();
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getColumns(null, null, "anuncio", null);
            final int colunaDaTabelaAnuncio= 4;

            while (rs.next()) {
                System.out.println("Tabela Anuncio Coluna : " + rs.getString(colunaDaTabelaAnuncio));
            }
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
    }
}