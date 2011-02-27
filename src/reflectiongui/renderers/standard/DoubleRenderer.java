package reflectiongui.renderers.standard;

import reflectiongui.annotations.Renders;
import reflectiongui.controllers.VariableController;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/** Renderer типа для Double */
@Renders(java.lang.Double.class)
public class DoubleRenderer extends AbstractSimpleVariableRenderer {
    @Override
    protected String objectToString(Object obj) {
        return obj == null ? null : obj.toString();
    }

    @Override
    protected Object stringToObject(String str) {
        str = str.trim();
        return str.isEmpty() ? null : Double.parseDouble(str);
    }

    @Override
    protected JTextComponent createTextComponent(VariableController controller) {
        return new JTextField();
    }
}
