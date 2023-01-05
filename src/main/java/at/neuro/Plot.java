package at.neuro;

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


public class Plot {
    public static double[][] values0;
    public static double max0;
    public static double min0;
    public static String[][] values1;
    public static double[][] values2;
    public static double[][] values3;
    public static String legend;

    public static void plotInit() {
        ImageTerminal png = new ImageTerminal();
        File file = new File("/home/sash/plot1.png");
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
        double boundMin = Double.parseDouble(resultMin) - (Double.parseDouble(resultMin) / 100);
        double boundMax = Double.parseDouble(resultMax) + (Double.parseDouble(resultMax) / 100);
        p.getAxis("y").setBoundaries(boundMin, boundMax);
        p.getAxis("x").setBoundaries(-10, 110);

        if (values0.length > 0) {
            PlotStyle styleVal0 = new PlotStyle();
            styleVal0.setStyle(Style.LINES);
            styleVal0.setLineType(NamedPlotColor.RED);
            DataSetPlot setVal0 = new DataSetPlot(values0);
            setVal0.setPlotStyle(styleVal0);
            setVal0.setTitle(legend);
            p.addPlot(setVal0);
        }

//        if (values1.length > 0) {
//            PlotStyle styleVal1 = new PlotStyle();
//            styleVal1.setStyle(Style.LINES);
//            styleVal1.setLineType(NamedPlotColor.BLUE);
//            DataSetPlot setVal1 = new DataSetPlot(values1);
//            setVal1.setPlotStyle(styleVal1);
//            setVal1.setTitle(legend);
//            p.addPlot(setVal1);
//        }
        p.plot();
        try {
            ImageIO.write(png.getImage(), "png", file);
        } catch (IOException ex) {
            System.err.print(ex);
        }
    }
}
