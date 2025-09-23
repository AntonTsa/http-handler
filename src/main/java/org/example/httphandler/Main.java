package org.example.httphandler;

public class Main {
    private static final String TASKS_DELIMITER = "///////////////////////////////////";
    private static final String PART_DELIMITER = "-------------------------------";

    public static void main(String[] args) {
        printUsers();
        System.out.println(TASKS_DELIMITER);
        printPosts();
        System.out.println(TASKS_DELIMITER);
        printTodos();

    }

    private static void printTodos() {
        TodosController controller = new TodosController();
        controller.getOpenedTodos(4L);
    }

    private static void printPosts() {
        PostsController postsController = new PostsController();
        postsController.getCommentsToLastPost(3L);
    }

    private static void printUsers() {
        UsersController usersController = new UsersController();
        System.out.println("Users Controller");
        usersController.addNewUser();
        System.out.println(PART_DELIMITER);
        usersController.updateUser(5L);
        System.out.println(PART_DELIMITER);
        usersController.deleteUser(5L);
        System.out.println(PART_DELIMITER);
        usersController.getUsers();
        System.out.println(PART_DELIMITER);
        usersController.getUser(5L);
        System.out.println(PART_DELIMITER);
        usersController.getUserByUsername("Elwyn.Skiles");
    }
}
