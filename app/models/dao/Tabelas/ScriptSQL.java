package models.dao.Tabelas;

/**
 * Created by Gabriel on 02/05/2016.
 */
public class ScriptSQL {

    public static String getCreateConfeiteiro() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE Confeiteiro ( ");
        sqlBuilder.append("ID_Confeiteiro\t\tSERIAL\t\tPRIMARY KEY, ");
        sqlBuilder.append("Nome_Confeiteiro\tCHAR(32)\tNOT NULL, ");
        sqlBuilder.append("ID_Facebook\t\t\tCHAR(128)\tUNIQUE NOT NULL");
        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }

    public static String getCreateContato() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE Contato (" );
        sqlBuilder.append("ID_Contato\t\t\tSERIAL\t\tPRIMARY KEY, ");
        sqlBuilder.append("Numero_Contato\t\tCHAR(16)\tNOT NULL, ");
        sqlBuilder.append("Codigo_Estado\t\tCHAR(4)\t\tNOT NULL, ");
        sqlBuilder.append("Codigo_Operadora\tCHAR(4), ");
        sqlBuilder.append("Codigo_Pais\t\t\tCHAR(4), ");
        sqlBuilder.append("Dono_Contato\t\tINT\t\t\treferences Confeiteiro(ID_Confeiteiro)");
        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }

    public static String getCreateEmail() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE Email ( ");
        sqlBuilder.append("ID_Email\t\t\tSERIAL\t\tPRIMARY KEY, ");
        sqlBuilder.append("Email\t\t\t\tCHAR(128)\tNOT NULL, ");
        sqlBuilder.append("Dono_Email\t\t\tINT\t\t\treferences Confeiteiro(ID_Confeiteiro)");
        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }

    public static String getCreateEndereco() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE Endereco ( ");
        sqlBuilder.append("ID_Endereco\t\t\tSERIAL\t\tPRIMARY KEY, ");
        sqlBuilder.append("Rua\t\t\t\t\tCHAR(64)\tNOT NULL, ");
        sqlBuilder.append("Numero\t\t\t\tCHAR(8)\t\tNOT NULL, ");
        sqlBuilder.append("Bairro\t\t\t\tCHAR(16)\tNOT NULL, ");
        sqlBuilder.append("cidade\t\t\t\tCHAR(32)\tNOT NULL, ");
        sqlBuilder.append("Estado\t\t\t\tCHAR(24)\tNOT NULL, ");
        sqlBuilder.append("Cep\t\t\t\t\tCHAR(16)\tNOT NULL, ");
        sqlBuilder.append("Dono_Endereco\t\tINT\t\t\treferences Confeiteiro(ID_Confeiteiro)");
        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }

    public static String getCreateAnuncio() {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE Anuncio ( ");
        sqlBuilder.append("ID_Anuncio\t\t\tSERIAL\t\tPRIMARY KEY, ");
        sqlBuilder.append("Titulo_Anuncio\t\tCHAR(32)\tNOT NULL, ");
        sqlBuilder.append("Descricao_Anuncio\tCHAR(256), ");
        sqlBuilder.append("Edicao_Anuncio\t\tDATE, ");
        sqlBuilder.append("Criacao_Anuncio\t\tDATE \t\tNOT NULL, ");
        sqlBuilder.append("Preco_Anuncio\t\tMONEY\t\tDEFAULT 0.00\tNOT NULL, ");
        sqlBuilder.append("Tipo_Anuncio\t\tCHAR(16)\tNOT NULL, ");
        sqlBuilder.append("Disponibilidade_Anuncio\t\tBOOLEAN\t\tDEFAULT TRUE\tNOT NULL, ");
        sqlBuilder.append("Criador_Anuncio\t\tINT\t\t\treferences Confeiteiro(ID_Confeiteiro)");
        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }

}
