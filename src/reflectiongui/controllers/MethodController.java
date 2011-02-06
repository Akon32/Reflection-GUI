package reflectiongui.controllers;

import reflectiongui.renderers.MethodRenderer;
import reflectiongui.renderers.RendererFactory;

import javax.swing.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * Контроллер метода.
 * Содержит метод {@link #invoke()}, который производит вызов контролируемого метода.
 */
public class MethodController implements AnnotatedElement {

    private ObjectController objectController;
    private Method method;
    private MethodParameters methodParameters;
    private MethodRenderer renderer;

    public MethodController(ObjectController objectController, Method method) {
        this.objectController = objectController;
        this.method = method;
        method.setAccessible(true);
        methodParameters = new MethodParameters(method);

        renderer = RendererFactory.getInstance().createMethodRenderer(method);
        renderer.initialize(this);
    }

    /**
     * Вызов контролируемого метода. При вызове сначала считываются параметры метода,
     * затем вызывается метод и выводится результат вызова. Также обновляются значения
     * свойств контролируемого объекта.
     */
    public void invoke() {
        // (lock)
        objectController.updateObject();
        methodParameters.updateObject();
        new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                return method.invoke(objectController.getControlledObject(), methodParameters.getParameters());
            }

            @Override
            protected void done() {
                try {
                    renderer.displayResult(get());
                } catch (Exception e) {
                    renderer.displayException(e);
                } finally {
                    // (unlock)
                    objectController.updateUI();
                }
            }
        }.execute();
    }

    public Method getMethod() {
        return method;
    }

    public MethodRenderer getRenderer() {
        return renderer;
    }

    public ObjectController getObjectController() {
        return objectController;
    }

    public MethodParameters getMethodParameters() {
        return methodParameters;
    }

    @Override
    public Annotation[] getAnnotations() {
        return method.getAnnotations();
    }

    @Override
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return method.isAnnotationPresent(annotationClass);
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return method.getDeclaredAnnotations();
    }

    @Override
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return method.getAnnotation(annotationClass);
    }
}
