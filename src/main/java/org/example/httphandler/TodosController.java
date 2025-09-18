package org.example.httphandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TodosController {
    private static final String TODOS = "todos";
    private static final String DELIMITER = "/";
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public void getOpenedTodos(long userId) throws IOException, InterruptedException {
        String pathToTodos = USERS_URL + DELIMITER + userId + DELIMITER + TODOS;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(pathToTodos))
                .GET()
                .build();

        HttpResponse<String> response =
                httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        filterOpenedTodos(response.body());
    }

    private void filterOpenedTodos(String todos) {
        JsonArray todosArray = JsonParser.parseString(todos).getAsJsonArray();
        todosArray.asList().stream()
                .filter(todo -> {
                    JsonObject todoJson = todo.getAsJsonObject();
                    return !todoJson.get("completed").getAsBoolean();
                })
                .forEach(System.out::println);
    }
}
