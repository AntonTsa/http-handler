package org.example.httphandler;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.Optional;

public class HttpRequestSender {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public Optional<HttpResponse<String>> getResponseToString(HttpRequest request) {
        try {
            return Optional.of(httpClient.send(request, HttpResponse.BodyHandlers.ofString()));
        } catch (IOException | InterruptedException e) {
            System.err.println(e.getMessage());
            Thread.currentThread().interrupt();
        }

        return Optional.empty();
    }

    public void getResponseToFile(HttpRequest request, String pathToFile) {
        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get(pathToFile)));

        } catch (InterruptedException | IOException e) {
            System.err.println(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
