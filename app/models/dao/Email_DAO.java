package models.dao;


import models.Email;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

            strSql = "INSERT INTO Email (Email , Dono_Email ) VALUES('" +
                    email.getEmail() + "','" + email.getDonoEmail() + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static void modificarAtributosDeEmail (String email, int DonoEmail, int idEmail) throws Exception{
        try{
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql= "UPDATE  Email SET  Email  = '"+email+"', Dono_Email = '"+DonoEmail+"' WHERE ID_Email = '"+idEmail+"';";
            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        }catch (Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }

    public static ArrayList<Email> getEmails(int idConfeiteiro)throws Exception {

        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "SELECT * FROM Email WHERE Dono_Email= '"+idConfeiteiro+"';";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Email> emailRetorno= new ArrayList<Email>();
            Email email;
            while(resultadoQuery.next()){
                email= new Email();
                email.setIdEmail(resultadoQuery.getInt("ID_Email"));
                email.setEmail(resultadoQuery.getString("Email"));
                email.setDonoEmail(resultadoQuery.getInt("Dono_Email"));

                emailRetorno.add(email);

            }
            declaracao.close();
            conexao.close();
            return emailRetorno;
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static ArrayList<Email> getEmails(String idConfeiteiroFacebook)throws Exception {

        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "SELECT * FROM Email WHERE Dono_Email= (SELECT ID_Confeiteiro FROM  Confeiteiro  WHERE ID_Facebook  = '"+idConfeiteiroFacebook+"');";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Email> emailRetorno= new ArrayList<Email>();
            Email email;
            while(resultadoQuery.next()){
                email= new Email();
                email.setIdEmail(resultadoQuery.getInt("ID_Email"));
                email.setEmail(resultadoQuery.getString("Email"));
                email.setDonoEmail(resultadoQuery.getInt("Dono_Email"));

                emailRetorno.add(email);

            }
            declaracao.close();
            conexao.close();
            return emailRetorno;
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }




}
