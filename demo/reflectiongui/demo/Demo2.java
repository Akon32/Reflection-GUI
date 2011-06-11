package reflectiongui.demo;

import reflectiongui.ReflectionGUI;
import reflectiongui.renderers.standard.DesktopPaneDesktopRenderer;

/**
 * Демо ReflectionGUI.
 * Этот класс служит для запуска программы
 */
public class Demo2 {

    public static void main(String[] args) {
        ReflectionGUI.setDesktopRendererClass(DesktopPaneDesktopRenderer.class);
        ReflectionGUI.getInstance().addObjects(
                new Reverser(),
                new GroupDemo(), new GroupDemo(),
                new Types(),
                new Calculator(),
                new MatrixCalculator());
    }
}
