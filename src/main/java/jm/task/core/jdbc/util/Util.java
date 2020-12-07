package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "nazee";
    private static final String PASSWORD = "Antanta22!";

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
