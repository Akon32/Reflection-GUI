package reflectiongui.controllers;

import reflectiongui.renderers.ObjectRenderer;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Контроллер объекта, отражаемого в графический интерфейс
 * с помощью библиотеки Reflection-GUI.
 */
public class ObjectController implements AnnotatedElement {

    private Object controlledObject;
    private ObjectRenderer renderer;
    private MethodController[] methodControllers;
    private VariableController[] propertyControllers;

    public ObjectController(Object controlledObject) {
        this.controlledObject = controlledObject;
        Class clazz = controlledObject.getClass();
        // TODO: Positions

        for (Method m : clazz.getMethods()) {

        }
        for (Field f : clazz.getFields()) {

        }
    }

    /**
     * Обновить поля объекта в соответствии с тем,
     * что содержит графический интерфейс.
     */
    public void updateObject() {
        for (VariableController c : propertyControllers) {
            c.updateObject();
        }
    }

    /**
     * Обновить графический интерфейс в соответствии
     * со значениями полей контролируемого объекта.
     */
    public void updateUI() {
        for (VariableController c : propertyControllers) {
            c.updateUI();
        }
    }

    public Object getControlledObject() {
        return controlledObject;
    }

    public ObjectRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(ObjectRenderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return controlledObject.getClass().isAnnotationPresent(annotationClass);
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return controlledObject.getClass().getAnnotation(annotationClass);
    }

    @Override
    public Annotation[] getAnnotations() {
        return controlledObject.getClass().getAnnotations();
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return controlledObject.getClass().getDeclaredAnnotations();
    }
}
