package reflectiongui.renderers.standard;

import reflectiongui.annotations.Renders;
import reflectiongui.controllers.VariableController;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * Renderer для double[][].
 * <p/>
 * Выводит отображаемую матрицу в многострочном текстовом поле.
 */
@Renders(double[][].class)
public class SimpleDoubleMatrixRenderer extends AbstractSimpleVariableRenderer {
    @Override
    protected String objectToString(Object obj) {
        double[][] m = (double[][]) obj;
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < m.length; i++) {
            writeVector(m[i], b);
            if (i < m.length - 1) {
                b.append('\n');
            }
        }
        return b.toString();
    }

    private static void writeVector(double[] v, StringBuilder b) {
        for (int i = 0; i < v.length; i++) {
            b.append(v[i]);
            if (i < v.length - 1) {
                b.append(' ');
            }
        }
    }

    @Override
    protected Object stringToObject(String str) {
        str = str.trim();
        if (str.isEmpty()) {
            return new double[0][0];
        }
        String[] ss = str.split("\n+");
        double[][] res = new double[ss.length][];
        for (int i = 0; i < res.length; i++) {
            res[i] = readVector(ss[i]);
        }
        return res;
    }

    private static double[] readVector(String s) {
        String ss[] = s.trim().split("\\s+");
        double res[] = new double[ss.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = Double.parseDouble(ss[i]);
        }
        return res;
    }

    @Override
    protected JTextComponent createTextComponent(VariableController controller) {
        return new JTextArea();
    }
}
