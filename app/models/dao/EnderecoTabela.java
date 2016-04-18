package models.dao;

import java.sql.Statement;

/**
 * Created by jordan on 18/04/2016.
 */
public class EnderecoTabela {

    private static java.sql.Connection conexao= null;
    private static Statement declaracao = null;
    private static  String str;
    //NAO EXECUTE ESSA CLASSE OU VAI GERA UM ERRO DIZENDO QUE A TABELA JA FOI CRIADA
    public static void main( String args[] ) throws Exception {

        try{
            conexao= models.dao.Connection.getConnection();
            declaracao= conexao.createStatement();

            str = "CREATE TABLE Endereco " +
                    "(ID_Endereco SERIAL PRIMARY KEY," +
                    "Rua         CHAR(64)    NOT NULL, " +
                    "Numero      CHAR(8) NOT NULL, " +
                    "Bairro         CHAR(16) NOT NULL, " +
                    "cidade       CHAR(32) NOT NULL,"  +
                    "Estado          CHAR(24) NOT NULL,"  +
                    "Dono_Endereco        INT references Confeiteiro(ID_Confeiteiro),"  +  // Chave estrangeira
                    "Cep          CHAR(16) NOT NULL )";



            declaracao.executeUpdate(str);
            declaracao.close();
            conexao.close();
            System.out.print("Sucesso");}
        catch (Exception e){
            System.out.print(e.getMessage());

        }

    }
}
