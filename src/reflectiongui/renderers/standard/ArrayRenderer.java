package reflectiongui.renderers.standard;

import reflectiongui.annotations.TableSize;
import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.RendererFactory;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import java.awt.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;

/** Renderer для массивов. */
public class ArrayRenderer implements VariableRenderer {

    JPanel rootPanel;
    Dimension tableSize;
    VariableRenderer[] renderers = new VariableRenderer[0];
    Class<?> componentType;

    public ArrayRenderer() {
        rootPanel = new JPanel();
    }

    @Override
    public JComponent rootComponent() {
        return rootPanel;
    }

    @Override
    public void setValue(Object value) {
        if (!value.getClass().isArray()) {
            throw new IllegalArgumentException("Argument must be array");
        }
        componentType = value.getClass().getComponentType();
        createRenderers(value);
        for (int i = 0; i < Array.getLength(value); i++) {
            renderers[i].setValue(Array.get(value, i));
        }
    }

    private void createRenderers(Object value) {
        rootPanel.removeAll();
        rootPanel.setLayout(new GridLayout(tableSize.height, tableSize.width));
        renderers = new VariableRenderer[Array.getLength(value)];
        Class<?> elementType = value.getClass().getComponentType();
        for (int i = 0; i < Array.getLength(value); i++) {
            Object obj = Array.get(value, i);
            VariableRenderer renderer = RendererFactory.getInstance().createVariableRenderer(elementType,
                    obj.getClass().getDeclaredAnnotations());
            VariableController controller = new EmptyVariableController(elementType, "");
            renderer.initialize(controller);
            rootPanel.add(renderer.rootComponent());
            renderers[i] = renderer;
        }
        rootPanel.validate();
    }

    @Override
    public Object getValue() {
        if (componentType == null) {
            return null;
        }
        Object res = Array.newInstance(componentType, renderers.length);
        for (int i = 0; i < renderers.length; i++) {
            Array.set(res, i, renderers[i].getValue());
        }
        return res;
    }

    @Override
    public void initialize(VariableController controller) {
        TableSize ts = controller.getAnnotation(TableSize.class);
        tableSize = ts == null ? new Dimension(1, 1) : new Dimension(ts.columnCount(), ts.rowCount());
        rootPanel.setLayout(new GridLayout(tableSize.height, tableSize.width));
    }

    /** Контроллер, не выполняющий никаких действий по изменению объекта/renderer'а. */
    private static class EmptyVariableController implements VariableController {
        private Class<?> variableClass;
        private String title;

        private EmptyVariableController(Class<?> variableClass, String title) {
            this.variableClass = variableClass;
            this.title = title;
        }

        @Override
        public void updateObject() {
            // ничего не делаем.
        }

        @Override
        public void updateUI() {
            // ничего не делаем.
        }

        @Override
        public Class getType() {
            return variableClass;
        }

        @Override
        public String getTitle() {
            return title;
        }

        @Override
        public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
            return variableClass.getAnnotation(annotationClass);
        }

        @Override
        public Annotation[] getAnnotations() {
            return variableClass.getAnnotations();
        }

        @Override
        public Annotation[] getDeclaredAnnotations() {
            return variableClass.getDeclaredAnnotations();
        }

        @Override
        public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
            return variableClass.isAnnotationPresent(annotationClass);
        }
    }
}
