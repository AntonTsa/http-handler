package org.example.httphandler;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpRequest;

public class PostsController {
    private static final String DIR_PATH = "./src/main/resources/";
    private static final String POSTS = "posts";
    private static final String DELIMITER = "/";
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final String COMMENTS = "comments";
    private final HttpRequestSender sender = new HttpRequestSender();

    public void getCommentsToLastPost(long userId) {
        String pathToPosts = USERS_URL + DELIMITER + userId + DELIMITER + POSTS;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(pathToPosts))
                .GET()
                .build();

        var response = sender.getResponseToString(request);

        if (response.isPresent()) {
            int lastPostId = getMaxPostId(response.get().body());
            getComments(userId, lastPostId);
        }
    }

    private int getMaxPostId(String answer) {
        JsonArray posts = JsonParser.parseString(answer).getAsJsonArray();
        return posts.asList().stream()
                .map(element -> {
                    JsonObject post = element.getAsJsonObject();
                    return post.get("id").getAsInt();
                })
                .max(Integer::compareTo)
                .orElse(-1);
    }

    private void getComments(long userId, long postId) {
        String commentsUrl = POSTS_URL + DELIMITER + postId + DELIMITER + COMMENTS;
        String pathToFile = DIR_PATH + POSTS + DELIMITER
                + "user-" + userId + "-post-" + postId + "-comments.json";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(commentsUrl))
                .GET()
                .build();

        sender.getResponseToFile(request, pathToFile);
    }

}
