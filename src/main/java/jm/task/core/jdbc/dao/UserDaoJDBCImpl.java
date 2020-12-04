package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=UTC&useSSL=false";
    private static final String USERNAME = "nazee";
    private static final String PASSWORD = "Antanta22!";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS  mydbtest.users (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  name VARCHAR(45) NOT NULL,\n" +
                    "  lastname VARCHAR(45) NOT NULL,\n" +
                    "  age INT(3) NOT NULL,\n" +
                    "  PRIMARY KEY (id));");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS users;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format("INSERT INTO users (name, lastname, age) values ('%s', '%s', %s);", name, lastName, age));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(String.format("DELETE FROM users WHERE id=%s;", id));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            List<User> list = new LinkedList<>();
            ResultSet resultSet = statement.executeQuery("SELECT  * from users");
            while (resultSet.next()) {
                User newUser = new User(resultSet.getString(2), resultSet.getString(3), (byte) resultSet.getInt(4));
                newUser.setId((long) resultSet.getInt(1));
                list.add(newUser);
            }
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users;");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
