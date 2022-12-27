package at.neuro;

import java.net.URISyntaxException;
import static at.neuro.ApiClient.apiRequest;

public class Neuro {
    //private static BinanceApi api = new BinanceApi();
    public static void main(String[] args) throws URISyntaxException {
        // init
        String baseUrl = "https://www.binance.com/api/";
        apiRequest(baseUrl);


        //getAccountInfo();
        //BigDecimal asset = api.pricesMap().get("TRXUSDT");
        //System.out.println(System.getenv());
        //System.out.println(api.myTrades(BinanceSymbol.valueOf("TRXUSDT")));

    }

    public static void getAccountInfo() {
        System.out.println("***");
    }
}
