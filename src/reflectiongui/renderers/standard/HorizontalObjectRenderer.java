package reflectiongui.renderers.standard;

import reflectiongui.controllers.MethodController;
import reflectiongui.controllers.ObjectController;
import reflectiongui.controllers.PropertyController;
import reflectiongui.renderers.ObjectRenderer;
import reflectiongui.util.renderers.ComponentHolder;
import reflectiongui.util.renderers.ComponentHolderFactory;

import javax.swing.*;
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
    private ComponentHolderFactory<JComponent> componentHolderFactory = new ComponentHolderFactory<JComponent>();

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
            methodHolders.add(componentHolderFactory.createHolder(mc.getRenderer().rootComponent(), mc));
        }
        // извлекаем компоненты, отсортированные в соответствии с Positions
        for (ComponentHolder<JComponent> h : methodHolders) {
            methodsPanel.add(h.getComponent());
        }
        // помещаем компоненты в SortedSet
        SortedSet<ComponentHolder<JComponent>> propertyHolders = new TreeSet<ComponentHolder<JComponent>>();
        for (PropertyController pc : controller.getPropertyControllers()) {
            propertyHolders.add(componentHolderFactory.createHolder(pc.getRenderer().rootComponent(), pc));
        }
        // извлекаем компоненты, отсортированные в соответствии с Positions
        for (ComponentHolder<JComponent> h : propertyHolders) {
            propertyPanel.add(h.getComponent());
        }
    }
}
