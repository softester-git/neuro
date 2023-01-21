package at.neuro.libs;

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.NamedPlotColor;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.terminal.ImageTerminal;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Plot {
    public static String legend;
    public static double max0;
    public static double min0;
    final static int barsDisplay = 100;
    public static ArrayList<String> legendLine = new ArrayList<>();
    public static ArrayList<String> colors = new ArrayList<>();
    public static ArrayList<Integer> widths = new ArrayList<>();
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

        for (int i = 0; i < Indicators.aList.size(); i++) {
            plotLine(p, Indicators.aList.get(i), i);
        }
        p.plot();

        try {
            ImageIO.write(png.getImage(), "png", file);
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }

    public static void plotLine(JavaPlot p, double[][] val, int i) {
        PlotStyle styleVal = new PlotStyle();
        styleVal.setStyle(Style.LINES);
        styleVal.setLineType(NamedPlotColor.valueOf(colors.get(i)));
        styleVal.setLineWidth(widths.get(i));

        DataSetPlot setVal = new DataSetPlot(val);
        setVal.setPlotStyle(styleVal);
        setVal.setTitle(legendLine.get(i));

        p.addPlot(setVal);
    }
}
