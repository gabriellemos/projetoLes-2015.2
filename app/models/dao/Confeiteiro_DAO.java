package models.dao;

import models.*;

import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by jordan on 08/04/2016.
 */
public class Confeiteiro_DAO {

    private static java.sql.Connection conexao = null;
    private static Statement declaracao = null;
    private static String strSql;

    public static void insertConfeiteiro(String Nome, String ID_Facebook) throws RequisicaoInvalidaBD {

        try {
            conexao = models.dao.Connection.getConnection();
            declaracao = conexao.createStatement();

            strSql = "INSERT INTO Confeiteiro (Nome_Confeiteiro,ID_Facebook  )" +
                    "VALUES('" + Nome + "','" + ID_Facebook + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static void insertConfeiteiro(Confeiteiro conf) throws RequisicaoInvalidaBD {

        insertConfeiteiro(conf.getNome(), conf.getIdFacebook());
    }

    public static void modificarAtributosDeConfeiteiro(String nome, ArrayList<Endereco> enderecos, ArrayList<Contato> contatos, ArrayList<Email> emails, String idFacebook, int idConfeiteiro, HashSet<Anuncio> meusAnuncios) throws Exception {
        try {
            Endereco_DAO enderecoDao = new Endereco_DAO();
            Contato_DAO contatoDao = new Contato_DAO();
            Email_DAO emailDao = new Email_DAO();
            Anuncio_DAO anuncioDao = new Anuncio_DAO();
            for (Endereco endereco :
                    enderecos) {
                enderecoDao.modificarAtributosDeEndereco(endereco.getRua(), endereco.getId(), endereco.getCidade(), endereco.getNumero(), endereco.getBairro(), endereco.getCep(), endereco.getEstado(), endereco.getConfeiteiro());
            }
            for (Contato contato :
                    contatos) {
                contatoDao.modificarAtributosDeContato(contato.getNumero(), contato.getCodigoPais(), contato.getIdContato(), contato.getCodigoOperadora(), contato.getCodigoEstado(), contato.getDonoContato());
            }
            for (Email email :
                    emails) {
                emailDao.modificarAtributosDeEmail(email.getEmail(), email.getDonoEmail(), email.getIdEmail());
            }
            for (Anuncio anuncio :
                    meusAnuncios) {
                anuncioDao.ModificarAtributosDeAnuncio(anuncio.getTitulo(), anuncio.getDescricao(), anuncio.getPreco(), anuncio.getTipoAnuncio().toString(), anuncio);
            }
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "UPDATE  Confeiteiro SET  Nome_Confeiteiro  = '" + nome + "', ID_Facebook = '" + idFacebook + "' WHERE ID_Confeiteiro = '" + idConfeiteiro + "';";
            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    /*
     * Retorna listagem de confeiteiros
     */
    public static ArrayList<Confeiteiro> GetConfeiteiros ()throws RequisicaoInvalidaBD {
        try {
            ResultSet resultadoQuery;
            conexao = models.dao.Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Confeiteiro;";
            resultadoQuery = declaracao.executeQuery(strSql);
            Confeiteiro confeiteiro;
            ArrayList listaRetorno = new ArrayList();
            // TODO: Verificar forma para converter a linha da tabela diretamente para Confeiteiro.
            while (resultadoQuery.next()) {
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
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static Confeiteiro GetConfeiteiro(int ID) throws RequisicaoInvalidaBD {
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
        } catch (Exception e) {
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

}
