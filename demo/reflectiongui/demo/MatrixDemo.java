package reflectiongui.demo;

import reflectiongui.annotations.RenderObjectBy;
import reflectiongui.renderers.standard.PlainObjectRenderer;

@RenderObjectBy(PlainObjectRenderer.class)
public class MatrixDemo {
    double[][] a = new double[3][3];
    double[][] b;
    double sumA;

    void sum() {
        sumA = 0;
        for (double[] v : a) {
            for (double vv : v) {
                sumA += vv;
            }
        }
    }
}
