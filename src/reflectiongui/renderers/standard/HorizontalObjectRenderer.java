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
 * Renderer объектов, размещающий поля и методы горизонтально.
 * (все поля размещаются над всеми методами).
 */
public class HorizontalObjectRenderer implements ObjectRenderer {
    private JComponent rootComponent;
    private JComponent methodsPanel;
    private JComponent propertyPanel;

    public HorizontalObjectRenderer() {
        rootComponent = new JPanel();
        rootComponent.setLayout(new BoxLayout(rootComponent, BoxLayout.PAGE_AXIS));
        methodsPanel = new JPanel();
        methodsPanel.setLayout(new BoxLayout(methodsPanel, BoxLayout.LINE_AXIS));
        propertyPanel = new JPanel();
        propertyPanel.setLayout(new BoxLayout(propertyPanel, BoxLayout.LINE_AXIS));
        rootComponent.add(propertyPanel);
        rootComponent.add(methodsPanel);
    }

    @Override
    public JComponent rootComponent() {
        return rootComponent;
    }

    @Override
    public void initialize(ObjectController controller) {
        // помещаем компоненты в SortedSet
        SortedSet<ComponentHolder<JComponent>> methodHolders = new TreeSet<ComponentHolder<JComponent>>();
        for (MethodController mc : controller.getMethodControllers()) {
            methodHolders.add(new ComponentHolder<JComponent>(mc.getRenderer().rootComponent(), mc));
        }
        // извлекаем компоненты, отсортированные в соответствии с Positions
        for (ComponentHolder<JComponent> h : methodHolders) {
            methodsPanel.add(h.component);
        }
        // помещаем компоненты в SortedSet
        SortedSet<ComponentHolder<JComponent>> propertyHolders = new TreeSet<ComponentHolder<JComponent>>();
        for (PropertyController pc : controller.getPropertyControllers()) {
            propertyHolders.add(new ComponentHolder<JComponent>(pc.getRenderer().rootComponent(), pc));
        }
        // извлекаем компоненты, отсортированные в соответствии с Positions
        for (ComponentHolder<JComponent> h : propertyHolders) {
            propertyPanel.add(h.component);
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