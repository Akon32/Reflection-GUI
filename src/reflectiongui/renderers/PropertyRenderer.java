package reflectiongui.renderers;

import reflectiongui.controllers.VariableController;

import javax.swing.*;

/**
 * Объект, создающий графическое представление свойства.
 * Генерируемый графический компонент может содержать поле
 * (создаваемое {@link VariableRenderer}) для ввода и вывода
 * значения соответствующего свойства, а также кнопки для принудительного чтения
 * или установки свойства.
 */
public class PropertyRenderer implements VariableRenderer {
    private VariableRenderer variableRenderer;

    public PropertyRenderer(VariableRenderer variableRenderer) {
        this.variableRenderer = variableRenderer;
    }

    public JComponent rootComponent() {
        return variableRenderer.rootComponent();
    }

    public void setValue(Object value) {
        variableRenderer.setValue(value);
    }

    public Object getValue() {
        return variableRenderer.getValue();
    }

    public void initialize(VariableController controller) {
        variableRenderer.initialize(controller);
    }
}
