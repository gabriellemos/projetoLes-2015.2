package models.dao.Tabelas;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Gabriel on 02/05/2016.
 */
public class ConfigureBD {

    private static Statement declaracao = null;
    private static java.sql.Connection conexao = null;

    public static void main( String args[] ) throws SQLException {

        conexao = models.dao.Connection.getConnection();
        declaracao = conexao.createStatement();

        // Drop tables
        //dropTables();

        // Create tables
        createTables();

        declaracao.close();
        conexao.close();

        System.out.println("BD configurado com sucesso");
    }

    private static void createTables() throws SQLException {
        declaracao.executeUpdate(ScriptSQL.getCreateConfeiteiro());
        declaracao.executeUpdate(ScriptSQL.getCreateContato());
        declaracao.executeUpdate(ScriptSQL.getCreateEmail());
        declaracao.executeUpdate(ScriptSQL.getCreateEndereco());
        declaracao.executeUpdate(ScriptSQL.getCreateAnuncio());
    }

    private static void dropTables() throws SQLException {
        declaracao.executeUpdate("DROP TABLE Anuncio");
        declaracao.executeUpdate("DROP TABLE Contato");
        declaracao.executeUpdate("DROP TABLE Email");
        declaracao.executeUpdate("DROP TABLE Endereco");
        declaracao.executeUpdate("DROP TABLE Confeiteiro");
    }

}
