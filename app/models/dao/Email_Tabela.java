package models.dao;

import java.sql.Statement;

/**
 * Created by jordan on 20/04/2016.
 */
public class Email_Tabela {


    private static java.sql.Connection conexao= null;
    private static Statement declaracao = null;
    private static  String str;
    //NAO EXECUTE ESSA CLASSE OU VAI GERA UM ERRO DIZENDO QUE A TABELA JA FOI CRIADA
    public static void main( String args[] ) throws Exception {

        try{
            conexao= models.dao.Connection.getConnection();
            declaracao= conexao.createStatement();

            str = "CREATE TABLE Email" +
                    "(ID_Email SERIAL PRIMARY KEY," +
                    "Email          CHAR(128) NOT NULL,"  +
                    "Dono_Email        INT references Confeiteiro(ID_Confeiteiro))"; // Chave estrangeira

           // str="DROP TABLE Email;";
            declaracao.executeUpdate(str);
            declaracao.close();
            conexao.close();
            System.out.print("Sucesso");}
        catch (Exception e){
            System.out.print(e.getMessage());

        }

    }


}
