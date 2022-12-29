package at.neuro;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import  java.net.http.WebSocket;

public class ApiClient {

    private static HttpClient client;
    private static HttpRequest request;
    private static HttpResponse response;
    public static String uri;
    public static int statusCode;
    public static String body;

    public static void apiInit() throws URISyntaxException, IOException, InterruptedException {
        apiClient();
        apiRequest();
        apiResponse();
    }

    public static void apiClient() {
        client = HttpClient.newHttpClient();
    }

    public static void apiRequest() throws URISyntaxException {
        request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();
    }

    public static void apiResponse() throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        statusCode = response.statusCode();
        body = response.body();
    }
}
