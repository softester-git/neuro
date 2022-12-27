package at.neuro;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import  java.net.http.HttpResponse;
import  java.net.http.WebSocket;

public class ApiClient {

    private static HttpClient client;
    private static HttpRequest request;
    private static HttpResponse response;

    public static void apiRequest(String uri) throws URISyntaxException {
        request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();
    }

    public static void apiResponse() {
        //response = client.send(request, HttpResponse.BodyHandler.asString());
    }
}
