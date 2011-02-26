package reflectiongui.renderers.standard;

import reflectiongui.annotations.Multiline;
import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import javax.swing.text.JTextComponent;

/**
 * Renderer для java.lang.Object.
 * <p/>
 * Не позволяет пользователю вводить данные объекта, служит только для отображения объекта.
 * Выводит информацию, полученную методом {@link Object#toString()}.
 * Учитывает аннотацию {@link Multiline}, если она есть на отображаемом объекте.
 */
public class JavaObjectRenderer implements VariableRenderer {
    private JPanel panel;
    private JLabel label;
    private JTextComponent textComponent;
    private Object value;

    public JavaObjectRenderer() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
    }

    @Override
    public JComponent rootComponent() {
        return panel;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
        textComponent.setText(value == null ? null : value.toString());
    }

    @Override
    public Object getValue() {
        return value;
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
        textComponent.setEditable(false);
        panel.add(textComponent);
    }
}
