package reflectiongui.demo;

import reflectiongui.ReflectionGUI;

/**
 * Демо ReflectionGUI.
 * Этот класс служит для запуска программы
 */
public class Demo1 {

    public static void main(String[] args) {
        ReflectionGUI.getInstance().addObjects(
                new Reverser(),
                new GroupDemo(), new GroupDemo(),
                new Types(),
                new Calculator());
    }
}
