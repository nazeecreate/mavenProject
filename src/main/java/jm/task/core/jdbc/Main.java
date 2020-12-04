package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("user1", "smith", (byte) 25);
        System.out.println("User с именем – user1 добавлен в базу данных");
        service.saveUser("user2", "smith", (byte) 25);
        System.out.println("User с именем – user2 добавлен в базу данных");
        service.saveUser("user3", "smith", (byte) 25);
        System.out.println("User с именем – user3 добавлен в базу данных");
        service.saveUser("user4", "smith", (byte) 25);
        System.out.println("User с именем – user4 добавлен в базу данных");
        for (User user : service.getAllUsers()) {
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
