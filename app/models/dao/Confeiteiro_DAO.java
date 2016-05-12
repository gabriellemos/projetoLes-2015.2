package models.dao;

import models.Confeiteiro;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by jordan on 08/04/2016.
 */
public class Confeiteiro_DAO {

    private static java.sql.Connection conexao= null;
    private static Statement declaracao = null;
    private static String strSql;

    public static void insertConfeiteiro (String Nome, String ID_Facebook, int ID_Confeiteiro)  throws RequisicaoInvalidaBD {

        try {
            conexao= models.dao.Connection.getConnection();
            declaracao= conexao.createStatement();

            strSql = "INSERT INTO Confeiteiro (Nome_Confeiteiro,ID_Facebook,ID_Confeiteiro)" +
                    "VALUES('" + Nome + "','" + ID_Facebook + "','" + ID_Confeiteiro + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static void insertConfeiteiro (Confeiteiro conf)  throws RequisicaoInvalidaBD {
       insertConfeiteiro(conf.getNome(), conf.getIdFacebook(), conf.getId());
    }

    /*
     * Retorna listagem de confeiteiros
     */
    public static ArrayList<Confeiteiro> GetConfeiteiros() throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = models.dao.Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Confeiteiro;";
            resultadoQuery = declaracao.executeQuery(strSql);

            Confeiteiro confeiteiro;
            ArrayList listaRetorno = new ArrayList();
            // TODO: Verificar forma para converter a linha da tabela diretamente para Confeiteiro.
            while(resultadoQuery.next()){
                confeiteiro = new Confeiteiro();
                confeiteiro.setNome(resultadoQuery.getString("Nome_Confeiteiro"));
                confeiteiro.setIdFacebook(resultadoQuery.getString("ID_Facebook"));
                confeiteiro.setId(resultadoQuery.getInt("ID_Confeiteiro"));
                confeiteiro.setEnderecos(Endereco_DAO.getEnderecos(confeiteiro.getId()));
                confeiteiro.setEmails(Email_DAO.getEmails(confeiteiro.getId()));
                confeiteiro.setContatos(Contato_DAO.getContatos(confeiteiro.getId()));
                listaRetorno.add(confeiteiro);
            }

            declaracao.close();
            conexao.close();
            return listaRetorno;
            // Verifica a exceção do bd
        } catch (Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static Confeiteiro GetConfeiteiro(int ID) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultado;
            conexao = models.dao.Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Confeiteiro c WHERE c.ID_Confeiteiro='" + ID + "';";
            resultado = declaracao.executeQuery(strSql);

            Confeiteiro confeiteiroResp = new Confeiteiro();

            // TODO: Verificar forma para converter a linha da tabela diretamente para Confeiteiro.
            resultado.next();
            confeiteiroResp.setNome(resultado.getString("Nome_Confeiteiro"));
            confeiteiroResp.setIdFacebook(resultado.getString("ID_Facebook"));
            confeiteiroResp.setId(resultado.getInt("ID_Confeiteiro"));
            confeiteiroResp.setEnderecos(Endereco_DAO.getEnderecos(ID));
            confeiteiroResp.setEmails(Email_DAO.getEmails(ID));
            confeiteiroResp.setContatos(Contato_DAO.getContatos(ID));
            //ate aqui

            declaracao.close();
            conexao.close();
            return confeiteiroResp;
            // Verifica a exceção do bd
        }catch (Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static Confeiteiro getConfeiteiro(String idFacebook) throws RequisicaoInvalidaBD {
        try {
            ResultSet resultado;
            conexao = models.dao.Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Confeiteiro c WHERE c.ID_Facebook='" + idFacebook + "';";
            resultado = declaracao.executeQuery(strSql);

            Confeiteiro confeiteiroResp = new Confeiteiro();

            resultado.next();
            confeiteiroResp.setNome(resultado.getString("Nome_Confeiteiro"));
            confeiteiroResp.setIdFacebook(resultado.getString("ID_Facebook"));
            confeiteiroResp.setId(resultado.getInt("ID_Confeiteiro"));

            declaracao.close();
            conexao.close();
            return confeiteiroResp;
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static int getNextAvailableID() throws RequisicaoInvalidaBD {
        int resposta = 0;
        try {
            ResultSet resultado;
            conexao = models.dao.Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT MIN(t1.ID_Confeiteiro + 1) AS nextID " +
                    "FROM Confeiteiro t1 " +
                    "LEFT JOIN Confeiteiro t2 " +
                    "ON t1.ID_Confeiteiro + 1 = t2.ID_Confeiteiro " +
                    "WHERE t2.ID_Confeiteiro IS NULL;";
            resultado = declaracao.executeQuery(strSql);

            resultado.next();
            resposta = resultado.getInt("nextID");

            declaracao.close();
            conexao.close();

            return resposta;
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }
}
