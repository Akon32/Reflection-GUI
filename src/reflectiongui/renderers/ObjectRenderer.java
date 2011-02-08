package reflectiongui.renderers;

import reflectiongui.controllers.ObjectController;

import javax.swing.*;

/**
 * Объект, создающий графическое представление объекта.
 * Генерируемый графический компонент может содержать представления свойств и методов класса.
 */
public interface ObjectRenderer {

    JComponent rootComponent();

    void initialize(ObjectController controller);
}
