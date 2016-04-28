package models.dao.Tabelas;

import java.sql.Statement;


/**
 * Created by jordan on 08/04/2016.
 */
public class Confeiteiro_Tabela {

    private static java.sql.Connection conexao= null;
    private static Statement declaracao = null;
    private static  String str;

    //NAO EXECUTE ESSA CLASSE OU VAI GERA UM ERRO DIZENDO QUE A TABELA JA FOI CRIADA
    public static void main( String args[] ) throws Exception {

        try{
            conexao= models.dao.Connection.getConnection();
            declaracao= conexao.createStatement();

            str = "CREATE TABLE Confeiteiro " +
                    "(ID_Confeiteiro SERIAL PRIMARY KEY," +
                    " Nome_Confeiteiro           CHAR(32)   NOT NULL, " +
                    " Email_Confeiteiro     CHAR(64)     NOT NULL, " +
                    " Endereco_Confeiteiro        CHAR(128)  NOT NULL, " +
                    " Contato_Confeiteiro         CHAR(16) NOT NULL,"  +
                    "ID_Facebook            CHAR(64)     UNIQUE NOT NULL )";


           // str="ALTER TABLE Confeiteiro DROP COLUMN Email_Confeiteiro ;";
            declaracao.executeUpdate(str);
            declaracao.close();
            conexao.close();
            System.out.print("Sucesso");}
        catch (Exception e){
             System.out.print(e.getMessage());

        }

    }

}