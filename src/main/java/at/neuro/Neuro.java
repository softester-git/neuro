package at.neuro;

import at.neuro.libs.ApiClient;
import at.neuro.libs.Indicators;
import at.neuro.libs.Klines;
import org.json.JSONArray;
import org.ta4j.core.BarSeries;
import org.ta4j.core.indicators.helpers.OpenPriceIndicator;

import java.io.IOException;
import java.net.URISyntaxException;

import static at.neuro.libs.Indicators.*;
import static at.neuro.libs.Plot.*;

public class Neuro {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Klines.symbol = "TRXUSDT";
        Klines.interval = "1h";
        Klines.limit = 1000;
        Klines.klinesInit();

        JSONArray ja = new JSONArray(ApiClient.body);
        BarSeries series = prepareBarSeries(ja);
        OpenPriceIndicator openPrice = new OpenPriceIndicator(series);

        Indicators.prepareData(ja);
        prepareDataSMA(openPrice);
        prepareDataBB(openPrice);
        plotInit();

        //debugRun();
    }
}
