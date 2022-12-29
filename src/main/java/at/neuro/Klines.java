package at.neuro;

import java.io.IOException;
import java.net.URISyntaxException;

import static at.neuro.constant.Urls.URLDATA;

public class Klines {
    private static String url = "/api/v3/klines";
    public static String symbol;
    public static String interval;
    public static int limit = 500;

    public static void klinesInit() throws URISyntaxException, IOException, InterruptedException {
        String params = "?symbol=" + symbol;
        params += "&interval=" + interval;
        params += "&limit=" + limit;
        ApiClient.uri = URLDATA+url+params;
        ApiClient.apiInit();
    }
}
