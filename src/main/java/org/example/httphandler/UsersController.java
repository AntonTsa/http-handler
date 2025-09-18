package org.example.httphandler;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;

public class UsersController {
    private static final String DIR_PATH = "./src/main/resources/";
    private static final String DIR_NAME = "users";
    private static final String DELIMITER = "/";
    private static final String FILE_PATH = DIR_PATH + DIR_NAME + DELIMITER;
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String USER_URL = USERS_URL + DELIMITER;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void addNewUser() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get(FILE_PATH + "create_user.json")))
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Code: " + response.statusCode());
        System.out.println("Location Header: ");
        response.headers().firstValue("Location").ifPresent(System.out::println);
    }

    public void updateUser(long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USER_URL + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofFile(Paths.get(FILE_PATH + "update_user.json")))
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Code: " + response.statusCode());
        System.out.println("Body: ");
        System.out.println(response.body());
        System.out.println();
    }

    public void deleteUser(long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USER_URL + id))
                .DELETE()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Code: " + response.statusCode());
    }

    public void getUsers() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL))
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Code: " + response.statusCode());
        System.out.println("Body: ");
        System.out.println(response.body());
        System.out.println();
    }

    public void getUser(long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USER_URL + id))
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Code: " + response.statusCode());
        System.out.println("Body: ");
        System.out.println(response.body());
        System.out.println();
    }

    public void getUserByUsername(String username) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(USERS_URL + "?username=" + username))
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Code: " + response.statusCode());
        System.out.println("Body: ");
        System.out.println(response.body());
        System.out.println();
    }
}
