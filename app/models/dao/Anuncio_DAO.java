package models.dao;

import models.Anuncio;
import models.Confeiteiro;
import models.TipoAnuncio;
import models.Utils;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.GregorianCalendar;

/**
 * Created by gabriel on 08/04/2016.
 */
public class Anuncio_DAO {

    private static java.sql.Connection conexao= null;
    private static Statement declaracao = null;
    private static String strSql;

    /*
     * Cria um Anúncio no banco de dados. Um id é atribuido automaticamente no ato da criação.
     */
    public static void createAnuncio (String titulo, String descricao, String dataEdicao,
                               String dataCriacao, String preco, String criador, String tipo)
            throws Exception {

        try {
            conexao= Connection.getConnection();
            declaracao= conexao.createStatement();

            strSql = "INSERT INTO Anuncio (titulo_anuncio, descricao_anuncio, edicao_anuncio, " +
                    "criacao_anuncio, preco_anuncio, criador_anuncio, tipo_anuncio) VALUES('" +
                    titulo + "','" + descricao + "','" + dataEdicao + "','" + dataCriacao + "','" +
                    preco + "','" + criador  + "','" + tipo + "')";

            declaracao.executeLargeUpdate(strSql);
            declaracao.close();
            conexao.commit();
            conexao.close();
        } catch(Exception e){
            throw new InvalidOperationException("Erro ao criar o Anúncio");
        }
    }

    /*
     * Retorna Listagem de Anúncio do banco de dados.
     */
    public static ResultSet getAnuncios() throws Exception{
        try {
            ResultSet resultado;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio;";
            resultado = declaracao.executeQuery(strSql);
            declaracao.close();
            conexao.close();
            return resultado;
            // Verifica a exceção do bd
        } catch (Exception e) {
            throw new InvalidOperationException("Tabela Inexistente");
        }
    }

    /*
     * Retorna um objeto Anúncio de acordo com o ID no banco de dados.
     */
    public static Anuncio getAnuncio(int ID) throws Exception{
        try {
            ResultSet resultado;
            Anuncio resposta= new Anuncio();
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio a WHERE a.id_anuncio='" + ID + "';";
            resultado = declaracao.executeQuery(strSql);
            // TODO: Verifica uma forma mais simples de resolver isso, utilizando o contutor da classe
            resposta.setId(resultado.getInt("id_anuncio"));
            resposta.setTitulo(resultado.getString("titulo_anuncio"));
            resposta.setDescricao(resultado.getString("descricao_anuncio"));
            resposta.setDataEdicao(Utils.converteDateToGregorianCalendar(
                    resultado.getDate("edicao_anuncio")
            ));
            resposta.setDataCriacao(Utils.converteDateToGregorianCalendar(
                    resultado.getDate("criacao_anuncio")
            ));
            resposta.setPreco(resultado.getFloat("preco_anuncio"));
            resposta.setCriador(Confeiteiro_DAO.GetConfeiteiro(
                    resultado.getInt("criador_anuncio")
            ));
            resposta.setTipoAnuncio(TipoAnuncio.valueOf(
                    resultado.getString("tipo-anuncio")
            ));
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
