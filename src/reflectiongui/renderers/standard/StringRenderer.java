package reflectiongui.renderers.standard;

import reflectiongui.annotations.Multiline;
import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * Renderer типа для String.
 * <p/>
 * По умолчанию строка отображается через JTextField.
 * Если указана аннотация {@link reflectiongui.annotations.Multiline},
 * то для отображения используется JTextArea.
 */
public class StringRenderer implements VariableRenderer {
    private JPanel panel;
    private JLabel label;
    private JTextComponent textComponent;

    public StringRenderer() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
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
        // добавление подписи
        panel.add(label = new JLabel());
        label.setText(controller.getTitle());
        // создание поля
        if (controller.isAnnotationPresent(Multiline.class)) {
            textComponent = new JTextArea();
        } else {
            textComponent = new JTextField();
        }
        panel.add(textComponent);
    }
}
