package dao;

import java.sql.SQLException;
import models.dao.Connection;

/**
 * Created by Jordan on 07/04/2016.
 */
public class TestConnection {

    public static void main(String[] args) {

        java.sql.Connection connection = null;
        try {
            connection = Connection.getConnection();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

    }
}