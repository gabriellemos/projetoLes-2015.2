package models.dao;

/**
 * Created by Gabriel on 25/04/2016.
 */
public class UtilsBD {

    public static String GetString(String str) {
        return str.replaceAll("\\s+$", "");
    }

}
