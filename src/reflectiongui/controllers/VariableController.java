package reflectiongui.controllers;

import java.lang.reflect.AnnotatedElement;

/**
 * Контроллер переменной. Предположительно, это общий интерфейс для контроллеров свойств
 * {@link PropertyController} и контроллеров виртуальных свойств из {@link MethodParameters}.
 */
// TODO: преобразовать в абстрактный класс, с реализованными методами AnnotatedElement
public interface VariableController extends AnnotatedElement {

    /**
     * Обновить переменную в соответствии с тем,
     * что содержит графический интерфейс.
     */
    void updateObject();

    /**
     * Обновить графический интерфейс в соответствии
     * со значениями контролируемой переменной..
     */
    void updateUI();

    /** @return класс контролируемой переменной. */
    Class getType();

}
