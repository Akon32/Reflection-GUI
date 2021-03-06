package reflectiongui.renderers.standard;

import reflectiongui.annotations.Renders;
import reflectiongui.controllers.VariableController;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/** Простой Renderer типа java.lang.Integer */
@Renders(java.lang.Integer.class)
public class IntRenderer extends AbstractSimpleVariableRenderer {
    @Override
    protected String objectToString(Object obj) {
        return obj == null ? null : obj.toString();
    }

    @Override
    protected Object stringToObject(String str) {
        str = str.trim();
        return str.isEmpty() ? null : Integer.decode(str);
    }

    @Override
    protected JTextComponent createTextComponent(VariableController controller) {
        return new JTextField();
    }
}
