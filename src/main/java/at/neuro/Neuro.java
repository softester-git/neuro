package at.neuro;

import java.io.IOException;
import java.net.URISyntaxException;

import static at.neuro.constant.Urls.URLDATA;

public class Neuro {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Klines.symbol = "TRXUSDT";
        Klines.interval = "1h";
        Klines.limit = 100;
        Klines.klinesInit();

        System.out.println(ApiClient.statusCode);
        System.out.println(ApiClient.body);
    }
}
