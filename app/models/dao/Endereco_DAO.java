package models.dao;

import models.Endereco;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


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

            strSql = "INSERT INTO Endereco (Rua, Numero, Bairro , " +
                    "cidade, Estado, Dono_Endereco , Cep) VALUES('" +
                    rua + "','" + numero + "','" + bairro + "','" + cidade +
                    "','" + estado + "','" + criador  + "','" + cep + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static void insertEndereco (Endereco end) throws Exception {
        try {
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "INSERT INTO Endereco (Rua, Numero, Bairro , " +
                    "cidade, Estado, Dono_Endereco , Cep) VALUES('" +
                    end.getRua() + "','" + end.getNumero()+ "','" + end.getBairro() + "','" + end.getCidade() +
                    "','" + end.getEstado() + "','" + end.getConfeiteiro() + "','" + end.getCep() + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static void modificarAtributosDeEndereco (String rua, int idEndereco, String cidade, String numero, String bairro, String cep, String estado, int confeiteiro) throws Exception {
        try {
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "UPDATE  Endereco SET  Rua  = '" + rua + "', Numero= '" + numero + "'," +
                    "Bairro= '" + bairro + "', cidade= '" + cidade + "', Estado = '" + estado + "', Dono_Endereco = '" + confeiteiro + "', Cep = '" + cep + "' WHERE ID_Endereco = '" + idEndereco + "';";
            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static ArrayList<Endereco> getEnderecos(int idConfeiteiro)throws Exception {

        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "SELECT * FROM Endereco WHERE Dono_Endereco= '"+idConfeiteiro+"';";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Endereco> endRetorno= new ArrayList<Endereco>();
            Endereco end;
            while(resultadoQuery.next()){
                end= new Endereco();
                end.setId(resultadoQuery.getInt("ID_Endereco"));
                end.setRua(resultadoQuery.getString("Rua"));
                end.setNumero(resultadoQuery.getString("Numero"));
                end.setBairro(resultadoQuery.getString("Bairro"));
                end.setCidade(resultadoQuery.getString("cidade"));
                end.setEstado(resultadoQuery.getString("Estado"));
                end.setConfeiteiro(resultadoQuery.getInt("Dono_Endereco"));
                end.setCep(resultadoQuery.getString("Cep"));
                endRetorno.add(end);

            }
            declaracao.close();
            conexao.close();
            return endRetorno;
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static ArrayList<Endereco> getEnderecos(String idConfeiteiroFacebook)throws Exception {

        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "SELECT * FROM Endereco WHERE Dono_Endereco= (SELECT ID_Confeiteiro FROM  Confeiteiro  WHERE ID_Facebook  = '"+idConfeiteiroFacebook+"');";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Endereco> endRetorno= new ArrayList<Endereco>();
            Endereco end;
            while(resultadoQuery.next()){
                end= new Endereco();
                end.setId(resultadoQuery.getInt("ID_Endereco"));
                end.setRua(resultadoQuery.getString("Rua"));
                end.setNumero(resultadoQuery.getString("Numero"));
                end.setBairro(resultadoQuery.getString("Bairro"));
                end.setCidade(resultadoQuery.getString("cidade"));
                end.setEstado(resultadoQuery.getString("Estado"));
                end.setConfeiteiro(resultadoQuery.getInt("Dono_Endereco"));
                end.setCep(resultadoQuery.getString("Cep"));
                endRetorno.add(end);

            }
            declaracao.close();
            conexao.close();
            return endRetorno;
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

}
