package models.dao;

import models.Confeiteiro;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by jordan on 08/04/2016.
 */
public class Confeiteiro_DAO {

    private static java.sql.Connection conexao= null;
    private static String strSql;

    public static void insertConfeiteiro (String Nome, String Email, String Endereco, String Contanto,
                                       String ID_Facebook)  throws Exception {

        try {
            conexao= models.dao.Connection.getConnection();
            Statement declaracao= conexao.createStatement();

            strSql = "INSERT INTO Confeiteiro (Nome_Confeiteiro,Email_Confeiteiro ," +
                    "Endereco_Confeiteiro, Contato_Confeiteiro,ID_Facebook  )" +
                    "VALUES('" + Nome + "','" + Email + "','" + Endereco + "','" + Contanto +
                    "','" + ID_Facebook + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            System.out.println(e);
            System.out.print(e.getMessage());
            throw new InvalidOperationException("Erro ao criar o Confeiteiro");
        }
    }

    /*
     * Retorna listagem de confeiteiros
     */
    public static ArrayList<Confeiteiro> GetConfeiteiros() throws Exception{
        try {
            ResultSet resultadoQuery;
            conexao = models.dao.Connection.getConnection();
            Statement declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Confeiteiro;";
            resultadoQuery = declaracao.executeQuery(strSql);

            Confeiteiro confeiteiro;
            ArrayList listaRetorno = new ArrayList();
            while(resultadoQuery.next()){
                confeiteiro = new Confeiteiro();
                confeiteiro.setNome(resultadoQuery.getString("Nome_Confeiteiro"));
                confeiteiro.setEmail(resultadoQuery.getString("Email_Confeiteiro"));
                confeiteiro.setContato(resultadoQuery.getString("Contato_Confeiteiro"));
                confeiteiro.setEndereco(resultadoQuery.getString("Endereco_Confeiteiro"));
                confeiteiro.setIdFacebook(resultadoQuery.getString("ID_Facebook"));
                confeiteiro.setId(resultadoQuery.getInt("ID_Confeiteiro"));
                listaRetorno.add(confeiteiro);
            }

            declaracao.close();
            conexao.close();
            return listaRetorno;
            // Verifica a exceção do bd
        } catch (Exception e){
            System.out.println(e);
            throw new InvalidOperationException("Tabela Inexistente");
        }
    }

    public static Confeiteiro GetConfeiteiro(int ID) throws Exception{
        try {
            ResultSet resultado;
            conexao = models.dao.Connection.getConnection();
            Statement declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Confeiteiro c WHERE c.ID_Confeiteiro='" + ID + "';";
            resultado = declaracao.executeQuery(strSql);

            Confeiteiro confeiteiroResp = new Confeiteiro();
            // TODO: Verifica uma forma mais simples de resolver isso, utilizando o contutor da classe
            resultado.next();
            confeiteiroResp.setNome(resultado.getString("Nome_Confeiteiro"));
            confeiteiroResp.setEmail(resultado.getString("Email_Confeiteiro"));
            confeiteiroResp.setContato(resultado.getString("Contato_Confeiteiro"));
            confeiteiroResp.setEndereco(resultado.getString("Endereco_Confeiteiro"));
            confeiteiroResp.setIdFacebook(resultado.getString("ID_Facebook"));
            confeiteiroResp.setId(resultado.getInt("ID_Confeiteiro"));
            //ate aqui

            declaracao.close();
            conexao.close();
            return confeiteiroResp;
            // Verifica a exceção do bd
        }catch (Exception e){
            System.out.println(e);
            throw new InvalidOperationException("Confeiteiro Inexistente");
        }
    }
}
