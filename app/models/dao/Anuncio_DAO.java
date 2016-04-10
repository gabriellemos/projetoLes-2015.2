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


}
