package reflectiongui.renderers.standard;

import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/** Renderer типа для String. */
public class StringRenderer implements VariableRenderer {
    private JTextComponent textComponent;

    public StringRenderer() {
        // TODO: другие типы компонентов (JTextPane,JTextArea)
        textComponent = new JTextField();
    }

    @Override
    public JComponent rootComponent() {
        return textComponent;
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
        // do nothing
    }
}
