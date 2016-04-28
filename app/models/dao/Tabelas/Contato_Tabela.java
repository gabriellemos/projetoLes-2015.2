package models.dao.Tabelas;

import java.sql.Statement;

/**
 * Created by jordan on 20/04/2016.
 */
public class Contato_Tabela {

    private static java.sql.Connection conexao= null;
    private static Statement declaracao = null;
    private static  String str;
    //NAO EXECUTE ESSA CLASSE OU VAI GERA UM ERRO DIZENDO QUE A TABELA JA FOI CRIADA
    public static void main( String args[] ) throws Exception {

        try{
            conexao= models.dao.Connection.getConnection();
            declaracao= conexao.createStatement();

            str = "CREATE TABLE Contato " +
                    "(ID_Contato SERIAL PRIMARY KEY," +
                    "Numero_Contato         CHAR(16)    NOT NULL, " +
                    "Codigo_Estado      CHAR(4) NOT NULL, " +
                    "Codigo_Operadora         CHAR(4) , " +
                    "Codigo_Pais       CHAR(4) ,"  +
                    "Dono_Contato        INT references Confeiteiro(ID_Confeiteiro))";    // Chave estrangeira



            declaracao.executeUpdate(str);
            declaracao.close();
            conexao.close();
            System.out.print("Sucesso");}
        catch (Exception e){
            System.out.print(e.getMessage());

        }

    }
}
