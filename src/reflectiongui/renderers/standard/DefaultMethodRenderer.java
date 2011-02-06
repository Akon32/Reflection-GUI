package reflectiongui.renderers.standard;

import reflectiongui.controllers.MethodController;
import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.MethodRenderer;
import reflectiongui.renderers.RendererFactory;
import reflectiongui.renderers.VariableRenderer;
import reflectiongui.util.Utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;

/** Renderer методов по умолчанию. */
public class DefaultMethodRenderer implements MethodRenderer {
    private JComponent rootComponent;
    private VariableRenderer resultRenderer;
    private VariableRenderer exceptionRenderer;

    public DefaultMethodRenderer() {
        rootComponent = new JPanel();
        rootComponent.setLayout(new BoxLayout(rootComponent, BoxLayout.PAGE_AXIS));
    }

    @Override
    public JComponent rootComponent() {
        return rootComponent;
    }

    @Override
    public void displayResult(Object result) {
        resultRenderer.setValue(result);
        exceptionRenderer.setValue(null);
    }

    @Override
    public void displayException(Throwable throwable) {
        resultRenderer.setValue(null);
        exceptionRenderer.setValue(throwable);
    }

    @Override
    public void initialize(final MethodController controller) {
        for (VariableRenderer paramRenderer : controller.getMethodParameters().getRenderers()) {
            rootComponent.add(paramRenderer.rootComponent());
        }

        JButton button = new JButton(controller.getTitle());
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.invoke();
            }
        });
        rootComponent.add(button);

        resultRenderer = RendererFactory.getInstance().createVariableRenderer(controller.getMethod().getReturnType(), controller.getAnnotations());
        resultRenderer.initialize(new EmptyVariableController("Result", controller.getMethod().getReturnType(), controller.getAnnotations()));
        rootComponent.add(resultRenderer.rootComponent());

        // TODO: несколько renderer'ов исключений в методе
        exceptionRenderer = RendererFactory.getInstance().createVariableRenderer(Throwable.class, new Annotation[0]);
        exceptionRenderer.initialize(new EmptyVariableController("Error", Throwable.class, new Annotation[0]));
        rootComponent.add(exceptionRenderer.rootComponent());
    }

    /**
     * VariableController, ничего не делающий при вызове
     * методов {@link #updateObject()} и {@link #updateUI()}
     */
    private static class EmptyVariableController implements VariableController {
        private final Class type;
        private final Annotation[] annotations;
        private final String title;

        private EmptyVariableController(String title, Class type, Annotation[] annotations) {
            this.title = title;
            this.type = type;
            this.annotations = annotations;
        }

        @Override
        public void updateObject() {
            // do nothing
        }

        @Override
        public void updateUI() {
            // do nothing
        }

        @Override
        public Class getType() {
            return type;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
            return getAnnotation(annotationClass) != null;
        }

        @Override
        public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
            return Utils.findObjectOfClass(annotations, annotationClass);
        }

        @Override
        public Annotation[] getAnnotations() {
            return annotations;
        }

        @Override
        public Annotation[] getDeclaredAnnotations() {
            return getAnnotations();
        }
    }
}
