package reflectiongui.renderers;

import reflectiongui.controllers.VariableController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Объект, создающий графическое представление свойства.
 * Генерируемый графический компонент может содержать поле
 * (создаваемое {@link VariableRenderer}) для ввода и вывода
 * значения соответствующего свойства, а также кнопки для принудительного чтения
 * или установки свойства.
 * <p/>
 * Наследники этого класса обязательно должны иметь конструктор, принимающий 1 параметр VariableRenderer.
 */
public class PropertyRenderer implements VariableRenderer {
    private VariableRenderer variableRenderer;
    private VariableController variableController;
    private JComponent rootComponent;

    public PropertyRenderer(VariableRenderer variableRenderer) {
        this.variableRenderer = variableRenderer;
        rootComponent = new JPanel();
        rootComponent.setLayout(new BoxLayout(rootComponent, BoxLayout.LINE_AXIS));
        rootComponent.add(variableRenderer.rootComponent());
        //TODO: задание подписи кнопки через аннотации
        JButton button = new JButton("set");
        rootComponent.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                variableController.updateObject();
            }
        });
    }

    public JComponent rootComponent() {
        return rootComponent;
    }

    public void setValue(Object value) {
        variableRenderer.setValue(value);
    }

    public Object getValue() {
        return variableRenderer.getValue();
    }

    public void initialize(VariableController controller) {
        variableController = controller;
        variableRenderer.initialize(controller);
    }

}
