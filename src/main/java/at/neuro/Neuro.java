package at.neuro;

import java.io.IOException;
import java.net.URISyntaxException;

import static at.neuro.constant.Urls.URLDATA;

public class Neuro {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
//        Klines.symbol = "TRXUSDT";
//        Klines.interval = "1h";
//        Klines.limit = 100;
//        Klines.klinesInit();

//        System.out.println(ApiClient.statusCode);
//        System.out.println(ApiClient.body);

        Plot.x = new double[]{3.3,4.8,5.5,4.6,3.3,5.4};
        Plot.y = new double[]{2.1,1.7,2.4,3.6,4.3,5.2};
        Plot.plotInit();
    }
}
