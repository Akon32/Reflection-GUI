package reflectiongui.renderers.standard;

import reflectiongui.ReflectionGUI;
import reflectiongui.controllers.ObjectController;
import reflectiongui.renderers.DesktopRenderer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

/** DesktopRenderer, отображающий объекты в разных окрах JFrame. */
public class MultiFrameDesktopRenderer implements DesktopRenderer {

    private Map<Object, JFrame> frames = new HashMap<Object, JFrame>();

    @Override
    public void addObject(Object object) {
        if (frames.containsKey(object)) {
            throw new IllegalArgumentException("Given object is already shown");
        }
        JFrame frame = createFrame(object);
        frames.put(object, frame);
        frame.setVisible(true);
    }

    private JFrame createFrame(final Object object) {
        JFrame frame = new JFrame();
        ObjectController controller = new ObjectController(object);
        frame.setTitle(controller.getTitle());
        frame.getContentPane().add(controller.getRenderer().rootComponent());
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                removeObject(object);
            }
        });
        return frame;
    }

    @Override
    public void removeObject(Object object) {
        JFrame frame = frames.remove(object);
        if (frame != null) {
            frame.dispose();
        }
        if (frames.isEmpty()) {
            ReflectionGUI.getInstance().shutdownApplication();
        }
    }
}
