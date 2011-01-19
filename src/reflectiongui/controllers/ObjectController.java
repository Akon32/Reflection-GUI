package reflectiongui.controllers;

import reflectiongui.renderers.ObjectRenderer;

/**
 * Контроллер объекта, отражаемого в графический интерфейс с помощью библиотеки Reflection-GUI.
 */
public class ObjectController {

    private Object controlledObject;
    private ObjectRenderer renderer;
    private MethodController[] methodControllers;
    private VariableController[] propertyControllers;

    public ObjectController(Object controlledObject) {
        this.controlledObject = controlledObject;
    }

    /**
     * Обновить поля объекта в соответствии с тем, что содержит графический интерфейс.
     */
    public void updateObject() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Обновить графический интерфейс в соответствии со значениями полей контролируемого объекта.
     */
    public void updateUI() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public ObjectRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(ObjectRenderer renderer) {
        this.renderer = renderer;
    }
}
