package models.dao;

import models.Anuncio;
import models.Confeiteiro;
import models.TipoAnuncio;
import models.Utils;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
    public static void insertAnuncio (String titulo, String descricao, GregorianCalendar dataEdicao,
                                      GregorianCalendar dataCriacao, float preco, int criador,
                                      String tipo) throws Exception {

        java.sql.Date dataEdicaoBD;
        java.sql.Date dataCriacaoBD;
        try {
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            dataEdicaoBD = new java.sql.Date(dataEdicao.getTimeInMillis());
            dataCriacaoBD = new java.sql.Date(dataCriacao.getTimeInMillis());
            strSql = "INSERT INTO Anuncio (titulo_anuncio, descricao_anuncio, edicao_anuncio, " +
                    "criacao_anuncio, preco_anuncio, criador_anuncio, tipo_anuncio) VALUES('" +
                    titulo + "','" + descricao + "','" + dataEdicaoBD + "','" + dataCriacaoBD +
                    "','" + preco + "','" + criador  + "','" + tipo + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            System.out.println(e);
            System.out.println(e.getMessage());
            throw new InvalidOperationException("Erro ao criar o Anúncio");
        }
    }

    /*
     * Retorna Listagem de Anúncio do banco de dados.
     */
    public static ArrayList<Anuncio> getAnuncios() throws Exception{
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio;";
            resultadoQuery = declaracao.executeQuery(strSql);

            Anuncio anuncio;
            ArrayList<Anuncio> listaRetorno = new ArrayList();
            while(resultadoQuery.next()){
                anuncio = new Anuncio();
                anuncio.setId(resultadoQuery.getInt("Id_Anuncio"));
                anuncio.setTitulo(resultadoQuery.getString("titulo_anuncio"));
                anuncio.setDescricao(resultadoQuery.getString("descricao_anuncio"));
                anuncio.setDataCriacao(Utils.converteDateToGregorianCalendar(
                        resultadoQuery.getDate("criacao_anuncio")));
                anuncio.setDataEdicao(Utils.converteDateToGregorianCalendar(
                        resultadoQuery.getDate("edicao_anuncio")));
                anuncio.setPreco(resultadoQuery.getFloat("Preco_Anuncio"));
                anuncio.setCriador(Confeiteiro_DAO.GetConfeiteiro(
                        resultadoQuery.getInt("criador_anuncio")));
                anuncio.setTipoAnuncio(TipoAnuncio.valueOf(
                        resultadoQuery.getString("tipo_anuncio").trim()));
                listaRetorno.add(anuncio);
            }

            declaracao.close();
            conexao.close();
            return listaRetorno;
            // Verifica a exceção do bd
        } catch (Exception e) {
            System.out.println(e);
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
