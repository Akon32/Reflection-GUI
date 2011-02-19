package reflectiongui.renderers.standard;

import reflectiongui.ReflectionGUI;
import reflectiongui.annotations.FramePosition;
import reflectiongui.annotations.FrameSize;
import reflectiongui.annotations.InCenter;
import reflectiongui.controllers.ObjectController;
import reflectiongui.grouping.GroupManager;
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
        // добавление в группы происходит при вызове конструктора ObjectController
        ObjectController controller = new ObjectController(object);
        frame.setTitle(controller.getTitle());
        frame.getContentPane().add(controller.getRenderer().rootComponent());
        frame.pack();
        // определение размера из аннотаций
        if (controller.isAnnotationPresent(FrameSize.class)) {
            FrameSize s = controller.getAnnotation(FrameSize.class);
            frame.setSize(s.width(), s.height());
        }
        // определение позиции из аннотаций
        if (controller.isAnnotationPresent(InCenter.class)) {
            // поместить в центр экрана
            // (!) важно, чтобы размер окна устанавливался ДО вызова следующего метода
            frame.setLocationRelativeTo(null);
        } else if (controller.isAnnotationPresent(FramePosition.class)) {
            FramePosition p = controller.getAnnotation(FramePosition.class);
            frame.setLocation(p.x(), p.y());//разместить по указанным координатам
        } else {
            frame.setLocationByPlatform(true);//размещение по умолчанию
        }
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
        // Удаление из групп
        GroupManager.getInstance().removeObjectsOfOwner(object);
        if (frames.isEmpty()) {
            ReflectionGUI.getInstance().shutdownApplication();
        }
    }
}
