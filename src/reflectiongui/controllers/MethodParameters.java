package reflectiongui.controllers;

import reflectiongui.renderers.RendererFactory;
import reflectiongui.renderers.VariableRenderer;
import reflectiongui.util.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Объект, хранящий параметры метода. Предоставляет контроллеры
 * и renderer'ы своих виртуальных "свойств".
 *
 * @see PropertyController
 * @see MethodController
 */
public class MethodParameters {
    private final Object[] parameters;
    private final VariableController[] controllers;
    private final VariableRenderer[] renderers;
    private final Annotation[][] annotations;
    private final Class[] types;

    /**
     * Конструктор.
     * Происходит инициализация полей объекта,
     * создание и связь между собой controller'ов и renderer'ов для параметров метода.
     *
     * @param method метод, для которого создается объект MethodParameters.
     */
    public MethodParameters(Method method) {
        int paramCount = method.getParameterTypes().length;
        parameters = new Object[paramCount];
        controllers = new VariableController[paramCount];
        renderers = new VariableRenderer[paramCount];
        annotations = method.getParameterAnnotations();
        types = method.getParameterTypes();
        for (int i = 0; i < paramCount; i++) {
            controllers[i] = new ParameterController(i);
            renderers[i] = RendererFactory.getInstance().createVariableRenderer(types[i], annotations[i]);
            renderers[i].initialize(controllers[i]);
        }
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

    /**
     * Renderer'ы, отображающие параметры метода.
     *
     * @return массив renderer'ов.
     */
    public VariableRenderer[] getRenderers() {
        return renderers;
    }

    /**
     * Обновить поля объекта в соответствии с тем,
     * что содержит графический интерфейс.
     */
    public void updateObject() {
        for (VariableController c : controllers)
            c.updateObject();
    }

    /**
     * Контроллер параметра метода. Тесно связан со своим внешним объектом {@link MethodParameters},
     * использует значения его полей-массивов как свои, подставляя свой номер.
     */
    private class ParameterController implements VariableController {

        /** Номер параметра */
        private final int paramNumber;

        private ParameterController(int paramNumber) {
            this.paramNumber = paramNumber;
        }

        @Override
        public void updateObject() {
            parameters[paramNumber] = renderers[paramNumber].getValue();
        }

        @Override
        public void updateUI() {
            renderers[paramNumber].setValue(parameters[paramNumber]);
        }

        @Override
        public Class getType() {
            return types[paramNumber];
        }

        @Override
        public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
            return getAnnotation(annotationClass) != null;
        }

        @Override
        public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
            return Utils.findObjectOfClass(annotations[paramNumber], annotationClass);
        }

        @Override
        public Annotation[] getAnnotations() {
            return annotations[paramNumber];
        }

        @Override
        public Annotation[] getDeclaredAnnotations() {
            return getAnnotations();
        }
    }
}
