package reflectiongui.renderers.standard;

import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/** Renderer типа для String. */
public class StringRenderer implements VariableRenderer {
    private JPanel panel;
    private JLabel label;
    private JTextComponent textComponent;

    public StringRenderer() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(label = new JLabel());
        // TODO: другие типы компонентов (JTextPane,JTextArea)
        panel.add(textComponent = new JTextField());
    }

    @Override
    public JComponent rootComponent() {
        return panel;
    }

    @Override
    public void setValue(Object value) {
        textComponent.setText(value == null ? null : (String) value);
    }

    @Override
    public Object getValue() {
        return textComponent.getText();
    }

    @Override
    public void initialize(VariableController controller) {
        label.setText(controller.getTitle());
    }
}
