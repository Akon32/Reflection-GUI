package reflectiongui.controllers;

import reflectiongui.renderers.PropertyRenderer;
import reflectiongui.renderers.RendererFactory;
import reflectiongui.util.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/** Контроллер свойства */
// TODO: доступ к свойству через getter'ы и setter'ы
public class PropertyController implements VariableController {
    private Object controlledObject;
    private PropertyRenderer renderer;
    private Field controlledField;
    // TODO: скорее всего, это поле нужно будет удалить
    private ObjectController objectController;
    private String title;

    public PropertyController(ObjectController objectController, Field controlledField) {
        this.controlledField = controlledField;
        controlledField.setAccessible(true);
        this.objectController = objectController;
        controlledObject = objectController.getControlledObject();
        title = Utils.getTitleFromAnnotations(controlledField, controlledField.getName());

        renderer = RendererFactory.getInstance().createPropertyRenderer(controlledField);
        renderer.initialize(this);
        updateUI();
    }

    public void updateObject() {
        try {
            controlledField.set(controlledObject, renderer.getValue());
        } catch (IllegalAccessException e) {
            //по идее, исключения здесь быть не должно.
            throw new RuntimeException(e);
        }
    }

    public void updateUI() {
        try {
            renderer.setValue(controlledField.get(controlledObject));
        } catch (IllegalAccessException e) {
            //по идее, исключения здесь быть не должно.
            throw new RuntimeException(e);
        }
    }

    public Class getType() {
        return controlledField.getType();
    }

    @Override
    public String getTitle() {
        return title;
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
}
