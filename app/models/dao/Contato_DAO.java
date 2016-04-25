package models.dao;

import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

import java.sql.Statement;

/**
 * Created by jordan on 20/04/2016.
 */
public class Contato_DAO {

    private static java.sql.Connection conexao= null;
    private static Statement declaracao = null;
    private static String strSql;

    public static void insertContato (String numero, String codigoEstado,
                                       String codigoOperadora, String codigoPais, int criador) throws Exception {
        try {
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "INSERT INTO Anuncio (Numero_Contato, Codigo_Estado, Codigo_Operadora , " +
                    "Codigo_Pais  , Dono_Contato ) VALUES('" +
                  numero + "','" + codigoEstado + "','" +codigoOperadora + "','" +codigoPais +
                    "','" + criador  + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            throw new InvalidOperationException("Erro ao cadastrar Endereço");
        }
    }
}
