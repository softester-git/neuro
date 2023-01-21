package at.neuro.libs;

import org.json.JSONArray;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsLowerIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsMiddleIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsUpperIndicator;
import org.ta4j.core.indicators.helpers.OpenPriceIndicator;
import org.ta4j.core.indicators.statistics.StandardDeviationIndicator;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import static at.neuro.constant.Params.*;

public class Indicators {
    public static ArrayList<double[][]> aList = new ArrayList<>();

    public static BarSeries prepareBarSeries(JSONArray ja) {
        BarSeries series = new BaseBarSeriesBuilder().withName("AXP_Stock").build();
        for (int i = 0; i < ja.length(); i++) {

            JSONArray jElem = new JSONArray(ja.get(i).toString());

            String timestamp = jElem.get(0).toString();
            long epochMilli = Long.parseLong(timestamp);
            Instant instant = Instant.ofEpochMilli(epochMilli);
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());

            series.addBar(zdt, Double.parseDouble(jElem.get(1).toString()), Double.parseDouble(jElem.get(2).toString()), Double.parseDouble(jElem.get(3).toString()), Double.parseDouble(jElem.get(4).toString()), Double.parseDouble(jElem.get(5).toString()));
        }
        return series;
    }

    public static void prepareData(JSONArray ja) {
        double[][] val0 = new double[ja.length()][2];
        double max0 = 0;
        double min0 = 1000000;

        for (int i = 0; i < Klines.limit; i ++) {
            JSONArray jElem = new JSONArray(ja.get(i).toString());

            val0[i][0] = Double.parseDouble(String.valueOf(i));
            val0[i][1] = Double.parseDouble(jElem.get(1).toString());

            if (val0[i][1] > max0) max0 = val0[i][1];
            if (val0[i][1] < min0) min0 = val0[i][1];
        }
        aList.add(val0);
        Plot.max0 = max0;
        Plot.min0 = min0;
        Plot.legend = Klines.symbol + " " + Klines.interval;
        Plot.legendLine.add("Price");
        Plot.colors.add("BLACK");
        Plot.widths.add(2);
    }

    public static void prepareDataSMA(OpenPriceIndicator openPrice) {
        double[][] val0 = new double[Klines.limit][2];
        double[][] val1 = new double[Klines.limit][2];
        double[][] val2 = new double[Klines.limit][2];

        SMAIndicator sma0 = new SMAIndicator(openPrice, SMA_FAST);
        SMAIndicator sma1 = new SMAIndicator(openPrice, SMA_MIDDLE);
        SMAIndicator sma2 = new SMAIndicator(openPrice, SMA_SLOW);

        for (int i = 0; i < Klines.limit; i ++ ) {
            val0[i][0] = Double.parseDouble(String.valueOf(i));
            val0[i][1] = Double.parseDouble(sma0.getValue(i).toString());

            val1[i][0] = Double.parseDouble(String.valueOf(i));
            val1[i][1] = Double.parseDouble(sma1.getValue(i).toString());

            val2[i][0] = Double.parseDouble(String.valueOf(i));
            val2[i][1] = Double.parseDouble(sma2.getValue(i).toString());
        }
        aList.add(val0);
        aList.add(val1);
        aList.add(val2);
        Plot.legendLine.add("SMA " + SMA_FAST);
        Plot.legendLine.add("SMA " + SMA_MIDDLE);
        Plot.legendLine.add("SMA " + SMA_SLOW);
        Plot.colors.add("LIGHT_RED");
        Plot.colors.add("RED");
        Plot.colors.add("DARK_RED");
        Plot.widths.add(1);
        Plot.widths.add(1);
        Plot.widths.add(1);
    }

    public static void prepareDataBB(OpenPriceIndicator openPrice) {
        double[][] val0 = new double[Klines.limit][2];
        double[][] val1 = new double[Klines.limit][2];
        double[][] val2 = new double[Klines.limit][2];

        SMAIndicator sma = new SMAIndicator(openPrice, BB_MIDDLE);
        BollingerBandsMiddleIndicator bb0 = new BollingerBandsMiddleIndicator(sma);
        StandardDeviationIndicator sd = new StandardDeviationIndicator(sma, BB_MIDDLE);
        BollingerBandsLowerIndicator bb1 = new BollingerBandsLowerIndicator(bb0, sd);
        BollingerBandsUpperIndicator bb2 = new BollingerBandsUpperIndicator(bb0, sd);

        for (int i = 0; i < Klines.limit; i ++ ) {
            val0[i][0] = Double.parseDouble(String.valueOf(i));
            val0[i][1] = Double.parseDouble(bb0.getValue(i).toString());

            val1[i][0] = Double.parseDouble(String.valueOf(i));
            val1[i][1] = Double.parseDouble(bb1.getValue(i).toString());

            val2[i][0] = Double.parseDouble(String.valueOf(i));
            val2[i][1] = Double.parseDouble(bb2.getValue(i).toString());
        }
        aList.add(val0);
        aList.add(val1);
        aList.add(val2);
        Plot.legendLine.add("BB Middle");
        Plot.legendLine.add("BB Low");
        Plot.legendLine.add("BB Upper");
        Plot.colors.add("BLUE");
        Plot.colors.add("DARK_BLUE");
        Plot.colors.add("DARK_BLUE");
        Plot.widths.add(1);
        Plot.widths.add(2);
        Plot.widths.add(2);
    }
}
