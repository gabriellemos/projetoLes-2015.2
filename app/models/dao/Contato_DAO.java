package models.dao;


import models.Contato;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

            strSql = "INSERT INTO Contato  (Numero_Contato, Codigo_Estado, Codigo_Operadora , " +
                    "Codigo_Pais  , Dono_Contato ) VALUES('" +
                  numero + "','" + codigoEstado + "','" +codigoOperadora + "','" +codigoPais +
                    "','" + criador  + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static void insertContato (Contato contato) throws Exception {
        try {
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "INSERT INTO Contato  (Numero_Contato, Codigo_Estado, Codigo_Operadora , " +
                    "Codigo_Pais  , Dono_Contato ) VALUES('" +
                    contato.getNumero() + "','" + contato.getCodigoEstado() + "','" +contato.getCodigoOperadora() + "','" +contato.getCodigoPais() +
                    "','" + contato.getDonoContato() + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static void modificarAtributosDeContato (String numero, String codigoPais, int idContato, String codigoOperadora, String codigoEstado, int donoContato) throws Exception {
        try {
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "UPDATE  Contato SET  Numero_Contato  = '" + numero + "', Codigo_Estado= '" + codigoEstado + "'," +
                    "Codigo_Operadora= '" + codigoOperadora + "', Codigo_Pais= '" + codigoPais + "', Dono_Contato = '" + donoContato + "' WHERE ID_Contato = '" + idContato + "';";
            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static ArrayList<Contato> getContatos(int idConfeiteiro)throws Exception {
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "SELECT * FROM Contato WHERE Dono_Contato= '"+idConfeiteiro+"';";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Contato> contatoRetorno= new ArrayList<Contato>();
            Contato contato;
            while(resultadoQuery.next()){
                contato= new Contato();
                contato.setNumero(resultadoQuery.getString("Numero_Contato"));
                contato.setCodigoEstado(resultadoQuery.getString("Codigo_Estado"));
                contato.setCodigoOperadora(resultadoQuery.getString("Codigo_Operadora"));
                contato.setCodigoPais(resultadoQuery.getString("Codigo_Pais"));
                contato.setIdContato(resultadoQuery.getInt("ID_Contato"));
                contato.setDonoContato(resultadoQuery.getInt("Dono_Contato"));
                contatoRetorno.add(contato);

            }
            declaracao.close();
            conexao.close();
            return contatoRetorno;
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }


    }

    public static ArrayList<Contato> getContatos(String idConfeiteiroFacebook)throws Exception {
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "SELECT * FROM Contato WHERE Dono_Contato= (SELECT ID_Confeiteiro FROM  Confeiteiro  WHERE ID_Facebook  = '"+idConfeiteiroFacebook+"');";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Contato> contatoRetorno= new ArrayList<Contato>();
            Contato contato;
            while(resultadoQuery.next()){
                contato= new Contato();
                contato.setNumero(resultadoQuery.getString("Numero_Contato"));
                contato.setCodigoEstado(resultadoQuery.getString("Codigo_Estado"));
                contato.setCodigoOperadora(resultadoQuery.getString("Codigo_Operadora"));
                contato.setCodigoPais(resultadoQuery.getString("Codigo_Pais"));
                contato.setIdContato(resultadoQuery.getInt("ID_Contato"));
                contato.setDonoContato(resultadoQuery.getInt("Dono_Contato"));
                contatoRetorno.add(contato);

            }
            declaracao.close();
            conexao.close();
            return contatoRetorno;
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }


    }

}
