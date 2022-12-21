package at.neuro;

import com.google.gson.JsonObject;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceSymbol;

import java.math.BigDecimal;

public class Neuro {
    private static BinanceApi api = new BinanceApi();
    public static void main (String[] args) {
        //getAccountInfo();
        try {
            //BigDecimal asset = api.pricesMap().get("TRXUSDT");
            System.out.println(api.myTrades(BinanceSymbol.valueOf("TRXUSDT")));
        } catch (BinanceApiException e) {
            throw new RuntimeException(e);
        }
    }

    public static void getAccountInfo() {
        JsonObject account = null;
        try {
            account = api.account();
        } catch (BinanceApiException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Maker Commission: " + account.get("makerCommission").getAsBigDecimal());
        System.out.println("Taker Commission: " + account.get("takerCommission").getAsBigDecimal());
        System.out.println("Buyer Commission: " + account.get("buyerCommission").getAsBigDecimal());
        System.out.println("Seller Commission: " + account.get("sellerCommission").getAsBigDecimal());
        System.out.println("Can Trade: " +  account.get("canTrade").getAsBoolean());
        System.out.println("Can Withdraw: " + account.get("canWithdraw").getAsBoolean());
        System.out.println("Can Deposit: " + account.get("canDeposit").getAsBoolean());
    }
}
