package reflectiongui.renderers.standard;

import reflectiongui.annotations.Multiline;
import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * Простой абстрактный renderer типа.
 * <p/>
 * Выводит название (title) объекта в виде метки, и содержимое объекта в виде текста.
 * Для преобразования служат методы {@link #objectToString(Object)}
 * и {@link #stringToObject(String)}.
 * <p/>
 * Используется LayoutManager, создаваемый методом
 * {@link #createLayoutManager(javax.swing.JComponent)}.
 * <p/>
 * По умолчанию объект отображается через JTextField.
 * Если указана аннотация {@link reflectiongui.annotations.Multiline},
 * то для отображения используется JTextArea.
 */
public abstract class AbstractSimpleVariableRenderer implements VariableRenderer {
    private JPanel panel;
    private JLabel label;
    private JTextComponent textComponent;

    public AbstractSimpleVariableRenderer() {
        panel = new JPanel();
    }

    /**
     * Создать LayoutManager для корневого компонента renderer'a.
     * <p/>
     * В данной реализации создается BoxLayout c ориентацией {@link BoxLayout#LINE_AXIS}.
     *
     * @param c корневой компонент.
     * @return LayoutManager.
     */
    protected LayoutManager createLayoutManager(JComponent c) {
        return new BoxLayout(c, BoxLayout.LINE_AXIS);
    }

    @Override
    public JComponent rootComponent() {
        return panel;
    }

    @Override
    public void setValue(Object value) {
        textComponent.setText(value == null ? null : objectToString(value));
    }

    @Override
    public Object getValue() {
        return stringToObject(textComponent.getText());
    }

    @Override
    public void initialize(VariableController controller) {
        panel.setLayout(createLayoutManager(panel));
        // добавление подписи
        panel.add(label = new JLabel());
        label.setText(controller.getTitle());
        // создание поля
        textComponent = createTextComponent(controller);
        panel.add(textComponent);
    }

    /**
     * Создать текстовый компонент для отображения объекта.
     * <p/>
     * В данной реализации создается {@link JTextArea}, если указана
     * аннотация {@link Multiline}, и {@link JTextField} в остальных случаях.
     *
     * @param controller контроллер переменной.
     * @return JTextComponent.
     */
    protected JTextComponent createTextComponent(VariableController controller) {
        if (controller.isAnnotationPresent(Multiline.class)) {
            return new JTextArea();
        } else {
            return new JTextField();
        }
    }

    /**
     * Преобразовать отображаемый объект в строку.
     *
     * @param obj объект
     * @return строковое представление объекта.
     */
    protected abstract String objectToString(Object obj);

    /**
     * Преобразовать строку в отображаетмый объект.
     *
     * @param str строковое представление объекта.
     * @return созданный из строки объект.
     */
    protected abstract Object stringToObject(String str);
}
