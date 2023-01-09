package at.neuro.libs;

import org.json.JSONArray;

public class Image {
    public static void saveImage() {
        JSONArray ja = new JSONArray(ApiClient.body);
        double[][] val0 = new double[ja.length()][2];

//        String[][] val1 = new String[ja.length()][2];

//        double[] xx = new double[ja.length()];
//        double[] yy = new double[ja.length()];

        double max0 = 0;
        double min0 = 1000000;

        for (int i=0; i<ja.length(); i++) {
            JSONArray jElem = new JSONArray(ja.get(i).toString());

//            xx[i] = Double.parseDouble(String.valueOf(i));
//            yy[i] = Double.parseDouble(jElem.get(1).toString());


            val0[i][0] = Double.parseDouble(String.valueOf(i));

            val0[i][1] = Double.parseDouble(jElem.get(1).toString());

            if (val0[i][1] > max0) max0 = val0[i][1];
            if (val0[i][1] < min0) min0 = val0[i][1];
        }
        Plot.values0 = val0;
//        Plot.xx = xx;
//        Plot.yy = yy;
        Plot.max0 = max0;
        Plot.min0 = min0;
        Plot.legend = Klines.symbol + " " + Klines.interval;
        Plot.plotInit();
    }
}
