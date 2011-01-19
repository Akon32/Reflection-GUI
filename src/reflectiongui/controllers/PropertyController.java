package reflectiongui.controllers;

import reflectiongui.renderers.PropertyRenderer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Контроллер свойства
 */
public class PropertyController implements VariableController {
    private Object controlledObject;
    private PropertyRenderer renderer;
    private Field controlledField;
    private ObjectController objectController;

    public PropertyController(Field controlledField, ObjectController objectController) {
        this.controlledField = controlledField;
        this.objectController = objectController;
    }

    public void updateObject() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void updateUI() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Class getType() {
        return controlledField.getType();
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return controlledField.isAnnotationPresent(annotationClass);
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return controlledField.getAnnotation(annotationClass);
    }

    public Annotation[] getAnnotations() {
        return controlledField.getAnnotations();
    }

    public Annotation[] getDeclaredAnnotations() {
        return controlledField.getDeclaredAnnotations();
    }

    public PropertyRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(PropertyRenderer renderer) {
        this.renderer = renderer;
    }
}
