package reflectiongui.controllers;

import reflectiongui.renderers.MethodRenderer;

import java.lang.reflect.Method;

/**
 * Контроллер метода. Содержит метод {@link #invoke()}, который производит вызов контролируемого метода.
 */
public class MethodController {

    private ObjectController objectController;
    private Method method;
    private MethodParameters methodParameters;
    private MethodRenderer renderer;

    public MethodController(ObjectController objectController, Method method) {
        this.objectController = objectController;
        this.method = method;
    }

    /**
     * Вызов контролируемого метода. При вызове сначала считываются параметры метода,
     * затем вызывается метод и выводится результат вызова. Также обновляются значения
     * свойств контролируемого объекта.
     */
    public void invoke() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Method getMethod() {
        return method;
    }

    public MethodRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(MethodRenderer renderer) {
        this.renderer = renderer;
    }
}
