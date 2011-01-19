package reflectiongui.controllers;

import java.lang.reflect.Method;

/**
 * Объект, хранящий параметры метода. Предоставляет контроллеры своих виртуальных "свойств".
 *
 * @see PropertyController
 * @see MethodController
 */
public class MethodParameters {
    private Object[] parameters;
    private VariableController[] controllers;

    public MethodParameters(Method method) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Контроллеры "свойств" объекта-параметров.
     *
     * @return массив контроллеров "свойств" объекта-параметров.
     */
    public VariableController[] getPropertyControllers() {
        return controllers;
    }

    /**
     * Параметры, представляемые данным объектом.
     *
     * @return массив параметров.
     */
    public Object[] getParameters() {
        return parameters;
    }
}
