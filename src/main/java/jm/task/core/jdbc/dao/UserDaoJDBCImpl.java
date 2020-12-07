package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "nazee";
    private static final String PASSWORD = "Antanta22!";
    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS  mydbtest.users (\n" +
                "  id INT NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(45) NOT NULL,\n" +
                "  lastname VARCHAR(45) NOT NULL,\n" +
                "  age INT(3) NOT NULL,\n" +
                "  PRIMARY KEY (id));";
        runSql(sql);
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        runSql(sql);
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = String.format("INSERT INTO users (name, lastname, age) values ('%s', '%s', %s);", name, lastName, age);
        runSql(sql);
    }

    public void removeUserById(long id) {
        String sql = (String.format("DELETE FROM users WHERE id=%s;", id));
        runSql(sql);
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users;";
        runSql(sql);
    }


    public List<User> getAllUsers() {
        Connection connection = null;
        Statement statement  = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM users";
        List<User> list = new ArrayList<>();
        try {
            connection = util.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
            connection.commit();
            System.out.println("List of Users created");
        } catch (SQLException exception) {
            try{
                if(connection != null){
                    connection.rollback();
                }
            } catch (SQLException exceptionRollback){
                exceptionRollback.printStackTrace();
            }
            exception.printStackTrace();
        } finally {
            try{
                if(connection != null) {
                    connection.close();
                }
                if(statement != null) {
                    statement.close();
                }
                if(resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException exceptionFinally) {
                exceptionFinally.printStackTrace();
            }
        }
        return list;
    }

    private void runSql(String sql) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = util.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException exception) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException exceptionRollback) {
                exceptionRollback.printStackTrace();
            }
            exception.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException exceptionFinally) {
                exceptionFinally.printStackTrace();
            }
        }
    }
}
