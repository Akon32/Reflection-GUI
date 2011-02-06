package reflectiongui.renderers.standard;

import reflectiongui.annotations.Renders;
import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import java.awt.*;

/** Renderer типа для Throwable. */
@Renders(java.lang.Throwable.class)
public class ThrowableRenderer implements VariableRenderer {
    private JTextField rootComponent;

    public ThrowableRenderer() {
        rootComponent = new JTextField();
        rootComponent.setForeground(Color.RED);
    }

    @Override
    public JComponent rootComponent() {
        return rootComponent;
    }

    @Override
    public void setValue(Object value) {
        rootComponent.setText(value == null ? "" : value.toString());
    }

    @Override
    public Object getValue() {
        return new Throwable(rootComponent.getText());
    }

    @Override
    public void initialize(VariableController controller) {
        // do nothing
    }
}
