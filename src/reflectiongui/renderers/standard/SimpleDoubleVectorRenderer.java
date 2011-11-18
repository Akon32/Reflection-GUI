package reflectiongui.renderers.standard;

import reflectiongui.annotations.Renders;
import reflectiongui.controllers.VariableController;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * Renderer для double[].
 * <p/>
 * Выводит отображаемый вектор в однострочном текстовом поле.
 */
@Renders(double[].class)
public class SimpleDoubleVectorRenderer extends AbstractSimpleVariableRenderer {
    @Override
    protected String objectToString(Object obj) {
        return vectorToString((double[]) obj);
    }

    private static String vectorToString(double[] v) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < v.length; i++) {
            b.append(v[i]);
            if (i < v.length - 1) {
                b.append(' ');
            }
        }
        return b.toString();
    }

    @Override
    protected Object stringToObject(String str) {
        return stringToVector(str);
    }

    private static double[] stringToVector(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return new double[0];
        }
        String ss[] = s.trim().split("\\s+");
        double res[] = new double[ss.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = Double.parseDouble(ss[i]);
        }
        return res;
    }

    @Override
    protected JTextComponent createTextComponent(VariableController controller) {
        return new JTextField();
    }
}
