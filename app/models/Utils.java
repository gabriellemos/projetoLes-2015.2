package models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Gabriel on 08/04/2016.
 */
public class Utils {

    private static final String STR_VAZIO = "";

    /*
     * Verifica se a string recebida para validação é Null, vazia ou com contendo apenas espaços em branco.
     */
    public static boolean checaStringValida(String strParaValidacao) {
        if (strParaValidacao == null || STR_VAZIO.equals(strParaValidacao.trim())){
            return false;
        }
        return true;
    }

    /*
     * @return  Data de hoje.
     */
    public static GregorianCalendar getHoje() {
        GregorianCalendar hoje = new GregorianCalendar();
        hoje.set(Calendar.HOUR_OF_DAY, 0);
        hoje.set(Calendar.MINUTE, 0);
        hoje.set(Calendar.SECOND, 0);
        hoje.set(Calendar.MILLISECOND, 0);

        return hoje;
    }

    /*
     * Verifica se a data é anterior ao dia de hoje.
     * @return: False se a data for anterior ao dia de hoje, true caso contrário.
     */
    public static boolean dataValida(GregorianCalendar dataParaValidacao) {
        return dataValida(dataParaValidacao, getHoje());
    }

    /*
     * Aqui o método verifica se a data é anterior a uma 'dataLimite' passada como parâmetro.
     * @param   dataParaValidacao: Data para validação.
     * @param   dataLimite: Data pela qual será feita a validação.
     * @return: False se a data for anterior ao dia de hoje, true caso contrário.
     */
    public static boolean dataValida(GregorianCalendar dataParaValidacao,
                                     GregorianCalendar dataLimite) {
        if (dataParaValidacao != null && dataParaValidacao.compareTo(dataLimite) > 0) {
            return false;
        }
        return true;
    }

    public static GregorianCalendar converteDateToGregorianCalendar(Date data) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(data);
        return calendar;
    }
}
