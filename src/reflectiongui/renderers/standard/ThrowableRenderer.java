package reflectiongui.renderers.standard;

import reflectiongui.annotations.Renders;
import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import java.awt.*;

/** Renderer типа для Throwable. */
@Renders(java.lang.Throwable.class)
public class ThrowableRenderer implements VariableRenderer {
    private JPanel panel;
    private JLabel label;
    private JTextField textField;

    public ThrowableRenderer() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(label = new JLabel());
        panel.add(textField = new JTextField());
        textField.setForeground(Color.RED);
    }

    @Override
    public JComponent rootComponent() {
        return panel;
    }

    @Override
    public void setValue(Object value) {
        textField.setText(value == null ? "" : value.toString());
    }

    @Override
    public Object getValue() {
        return new Throwable(textField.getText());
    }

    @Override
    public void initialize(VariableController controller) {
        label.setText(controller.getTitle());
    }
}
