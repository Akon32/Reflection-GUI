package reflectiongui.renderers;

import reflectiongui.controllers.MethodController;

import javax.swing.*;

/**
 * Объект, создающий графическое представление метода. Созданный этим классом
 * графический компонент может содержать поля для параметров метода, и кнопку для вызова метода.
 */
public interface MethodRenderer {
    JComponent rootComponent();

    /**
     * Вывести результат успешного выполнения метода.
     *
     * @param result объект, возвращенный методом. Если метод возвращает тип void,
     *               то значение этого параметра не должно учитываться.
     */
    void displayResult(Object result);

    /**
     * Вывести результат неуспешного выполнения метода.
     *
     * @param throwable исключение, выброшенное методом.
     */
    void displayException(Throwable throwable);

    /**
     * Инициализация renderer'а. Параметры берутся из переданного контроллера.
     *
     * @param controller контроллер отображаемого метода.
     */
    void initialize(MethodController controller);
}

