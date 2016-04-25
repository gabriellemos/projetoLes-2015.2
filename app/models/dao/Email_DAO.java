package models.dao;


import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

import java.sql.Statement;

/**
 * Created by jordan on 20/04/2016.
 */
public class Email_DAO {
    private static java.sql.Connection conexao= null;
    private static Statement declaracao = null;
    private static String strSql;

    public static void insertEmail (Email email) throws Exception {
        try {
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "INSERT INTO Anuncio (Email , Dono_Email ) VALUES('" +
                    email.getEmail() + "','" + email.getDonoEmail() + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            throw new InvalidOperationException("Erro ao cadastrar Endereço");
        }
    }

}
