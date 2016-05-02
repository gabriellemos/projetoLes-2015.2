package models.dao;

import models.Anuncio;
import models.Confeiteiro;
import models.TipoAnuncio;
import models.Utils;

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
    private static final String SEPARADOR = "','";

    /*
     * Cria um Anúncio no banco de dados. Um id é atribuido automaticamente no ato da criação.
     */
    public static void insertAnuncio (String titulo, String descricao, GregorianCalendar dataEdicao,
                                      float preco, int criador, String tipo) throws RequisicaoInvalidaBD {

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
            strSql= "UPDATE  Anuncio SET  Disponibilidade = '"+boll+"' WHERE ID_Anuncio = '"+id+"';";
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

    public static  void ModificarAtributosDeAnuncio (String titulo, String descricao, float preco, String tipo, int id){

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

    public static  void ModificarAtributosDeAnuncio (String titulo, String descricao, float preco, String tipo, Anuncio anuncio){

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
            strSql = "SELECT * FROM Anuncio WHERE  Disponibilidade = TRUE ORDER BY Edicao_Anuncio ASC;";
            resultadoQuery = declaracao.executeQuery(strSql);
            Anuncio anuncio;
            ArrayList<Anuncio> listaRetorno = new ArrayList();
            // TODO: Verificar forma para converter a linha da tabela diretamente para Anuncio.
            while(resultadoQuery.next()){
                anuncio = new Anuncio();
                anuncio.setId(resultadoQuery.getInt("Id_Anuncio"));
                anuncio.setTitulo(
                        UtilsBD.GetString(resultadoQuery.getString("titulo_anuncio"))
                );
                anuncio.setDescricao(
                        UtilsBD.GetString(resultadoQuery.getString("descricao_anuncio"))
                );
                anuncio.setDataCriacao(
                        Utils.converteDateToGregorianCalendar(
                                resultadoQuery.getDate("criacao_anuncio"))
                );
                anuncio.setDataEdicao(
                        Utils.converteDateToGregorianCalendar(
                                resultadoQuery.getDate("edicao_anuncio"))
                );
                anuncio.setPreco(
                        resultadoQuery.getFloat("Preco_Anuncio")
                );
                anuncio.setCriador(
                        Confeiteiro_DAO.GetConfeiteiro(
                                resultadoQuery.getInt("criador_anuncio"))
                );
                anuncio.setTipoAnuncio(
                        TipoAnuncio.valueOf(UtilsBD.GetString(
                                resultadoQuery.getString("tipo_anuncio").trim()))
                );
                listaRetorno.add(anuncio);
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


    public static ArrayList<Anuncio> getAnunciosPelaCidadeEData(String cidade, GregorianCalendar data) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            java.sql.Date dataEdicaoBD;
            dataEdicaoBD = new java.sql.Date(data.getTimeInMillis());
            strSql = "SELECT * FROM Anuncio WHERE Edicao_Anuncio = '"+ data+"' AND Disponibilidade = TRUE AND criador_anuncio IN (SELECT Dono_Endereco FROM  Endereco  WHERE cidade  = '"+cidade+"')" +
                    " ORDER BY Titulo_Anuncio ASC;";
            resultadoQuery = declaracao.executeQuery(strSql);
            Anuncio anuncio;
            ArrayList<Anuncio> listaRetorno = new ArrayList();
            // TODO: Verificar forma para converter a linha da tabela diretamente para Anuncio.
            while(resultadoQuery.next()){
                anuncio = new Anuncio();
                anuncio.setId(
                        resultadoQuery.getInt("Id_Anuncio")
                );
                anuncio.setTitulo(
                        UtilsBD.GetString(resultadoQuery.getString("titulo_anuncio"))
                );
                anuncio.setDescricao(
                        UtilsBD.GetString(resultadoQuery.getString("descricao_anuncio"))
                );
                anuncio.setDataCriacao(
                        Utils.converteDateToGregorianCalendar(
                                resultadoQuery.getDate("criacao_anuncio"))
                );
                anuncio.setDataEdicao(
                        Utils.converteDateToGregorianCalendar(
                                resultadoQuery.getDate("edicao_anuncio"))
                );
                anuncio.setPreco(
                        resultadoQuery.getFloat("Preco_Anuncio")
                );
                anuncio.setCriador(
                        Confeiteiro_DAO.GetConfeiteiro(resultadoQuery.getInt("criador_anuncio"))
                );
                anuncio.setTipoAnuncio(
                        TipoAnuncio.valueOf(
                                UtilsBD.GetString(resultadoQuery.getString("tipo_anuncio"))));
                listaRetorno.add(anuncio);
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

    /*
     * Retorna um objeto Anúncio de acordo com o ID no banco de dados.
     */
    public static Anuncio getAnuncio(int ID) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultado;
            Anuncio resposta= new Anuncio();
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio a WHERE a.id_anuncio='" + ID + "' AND Disponibilidade = TRUE;";
            resultado = declaracao.executeQuery(strSql);

            // TODO: Verificar forma para converter a linha da tabela diretamente para Anuncio.
            resposta.setId(
                    resultado.getInt("id_anuncio")
            );
            resposta.setTitulo(
                    UtilsBD.GetString(resultado.getString("titulo_anuncio"))
            );
            resposta.setDescricao(
                    UtilsBD.GetString(resultado.getString("descricao_anuncio"))
            );
            resposta.setDataEdicao(
                    Utils.converteDateToGregorianCalendar(resultado.getDate("edicao_anuncio")
            ));
            resposta.setDataCriacao(
                    Utils.converteDateToGregorianCalendar(resultado.getDate("criacao_anuncio")
            ));
            resposta.setPreco(
                    resultado.getFloat("preco_anuncio")
            );
            resposta.setCriador(
                    Confeiteiro_DAO.GetConfeiteiro(resultado.getInt("criador_anuncio")
            ));
            resposta.setTipoAnuncio(
                    TipoAnuncio.valueOf(UtilsBD.GetString(resultado.getString("tipo-anuncio"))
            ));
            //ate aqui
            declaracao.close();
            conexao.close();
            return resposta;
            // Verifica a exceção do bd
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
            strSql = "SELECT * FROM Anuncio WHERE  Disponibilidade = TRUE AND criador_anuncio ='"+IdConfeiteiro+"'" +
                    " ORDER BY Titulo_Anuncio ASC;";
            resultadoQuery = declaracao.executeQuery(strSql);
            Anuncio anuncio;
            ArrayList<Anuncio> listaRetorno = new ArrayList();
            // TODO: Verificar forma para converter a linha da tabela diretamente para Anuncio.
            while(resultadoQuery.next()){
                anuncio = new Anuncio();
                anuncio.setId(resultadoQuery.getInt("Id_Anuncio"));
                anuncio.setTitulo(
                        UtilsBD.GetString(resultadoQuery.getString("titulo_anuncio"))
                );
                anuncio.setDescricao(
                        UtilsBD.GetString(resultadoQuery.getString("descricao_anuncio"))
                );
                anuncio.setDataCriacao(
                        Utils.converteDateToGregorianCalendar(
                                resultadoQuery.getDate("criacao_anuncio"))
                );
                anuncio.setDataEdicao(
                        Utils.converteDateToGregorianCalendar(
                                resultadoQuery.getDate("edicao_anuncio"))
                );
                anuncio.setPreco(
                        resultadoQuery.getFloat("Preco_Anuncio")
                );
                anuncio.setCriador(
                        Confeiteiro_DAO.GetConfeiteiro(resultadoQuery.getInt("criador_anuncio"))
                );
                anuncio.setTipoAnuncio(
                        TipoAnuncio.valueOf(
                                UtilsBD.GetString(resultadoQuery.getString("tipo_anuncio")))
                );
                listaRetorno.add(anuncio);
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

    public static  void getAnunciosPeloConfeiteiro(Confeiteiro confeiteiro) throws RequisicaoInvalidaBD{
        try {
            ResultSet resultadoQuery;
            conexao = Connection.getConnection();
            declaracao = conexao.createStatement();
            strSql = "SELECT * FROM Anuncio WHERE  Disponibilidade = TRUE AND criador_anuncio ='"+ confeiteiro.getId()+"'" +
                    " ORDER BY Titulo_Anuncio ASC;";
            resultadoQuery = declaracao.executeQuery(strSql);
            Anuncio anuncio;

            // TODO: Verificar forma para converter a linha da tabela diretamente para Anuncio.
            while(resultadoQuery.next()){
                anuncio = new Anuncio();
                anuncio.setId(
                        resultadoQuery.getInt("Id_Anuncio")
                );
                anuncio.setTitulo(
                        UtilsBD.GetString(resultadoQuery.getString("titulo_anuncio"))
                );
                anuncio.setDescricao(
                        UtilsBD.GetString(resultadoQuery.getString("descricao_anuncio"))
                );
                anuncio.setDataCriacao(
                        Utils.converteDateToGregorianCalendar(
                                resultadoQuery.getDate("criacao_anuncio"))
                );
                anuncio.setDataEdicao(
                        Utils.converteDateToGregorianCalendar(
                                resultadoQuery.getDate("edicao_anuncio"))
                );
                anuncio.setPreco(
                        resultadoQuery.getFloat("Preco_Anuncio")
                );
                anuncio.setCriador(
                        Confeiteiro_DAO.GetConfeiteiro(resultadoQuery.getInt("criador_anuncio"))
                );
                anuncio.setTipoAnuncio(
                        TipoAnuncio.valueOf(
                                UtilsBD.GetString(resultadoQuery.getString("tipo_anuncio")))
                );
                confeiteiro.addAnuncio(anuncio);
            }
            declaracao.close();
            conexao.close();
            // Verifica a exceção do bd
        } catch (Exception e) {
            RequisicaoInvalidaBD exception = new RequisicaoInvalidaBD(e.getMessage());
            exception.setStackTrace(e.getStackTrace());
            throw exception;
        }
    }
}
