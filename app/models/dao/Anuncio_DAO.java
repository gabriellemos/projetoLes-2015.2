package models.dao;

import models.Anuncio;
import models.Confeiteiro;
import models.TipoAnuncio;
import models.Utils;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private static final String SEPARADOR = "','";

    /*
     * Cria um Anúncio no banco de dados. Um id é atribuido automaticamente no ato da criação.
     */
    public static void insertAnuncio (String titulo, String descricao, GregorianCalendar dataEdicao,
                                      String preco, int criador, String tipo) throws RequisicaoInvalidaBD {

        TipoAnuncio tipoAnuncio = TipoAnuncio.valueOf(tipo.trim());
        Confeiteiro confeiteiro = new Confeiteiro();
        confeiteiro.setId(criador);

        Anuncio anuncio = new Anuncio(titulo, confeiteiro, descricao, tipoAnuncio, preco);
        insertAnuncio(anuncio);
    }

    public static void insertAnuncio (Anuncio anuncio) throws RequisicaoInvalidaBD {

        java.sql.Date dataEdicaoBD;
        java.sql.Date dataCriacaoBD;
        try {
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();

            dataEdicaoBD = new java.sql.Date(Utils.getHoje().getTimeInMillis());
            dataCriacaoBD = new java.sql.Date(Utils.getHoje().getTimeInMillis());
            strSql = "INSERT INTO Anuncio (titulo_anuncio, descricao_anuncio, edicao_anuncio, " +
                    "criacao_anuncio, preco_anuncio, criador_anuncio, tipo_anuncio) VALUES('" +
                    anuncio.getTitulo() + SEPARADOR + anuncio.getDescricao() + SEPARADOR +
                    dataEdicaoBD + SEPARADOR + dataCriacaoBD + SEPARADOR + anuncio.getPreco() +
                    SEPARADOR + anuncio.getCriador().getId()  + SEPARADOR + anuncio.getTipoAnuncio()
                    + "')";

            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        } catch(Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

public static  void removeAnuncio (int id){

    try{
        conexao = Connection.getConnection();
        declaracao = conexao.createStatement();
        strSql= "DELETE FROM Anuncio WHERE ID_Anuncio = '"+id+"';";
        declaracao.executeUpdate(strSql);
        declaracao.close();
        conexao.close();
    }catch (Exception e){
        RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
        exception.setStackTrace(e.getStackTrace());
        throw exception;
    }

}

    public static  void removeAnuncio (Anuncio anuncio){

        try{
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql= "DELETE FROM Anuncio WHERE ID_Anuncio = '"+anuncio.getId()+"';";
            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        }catch (Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }

    }

    public static  void ModificarVisibilidadeAnuncio (boolean boll, int id){
        try{
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql= "UPDATE  Anuncio SET  Disponibilidade_Anuncio = '"+boll+"' WHERE ID_Anuncio = '"+id+"';";
            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        }catch (Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static  void ModificarVisibilidadeAnuncio (boolean boll, Anuncio anuncio){
        ModificarVisibilidadeAnuncio(boll, anuncio.getId());
    }

    public static  void ModificarAtributosDeAnuncio (String titulo, String descricao, String preco, String tipo, int id){

        try{
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            java.sql.Date dataEdicaoBD;
            GregorianCalendar hoje= new GregorianCalendar();
            dataEdicaoBD = new java.sql.Date(hoje.getTimeInMillis());
            strSql= "UPDATE  Anuncio SET  Titulo_Anuncio  = '"+titulo+"', Descricao_Anuncio= '"+ descricao+"'," +
                    "Preco_Anuncio= '"+preco+"', Tipo_Anuncio= '"+ tipo+"', Edicao_Anuncio = '"+dataEdicaoBD+"' WHERE ID_Anuncio = '"+id+"';";
            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        }catch (Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static  void ModificarAtributosDeAnuncio (String titulo, String descricao, String preco, String tipo, Anuncio anuncio){

        try{
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            java.sql.Date dataEdicaoBD;
            GregorianCalendar hoje= new GregorianCalendar();
            dataEdicaoBD = new java.sql.Date(hoje.getTimeInMillis());
            strSql= "UPDATE  Anuncio SET  Titulo_Anuncio  = '"+titulo+"', Descricao_Anuncio= '"+ descricao+"'," +
                    "Preco_Anuncio= '"+preco+"', Tipo_Anuncio= '"+ tipo+"', Edicao_Anuncio = '"+dataEdicaoBD+"' WHERE ID_Anuncio = '"+anuncio.getId()+"';";
            declaracao.executeUpdate(strSql);
            declaracao.close();
            conexao.close();
        }catch (Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }

    }
    /*
     * Retorna Listagem de Anúncio do banco de dados.
     */
    public static ArrayList<Anuncio> getAnuncios() throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio WHERE  Disponibilidade_Anuncio = TRUE ORDER BY Edicao_Anuncio ASC;";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Anuncio> listaRetorno = new ArrayList();

            Anuncio anuncio;
            while(resultadoQuery.next()){
                anuncio = getAnuncio(resultadoQuery);
                listaRetorno.add(anuncio);
            }

            declaracao.close();
            conexao.close();
            return listaRetorno;
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static ArrayList<Anuncio> getAnunciosPelaCidade(String cidade) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio WHERE  Disponibilidade_Anuncio = TRUE AND criador_anuncio IN (SELECT Dono_Endereco FROM  Endereco  WHERE cidade  = '"+cidade+"')" +
                    " ORDER BY Titulo_Anuncio ASC;";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Anuncio> listaRetorno = new ArrayList();

            Anuncio anuncio;
            while(resultadoQuery.next()){
                anuncio = getAnuncio(resultadoQuery);
                listaRetorno.add(anuncio);
            }

            declaracao.close();
            conexao.close();
            return listaRetorno;
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    /*
     * Retorna um objeto Anúncio de acordo com o ID no banco de dados.
     */
    public static Anuncio getAnuncio(int ID) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio a WHERE a.id_anuncio='" + ID + "' AND Disponibilidade_Anuncio = TRUE;";
            resultadoQuery = declaracao.executeQuery(strSql);

            Anuncio anuncio;
            anuncio = getAnuncio(resultadoQuery);

            declaracao.close();
            conexao.close();
            return anuncio;
        }catch (Exception e){
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static  ArrayList<Anuncio> getAnunciosPeloConfeiteiro(int IdConfeiteiro) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio WHERE  Disponibilidade_Anuncio= TRUE AND criador_anuncio ='"+IdConfeiteiro+"'" +
                    " ORDER BY Titulo_Anuncio ASC;";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Anuncio> listaRetorno = new ArrayList();

            Anuncio anuncio;
            while(resultadoQuery.next()){
                anuncio = getAnuncio(resultadoQuery);
                listaRetorno.add(anuncio);
            }

            declaracao.close();
            conexao.close();
            return listaRetorno;
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static  void getAnunciosPeloConfeiteiro(Confeiteiro confeiteiro) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio WHERE  Disponibilidade_Anuncio = TRUE AND criador_anuncio ='"+ confeiteiro.getId()+"'" +
                    " ORDER BY Titulo_Anuncio ASC;";
            resultadoQuery = declaracao.executeQuery(strSql);

            Anuncio anuncio;
            while(resultadoQuery.next()){
                anuncio = getAnuncio(resultadoQuery);
                confeiteiro.addAnuncio(anuncio);
            }

            declaracao.close();
            conexao.close();
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }
    public static  ArrayList<Anuncio> getAnunciosPeloConfeiteiroFacebook(String IdConfeiteiroFacebook) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio WHERE  Disponibilidade_Anuncio= TRUE AND criador_anuncio = (SELECT ID_Confeiteiro FROM  Confeiteiro  WHERE ID_Facebook  = '"+IdConfeiteiroFacebook+"')" +
                    " ORDER BY Titulo_Anuncio ASC;";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Anuncio> listaRetorno = new ArrayList();

            Anuncio anuncio;
            while(resultadoQuery.next()){
                anuncio = getAnuncio(resultadoQuery);
                listaRetorno.add(anuncio);
            }

            declaracao.close();
            conexao.close();
            return listaRetorno;
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    public static  ArrayList<Anuncio> getAnunciosPeloConfeiteiroFacebook(Confeiteiro confeiteiro) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio WHERE  Disponibilidade_Anuncio= TRUE AND criador_anuncio = (SELECT ID_Confeiteiro FROM  Confeiteiro  WHERE ID_Facebook  = '"+confeiteiro.getIdFacebook()+"')" +
                    " ORDER BY Titulo_Anuncio ASC;";
            resultadoQuery = declaracao.executeQuery(strSql);
            ArrayList<Anuncio> listaRetorno = new ArrayList();

            Anuncio anuncio;
            while(resultadoQuery.next()){
                anuncio = getAnuncio(resultadoQuery);
                listaRetorno.add(anuncio);
            }

            declaracao.close();
            conexao.close();
            return listaRetorno;
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }

    private static Anuncio getAnuncio(ResultSet resultadoQuery) throws SQLException {
        // TODO: Verificar forma para converter a linha da tabela diretamente para Anuncio.

        Anuncio anuncio = new Anuncio();
        anuncio.setId(resultadoQuery.getInt("ID_Anuncio"));
        anuncio.setTitulo(
                UtilsBD.GetString(resultadoQuery.getString("Titulo_Anuncio"))
        );
        anuncio.setDescricao(
                UtilsBD.GetString(resultadoQuery.getString("Descricao_Anuncio"))
        );
        anuncio.setDataEdicao(
                Utils.converteDateToGregorianCalendar(
                        resultadoQuery.getDate("Edicao_Anuncio"))
        );
        anuncio.setDataCriacao(
                Utils.converteDateToGregorianCalendar(
                        resultadoQuery.getDate("Criacao_Anuncio"))
        );
        anuncio.setPreco(
                resultadoQuery.getString("Preco_Anuncio")
        );
        anuncio.setTipoAnuncio(
                TipoAnuncio.valueOf(UtilsBD.GetString(
                        resultadoQuery.getString("Tipo_Anuncio").trim()))
        );
        anuncio.setDisponibilidade(
                resultadoQuery.getBoolean("Disponibilidade_Anuncio")
        );
        anuncio.setCriador(
                Confeiteiro_DAO.GetConfeiteiro(
                        resultadoQuery.getInt("Criador_Anuncio"))
        );

        return anuncio;
    }
}
