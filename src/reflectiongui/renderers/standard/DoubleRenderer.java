package reflectiongui.renderers.standard;

import reflectiongui.annotations.Renders;
import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/** Renderer типа для Double */
@Renders(java.lang.Double.class)
public class DoubleRenderer implements VariableRenderer {
    private JPanel panel;
    private JLabel label;
    private JTextComponent textComponent;

    public DoubleRenderer() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(label = new JLabel());
        panel.add(textComponent = new JTextField());
    }

    @Override
    public JComponent rootComponent() {
        return panel;
    }

    @Override
    public void setValue(Object value) {
        textComponent.setText(value == null ? null : value.toString());
    }

    @Override
    public Object getValue() {
        return textComponent.getText() == null ? null : Double.valueOf(textComponent.getText());
    }

    @Override
    public void initialize(VariableController controller) {
        label.setText(controller.getTitle());
    }
}
