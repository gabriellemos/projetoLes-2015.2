package models.dao;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

import java.sql.Statement;


/**
 * Created by jordan on 18/04/2016.
 */
public class Endereco_DAO {
    private static java.sql.Connection conexao= null;
    private static Statement declaracao = null;
    private static String strSql;

    public static void insertEndereco (String rua, String cidade, String numero,
                                      String bairro, String cep, int criador,
                                      String estado) throws Exception {
        try {
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "INSERT INTO Anuncio (Rua, Numero, Bairro , " +
                    "cidade, Estado, Dono_Endereco , Cep) VALUES('" +
                    rua + "','" + numero + "','" + bairro + "','" + cidade +
                    "','" + estado + "','" + criador  + "','" + cep + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            throw new InvalidOperationException("Erro ao cadastrar Endereço");
        }
    }
}
