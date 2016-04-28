package models.dao.Tabelas;

import java.sql.Statement;

/**
 * Created by jordan on 08/04/2016.
 */


public class Anuncio_Tabela {
    private static java.sql.Connection conexao= null;
    private static Statement declaracao = null;
    private static  String str;

    //NAO EXECUTE ESSA CLASSE OU VAI GERA UM ERRO DIZENDO QUE A TABELA JA FOI CRIADA
    public static void main( String args[] ) throws Exception {

        try{
            conexao= models.dao.Connection.getConnection();
            declaracao= conexao.createStatement();

            str = "CREATE TABLE Anuncio " +
                    "(ID_Anuncio SERIAL PRIMARY KEY," +
                    "Titulo_Anuncio         CHAR(32)    NOT NULL, " +
                    "Descricao_Anuncio      CHAR(256), " +
                    "Edicao_Anuncio         DATE, " +
                    "Criacao_Anuncio        DATE NOT NULL,"  +
                    "Preco_Anuncio          MONEY DEFAULT 0.00 NOT NULL,"  + // Default 0.0
                    "Criador_Anuncio        INT references Confeiteiro(ID_Confeiteiro),"  +  // Chave estrangeira
                    "Tipo_Anuncio           CHAR(16) NOT NULL )";


            //str="ALTER TABLE Anuncio ADD Disponibilidade BOOLEAN DEFAULT TRUE";
            declaracao.executeUpdate(str);
            declaracao.close();
            conexao.close();
            System.out.print("Sucesso");}
        catch (Exception e){
            System.out.print(e.getMessage());

        }

    }
}
