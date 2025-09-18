package org.example.httphandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        UsersController usersController = new UsersController();
        System.out.println("Users Controller");
        usersController.addNewUser();
        System.out.println("-------------");
        usersController.updateUser(5L);
        System.out.println("-------------");
        usersController.deleteUser(5L);
        System.out.println("-------------");
        usersController.getUsers();
        System.out.println("-------------");
        usersController.getUser(5L);
        System.out.println("-------------");
        usersController.getUserByUsername("Elwyn.Skiles");

    }
}
