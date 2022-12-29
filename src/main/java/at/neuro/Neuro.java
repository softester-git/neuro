package at.neuro;

import java.io.IOException;
import java.net.URISyntaxException;

import static at.neuro.constant.Urls.URLDATA;

public class Neuro {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        String method = "/api/v3/klines";
        //String method = "/api/v3/exchangeInfo";
        String params = "?symbol=TRXUSDT";
        params += "&interval=1h";
        params += "&limit=10";
        ApiClient.uri = URLDATA+method+params;
        ApiClient.apiInit();

        System.out.println(ApiClient.statusCode);
        System.out.println(ApiClient.body);
    }
}
