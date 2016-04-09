package models.dao;

import models.Confeiteiro;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by jordan on 08/04/2016.
 */
public class Confeiteiro_DAO {

    private  java.sql.Connection conexao= null;
    private  Statement declaracao = null;
    private  String strSql;

    public void cadastrar_Confeiteiro (String Nome, String Email, String Endereco, String Contanto,
                                       String ID_Facebook)  throws Exception {

        try {
            conexao= models.dao.Connection.getConnection();
            declaracao= conexao.createStatement();

            strSql = "INSERT INTO Confeiteiro (Nome_Confeiteiro,Email_Confeiteiro ," +
                    "Endereco_Confeiteiro, Contato_Confeiteiro,ID_Facebook  )" +
                    "VALUES('" + Nome + "','" + Email + "','" + Endereco + "','" + Contanto +
                    "','" + ID_Facebook + "')";

            declaracao.executeLargeUpdate(strSql);
            declaracao.close();
            conexao.commit();
            conexao.close();
        } catch(Exception e){
            throw new InvalidOperationException("Erro ao criar o Confeiteiro");
        }
    }

    /*
     * Retorna listagem de confeiteiros
     */
    public ResultSet GetConfeiteiros() throws Exception{
        try {
            ResultSet resultado;
            conexao = models.dao.Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Confeiteiro;";
            resultado = declaracao.executeQuery(strSql);
            declaracao.close();
            conexao.close();
            return resultado;
            // Verifica a exceção do bd
        } catch (Exception e){
            throw new InvalidOperationException("Tabela Inexistente");
        }
    }

    public Confeiteiro GetConfeiteiro(int ID) throws Exception{
        try {
            ResultSet resultado;
            Confeiteiro resposta= new Confeiteiro();
            conexao = models.dao.Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Confeiteiro c WHERE c.ID_Confeiteiro='" + ID + "';";
            resultado = declaracao.executeQuery(strSql);
            // TODO: Verifica uma forma mais simples de resolver isso, utilizando o contutor da classe
            resposta.setNome(resultado.getString("Nome_Confeiteiro"));
            resposta.setEmail(resultado.getString("Email_Confeiteiro"));
            resposta.setContato(resultado.getString("Contato_Confeiteiro"));
            resposta.setEndereco(resultado.getString("Endereco_Confeiteiro"));
            resposta.setIdFacebook(resultado.getString("ID_Facebook"));
            resposta.setId(resultado.getInt("ID_Confeiteiro"));
            //ate aqui
            declaracao.close();
            conexao.close();
            return resposta;
            // Verifica a exceção do bd
        }catch (Exception e){
            throw new InvalidOperationException("Confeiteiro Inexistente");
        }
    }
}
