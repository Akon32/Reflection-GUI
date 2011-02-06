package reflectiongui.renderers.standard;

import reflectiongui.controllers.MethodController;
import reflectiongui.controllers.ObjectController;
import reflectiongui.controllers.PropertyController;
import reflectiongui.renderers.ObjectRenderer;

import javax.swing.*;

/** Renderer объектов по умолчанию. */
public class DefaultObjectRenderer implements ObjectRenderer {
    private JComponent rootComponent;

    public DefaultObjectRenderer() {
        rootComponent = new JPanel();
        rootComponent.setLayout(new BoxLayout(rootComponent, BoxLayout.PAGE_AXIS));
    }

    @Override
    public JComponent rootComponent() {
        return rootComponent;
    }

    @Override
    public void initialize(ObjectController controller) {
        for (PropertyController pc : controller.getPropertyControllers()) {
            rootComponent.add(pc.getRenderer().rootComponent());
        }
        JComponent tabs = new JTabbedPane();
        rootComponent.add(tabs);
        for (MethodController mc : controller.getMethodControllers()) {
            tabs.add(mc.getMethod().getName(), mc.getRenderer().rootComponent());
        }
    }
}
