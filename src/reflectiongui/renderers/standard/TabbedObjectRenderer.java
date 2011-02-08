package reflectiongui.renderers.standard;

import reflectiongui.controllers.MethodController;
import reflectiongui.controllers.ObjectController;
import reflectiongui.controllers.PropertyController;
import reflectiongui.renderers.ObjectRenderer;

import javax.swing.*;

/** Renderer объектов, в котором методы отображаются как вкладки в TabbedPane. */
public class TabbedObjectRenderer implements ObjectRenderer {
    private JComponent rootComponent;

    public TabbedObjectRenderer() {
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
            tabs.add(mc.getTitle(), mc.getRenderer().rootComponent());
        }
    }
}
