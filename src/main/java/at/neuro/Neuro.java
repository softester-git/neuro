package at.neuro;

import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;

import java.math.BigDecimal;

public class Neuro {
    public static void main (String[] args) {
        BinanceApi api = new BinanceApi();
        try {
            BigDecimal asset = api.pricesMap().get("TRXUSDT");
            System.out.println(asset);
        } catch (BinanceApiException e) {
            throw new RuntimeException(e);
        }
    }
}
