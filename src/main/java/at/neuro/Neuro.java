package at.neuro;

import java.io.IOException;
import java.net.URISyntaxException;

public class Neuro {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String baseUrl = "https://www.binance.com/api/";
        ApiClient.uri = baseUrl;
        ApiClient.apiInit();

        System.out.println(ApiClient.statusCode);
        System.out.println(ApiClient.body);
    }
}
