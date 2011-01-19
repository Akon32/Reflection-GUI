package reflectiongui.renderers;

import reflectiongui.controllers.VariableController;

import javax.swing.*;

/**
 * Объект, создающий графическое представление переменной определенного типа.
 * Генерируемый графический компонент содержит поле для ввода и вывода
 * значения соответствующего типа.
 *
 * @see PropertyRenderer
 */
public interface VariableRenderer {
    JComponent rootComponent();

    void setValue(Object value);

    Object getValue();

    void initialize(VariableController controller);
}
