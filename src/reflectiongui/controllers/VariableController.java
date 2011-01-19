package reflectiongui.controllers;

import java.lang.reflect.AnnotatedElement;

/**
 * Контроллер переменной. Предположительно, это общий интерфейс для контроллеров свойств
 * {@link PropertyController} и контроллеров виртуальных свойств из {@link MethodParameters}.
 */
public interface VariableController extends AnnotatedElement {

    /**
     * Обновить переменную в соответствии с тем, что содержит графический интерфейс.
     */
    void updateObject();

    /**
     * Обновить графический интерфейс в соответствии со значениями контролируемой переменной..
     */
    void updateUI();

    /**
     * @return класс контролируемой переменной.
     */
    Class getType();

}
