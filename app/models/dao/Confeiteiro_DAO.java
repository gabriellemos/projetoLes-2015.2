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
    private static String strSql;

    public static void insertConfeiteiro (String Nome, String Email, String Endereco,
                                       String ID_Facebook)  throws RequisicaoInvalidaBD {

        try {
            conexao= models.dao.Connection.getConnection();
            Statement declaracao= conexao.createStatement();

            strSql = "INSERT INTO Confeiteiro (Nome_Confeiteiro,ID_Facebook  )" +
                    "VALUES('" + Nome + "',''" + ID_Facebook + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            throw new RequisicaoInvalidaBD("Erro ao criar o Confeiteiro");
        }
    }

    /*
     * Retorna listagem de confeiteiros
     */
    public static ArrayList<Confeiteiro> GetConfeiteiros() throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = models.dao.Connection.getConnection();
            Statement declaracao = conexao.createStatement();
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
                listaRetorno.add(confeiteiro);
            }

            declaracao.close();
            conexao.close();
            return listaRetorno;
            // Verifica a exceção do bd
        } catch (Exception e){
            throw new RequisicaoInvalidaBD("Tabela Inexistente");
        }
    }

    public static Confeiteiro GetConfeiteiro(int ID) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultado;
            conexao = models.dao.Connection.getConnection();
            Statement declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Confeiteiro c WHERE c.ID_Confeiteiro='" + ID + "';";
            resultado = declaracao.executeQuery(strSql);

            Confeiteiro confeiteiroResp = new Confeiteiro();

            // TODO: Verificar forma para converter a linha da tabela diretamente para Confeiteiro.
            resultado.next();
            confeiteiroResp.setNome(resultado.getString("Nome_Confeiteiro"));
            confeiteiroResp.setIdFacebook(resultado.getString("ID_Facebook"));
            confeiteiroResp.setId(resultado.getInt("ID_Confeiteiro"));
            //ate aqui

            declaracao.close();
            conexao.close();
            return confeiteiroResp;
            // Verifica a exceção do bd
        }catch (Exception e){
            System.out.println(e);
            throw new RequisicaoInvalidaBD("Confeiteiro Inexistente");
        }
    }
}
