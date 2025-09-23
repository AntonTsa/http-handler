package org.example.httphandler;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.nio.file.Paths;

public class UsersController {
    private static final String DIR_PATH = "./src/main/resources/";
    private static final String DIR_NAME = "users";
    private static final String DELIMITER = "/";
    private static final String FILE_PATH = DIR_PATH + DIR_NAME + DELIMITER;
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String USER_URL = USERS_URL + DELIMITER;
    private static final String CODE = "Code: ";
    private static final String BODY = "Body: ";
    private final HttpRequestSender sender = new HttpRequestSender();

    public void addNewUser() {
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(USERS_URL))
                    .header("Content-Type", "application/json")
                    .POST(
                            HttpRequest.BodyPublishers
                                    .ofFile(Paths.get(FILE_PATH + "create_user.json")))
                    .build();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return;
        }

        var response =
                sender.getResponseToString(request);

        if (response.isPresent()) {
            System.out.println(CODE + response.get().statusCode());
            System.out.println("Location Header: ");
            response.get().headers().firstValue("Location").ifPresent(System.out::println);
        }
    }

    public void updateUser(long id) {
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(USER_URL + id))
                    .header("Content-Type", "application/json")
                    .PUT(
                            HttpRequest.BodyPublishers
                                    .ofFile(Paths.get(FILE_PATH + "update_user.json")))
                    .build();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            return;
        }

        var response = sender.getResponseToString(request);

        if (response.isPresent()) {
            System.out.println(CODE + response.get().statusCode());
            System.out.println(BODY);
            System.out.println(response.get().body());
            System.out.println();
        }
    }

    public void deleteUser(long id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USER_URL + id))
                .DELETE()
                .build();

        var response = sender.getResponseToString(request);

        response.ifPresent(httpResponse -> System.out.println(CODE + response.get().statusCode()));
    }

    public void getUsers() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL))
                .GET()
                .build();

        var response = sender.getResponseToString(request);

        if (response.isPresent()) {
            System.out.println(CODE + response.get().statusCode());
            System.out.println(BODY);
            System.out.println(response.get().body());
            System.out.println();
        }
    }

    public void getUser(long id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USER_URL + id))
                .GET()
                .build();

        var response = sender.getResponseToString(request);

        if (response.isPresent()) {
            System.out.println(CODE + response.get().statusCode());
            System.out.println(BODY);
            System.out.println(response.get().body());
            System.out.println();
        }
    }

    public void getUserByUsername(String username) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL + "?username=" + username))
                .GET()
                .build();

        var response = sender.getResponseToString(request);

        if (response.isPresent()) {
            System.out.println(CODE + response.get().statusCode());
            System.out.println(BODY);
            System.out.println(response.get().body());
            System.out.println();
        }
    }
}
