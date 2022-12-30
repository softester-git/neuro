package at.neuro;

import org.math.plot.*;

import javax.swing.*;

public class Plot {
    public static double[] x;
    public static double[] y;

    public static void plotInit() {

//        Plot2DPanel plot = new Plot2DPanel();   // add a line plot to the PlotPanel
//        plot.addLinePlot("Raw Data Samples Channel 0", x, converter.getChannel(0));   // put the PlotPanel in a JFrame, as a JPanel
//        plot.addLinePlot("Raw Data Samples Channel 1", x, converter.getChannel(1));   // put the PlotPanel in a JFrame, as a JPanel
//        frame.add(plot);
//
//        frame.setVisible(true);
//        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        Plot2DPanel plot = new Plot2DPanel();
        plot.addLinePlot("my plot", x, y);
        JFrame frame = new JFrame("a plot panel");
        frame.setContentPane(plot);
        frame.setVisible(true);
        frame.setSize(600, 600);
    }
}
