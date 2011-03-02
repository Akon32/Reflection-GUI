package reflectiongui.controllers;

import reflectiongui.annotations.Ignored;
import reflectiongui.grouping.GroupManager;
import reflectiongui.renderers.ObjectRenderer;
import reflectiongui.renderers.RendererFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Контроллер объекта, отражаемого в графический интерфейс
 * с помощью библиотеки Reflection-GUI.
 */
public class ObjectController implements AnnotatedElement {

    private Object controlledObject;
    private ObjectRenderer renderer;
    private MethodController[] methodControllers;
    private PropertyController[] propertyControllers;
    private TitleGetter titleGetter;
    private ObjectFrameUpdater frameUpdater;

    /**
     * Конструктор. В нем создаются все контроллеры свойств и методов объекта,
     * а также производится добавление в группы соответственно аннотированных
     * объектов.
     *
     * @param controlledObject контролируемый объект.
     */
    public ObjectController(Object controlledObject) {
        this.controlledObject = controlledObject;
        // ^ после этого объект корректно ведет себя как AnnotatedElement
        Class clazz = controlledObject.getClass();
        GroupManager.getInstance().addToGroups(controlledObject, this);
        Set<MethodController> ms = new LinkedHashSet<MethodController>();
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getAnnotation(Ignored.class) == null) {
                MethodController controller = new MethodController(this, m);
                GroupManager.getInstance().addToGroups(controlledObject, controller);
                ms.add(controller);
            }
        }
        methodControllers = ms.toArray(new MethodController[ms.size()]);
        Set<PropertyController> ps = new LinkedHashSet<PropertyController>();
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getAnnotation(Ignored.class) == null) {
                PropertyController controller = new PropertyController(this, f);
                GroupManager.getInstance().addToGroups(controlledObject, controller);
                ps.add(controller);
            }
        }
        propertyControllers = ps.toArray(new PropertyController[ps.size()]);
        titleGetter = new TitleGetter(controlledObject);

        renderer = RendererFactory.getInstance().createObjectRenderer(clazz);
        renderer.initialize(this);
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
        if (frameUpdater != null) {
            frameUpdater.updateFrame();
        }
    }

    public Object getControlledObject() {
        return controlledObject;
    }

    public ObjectRenderer getRenderer() {
        return renderer;
    }

    public void setFrameUpdater(ObjectFrameUpdater frameUpdater) {
        this.frameUpdater = frameUpdater;
    }

    public void setRenderer(ObjectRenderer renderer) {
        this.renderer = renderer;
    }

    public String getTitle() {
        return titleGetter.getTitle();
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

    public MethodController[] getMethodControllers() {
        return methodControllers;
    }

    public PropertyController[] getPropertyControllers() {
        return propertyControllers;
    }
}
