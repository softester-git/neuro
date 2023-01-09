package at.neuro;

import at.neuro.libs.ApiClient;
import at.neuro.libs.Klines;
import org.json.JSONArray;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

import static at.neuro.libs.Image.saveImage;

public class Neuro {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Klines.symbol = "TRXUSDT";
        Klines.interval = "1h";
        Klines.limit = 100;
        Klines.klinesInit();

        JSONArray ja = new JSONArray(ApiClient.body);

        BarSeries series = new BaseBarSeriesBuilder().withName("AXP_Stock").build();
        for (int i = 0; i < ja.length(); i++) {

            JSONArray jElem = new JSONArray(ja.get(i).toString());

            String timestamp = jElem.get(0).toString();
            long epochMilli = Long.parseLong(timestamp);
            Instant instant = Instant.ofEpochMilli(epochMilli);
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());

            System.out.println(jElem.get(1));
            System.out.println(jElem.get(2));
            System.out.println(jElem.get(3));
            System.out.println(jElem.get(4));
            System.out.println(jElem.get(5));
            System.out.println("---");

            series.addBar(zdt, Double.parseDouble(jElem.get(1).toString()), Double.parseDouble(jElem.get(2).toString()), Double.parseDouble(jElem.get(3).toString()), Double.parseDouble(jElem.get(4).toString()), Double.parseDouble(jElem.get(5).toString()));

        }

        saveImage();

        //testWork();
    }
}
