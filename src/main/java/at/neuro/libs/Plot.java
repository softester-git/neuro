package at.neuro.libs;

import at.neuro.Neuro;
import at.neuro.libs.Klines;
import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.NamedPlotColor;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.ImageTerminal;
import org.json.JSONArray;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseBarSeriesBuilder;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.OpenPriceIndicator;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


public class Plot {
    public static double[] xx;
    public static double[] yy;
    public static double[][] values0;
    public static double max0;
    public static double min0;
    public static double[][] values1;
    public static double[][] values2;
    public static double[][] values3;
    public static String legend;

    public static void plotInit() {
        ImageTerminal png = new ImageTerminal();
        String filePath = "/home/sash/plot_" + legend.replace(" ", "_")+ ".png";
        File file = new File(filePath.toLowerCase());
        System.out.println(filePath.toLowerCase());
        try {
            file.createNewFile();
            png.processOutput(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            System.err.print(ex);
        } catch (IOException ex) {
            System.err.print(ex);
        }

//        Plot2DPanel plot = new Plot2DPanel();
//        plot.addLinePlot(legend, xx, yy);
//        JFrame frame = new JFrame(legend);
//        frame.setContentPane(plot);
//        frame.setVisible(true);
//        frame.setSize(600, 600);

        JavaPlot p = new JavaPlot();
        p.setTerminal(png);
        p.getAxis("x").setLabel("time");
        p.getAxis("y").setLabel("price");

        String resultMin = String.format("%.5f", min0).replace(',', '.');
        String resultMax = String.format("%.5f", max0).replace(',', '.');
        double boundMin = Double.parseDouble(resultMin) - (Double.parseDouble(resultMin) / 1000);
        double boundMax = Double.parseDouble(resultMax) + (Double.parseDouble(resultMax) / 1000);
        p.getAxis("y").setBoundaries(boundMin, boundMax);
        p.getAxis("x").setBoundaries(-10, Klines.limit + 10);

        if (values0.length > 0) {
            PlotStyle styleVal0 = new PlotStyle();
            styleVal0.setStyle(Style.LINES);
            styleVal0.setLineType(NamedPlotColor.RED);
            DataSetPlot setVal0 = new DataSetPlot(values0);
            setVal0.setPlotStyle(styleVal0);
            setVal0.setTitle(legend);
            p.addPlot(setVal0);
        }
        if (values1.length > 0) {
            PlotStyle styleVal1 = new PlotStyle();
            styleVal1.setStyle(Style.LINES);
            styleVal1.setLineType(NamedPlotColor.BLUE);
            DataSetPlot setVal1 = new DataSetPlot(values1);
            setVal1.setPlotStyle(styleVal1);
            setVal1.setTitle("SMA 21: " + legend);
            p.addPlot(setVal1);
        }
        if (values2.length > 0) {
            PlotStyle styleVal2 = new PlotStyle();
            styleVal2.setStyle(Style.LINES);
            styleVal2.setLineType(NamedPlotColor.GRAY);
            DataSetPlot setVal2 = new DataSetPlot(values2);
            setVal2.setPlotStyle(styleVal2);
            setVal2.setTitle("SMA 88: " + legend);
            p.addPlot(setVal2);
        }
        if (values3.length > 0) {
            PlotStyle styleVal3 = new PlotStyle();
            styleVal3.setStyle(Style.LINES);
            styleVal3.setLineType(NamedPlotColor.GREEN);
            DataSetPlot setVal3 = new DataSetPlot(values3);
            setVal3.setPlotStyle(styleVal3);
            setVal3.setTitle("SMA 210: " + legend);
            p.addPlot(setVal3);
        }

        p.plot();
        try {
            ImageIO.write(png.getImage(), "png", file);
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }

    public static void prepareData(JSONArray ja) {
        double[][] val0 = new double[ja.length()][2];
        double max0 = 0;
        double min0 = 1000000;

        for (int i=0; i<ja.length(); i++) {
            JSONArray jElem = new JSONArray(ja.get(i).toString());

            val0[i][0] = Double.parseDouble(String.valueOf(i));
            val0[i][1] = Double.parseDouble(jElem.get(1).toString());

            if (val0[i][1] > max0) max0 = val0[i][1];
            if (val0[i][1] < min0) min0 = val0[i][1];
        }
        Plot.values0 = val0;
        Plot.max0 = max0;
        Plot.min0 = min0;
        Plot.legend = Klines.symbol + " " + Klines.interval;
    }

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


    public static void prepareDataSma(OpenPriceIndicator openPrice) {
        double[][] val0 = new double[Klines.limit][2];
        double[][] val1 = new double[Klines.limit][2];
        double[][] val2 = new double[Klines.limit][2];

        SMAIndicator sma0 = new SMAIndicator(openPrice, 21);
        SMAIndicator sma1 = new SMAIndicator(openPrice, 88);
        SMAIndicator sma2 = new SMAIndicator(openPrice, 210);

        for (int i = 0; i < Klines.limit; i ++ ) {
            val0[i][0] = Double.parseDouble(String.valueOf(i));
            val0[i][1] = Double.parseDouble(sma0.getValue(i).toString());

            val1[i][0] = Double.parseDouble(String.valueOf(i));
            val1[i][1] = Double.parseDouble(sma1.getValue(i).toString());

            val2[i][0] = Double.parseDouble(String.valueOf(i));
            val2[i][1] = Double.parseDouble(sma2.getValue(i).toString());
        }

        Plot.values1 = val0;
        Plot.values2 = val1;
        Plot.values3 = val2;

        Plot.legend = Klines.symbol + " " + Klines.interval;
    }
}
