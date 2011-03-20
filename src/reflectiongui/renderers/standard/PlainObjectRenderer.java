package reflectiongui.renderers.standard;

import reflectiongui.annotations.Position;
import reflectiongui.controllers.MethodController;
import reflectiongui.controllers.ObjectController;
import reflectiongui.controllers.PropertyController;
import reflectiongui.renderers.ObjectRenderer;

import javax.swing.*;
import java.lang.reflect.AnnotatedElement;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Renderer объектов, в котором методы и свойства объекта
 * перечисляются сверху вниз, при этом методы и свойства могут чередоваться.
 */
public class PlainObjectRenderer implements ObjectRenderer {
    private JComponent rootComponent;
    private JScrollPane scrollPane;

    public PlainObjectRenderer() {
        rootComponent = new JPanel();
        rootComponent.setLayout(new BoxLayout(rootComponent, BoxLayout.PAGE_AXIS));
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(rootComponent);
    }

    @Override
    public JComponent rootComponent() {
        return scrollPane;
    }

    @Override
    public void initialize(ObjectController controller) {
        // помещаем компоненты в SortedSet
        SortedSet<ComponentHolder<JComponent>> holders = new TreeSet<ComponentHolder<JComponent>>();
        for (PropertyController pc : controller.getPropertyControllers()) {
            holders.add(new ComponentHolder<JComponent>(pc.getRenderer().rootComponent(), pc));
        }
        for (MethodController mc : controller.getMethodControllers()) {
            holders.add(new ComponentHolder<JComponent>(mc.getRenderer().rootComponent(), mc));
        }
        // извлекаем компоненты, отсортированные в соответствии с Positions
        for (ComponentHolder<JComponent> h : holders) {
            rootComponent.add(h.component);
        }
    }

    private int componentHolderNextIndex;

    private class ComponentHolder<T> implements Comparable<ComponentHolder<T>> {
        public final T component;
        public final int positionValue;
        public final int index;

        ComponentHolder(T component, AnnotatedElement annotations) {
            this.component = component;
            index = componentHolderNextIndex++;
            Position position = annotations.getAnnotation(Position.class);
            // если аннотация Position отсутствует, считаем её значение равным -1,
            // т.е. помещаем неаннотированный элемент в начало.
            positionValue = position == null ? -1 : position.value();
        }

        @Override
        public int compareTo(ComponentHolder<T> o) {
            if (this.positionValue > o.positionValue) {
                return 1;
            } else if (this.positionValue < o.positionValue) {
                return -1;
            } else if (this.index > o.index) {
                return 1;
            } else if (this.index < o.index) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
