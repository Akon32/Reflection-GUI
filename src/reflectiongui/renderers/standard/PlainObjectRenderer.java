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
 * Renderer объектов, в котором методы и свойства объекта
 * перечисляются сверху вниз, при этом методы и свойства могут чередоваться.
 */
public class PlainObjectRenderer implements ObjectRenderer {
    private JComponent rootComponent;
    private JScrollPane scrollPane;
    private ComponentHolderFactory<JComponent> componentHolderFactory = new ComponentHolderFactory<JComponent>();

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
            holders.add(componentHolderFactory.createHolder(pc.getRenderer().rootComponent(), pc));
        }
        for (MethodController mc : controller.getMethodControllers()) {
            holders.add(componentHolderFactory.createHolder(mc.getRenderer().rootComponent(), mc));
        }
        // извлекаем компоненты, отсортированные в соответствии с Positions
        for (ComponentHolder<JComponent> h : holders) {
            rootComponent.add(h.getComponent());
        }
    }
}
