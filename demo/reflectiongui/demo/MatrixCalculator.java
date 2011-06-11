package reflectiongui.demo;

import reflectiongui.annotations.FrameSize;
import reflectiongui.annotations.Ignored;
import reflectiongui.annotations.RenderObjectBy;
import reflectiongui.renderers.standard.HorizontalObjectRenderer;

import java.util.Arrays;

@RenderObjectBy(HorizontalObjectRenderer.class)
@FrameSize(width = 400, height = 300)
public class MatrixCalculator {
    double[][] a = {{1, 2, 3}, {3, 4, 5}};
    double[][] b = {{1, 2, 3}, {3, 4, 5}};
    double[][] c = {};

    void add() {
        normalize(a);
        normalize(b);
        c = a.length != 0 ? new double[a.length][a[0].length] : new double[0][];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
    }

    void sub() {
        normalize(a);
        normalize(b);
        c = a.length != 0 ? new double[a.length][a[0].length] : new double[0][];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                c[i][j] = a[i][j] - b[i][j];
            }
        }
    }

    @Ignored
    void normalize(double[][] m) {
        if (m.length == 0 || m.length == 1) {
            return;
        }
        int maxRowSize = m[0].length;
        for (double[] r : m) {
            if (r.length > maxRowSize) {
                maxRowSize = r.length;
            }
        }
        for (int i = 0; i < m.length; i++) {
            if (m[i].length < maxRowSize) {
                m[i] = Arrays.copyOf(m[i], maxRowSize);
            }
        }
    }
}
