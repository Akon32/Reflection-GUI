package reflectiongui.renderers.standard;


import reflectiongui.ReflectionGUI;
import reflectiongui.annotations.FramePosition;
import reflectiongui.annotations.FrameSize;
import reflectiongui.annotations.InCenter;
import reflectiongui.controllers.ObjectController;
import reflectiongui.controllers.ObjectFrameUpdater;
import reflectiongui.grouping.GroupManager;
import reflectiongui.renderers.DesktopRenderer;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/** DesktopRenderer, отображающий объекты в одном окне. */
public class DesktopPaneDesktopRenderer implements DesktopRenderer {

    private Map<Object, JInternalFrame> frames = new HashMap<Object, JInternalFrame>();
    private JDesktopPane desktopPane;
    private JFrame mainFrame;

    @Override
    public void addObject(Object object) {
        if (frames.containsKey(object)) {
            throw new IllegalArgumentException("Given object is already shown");
        }
        if (frames.isEmpty()) {
            initDesktopPane();
        }
        JInternalFrame frame = createFrame(object);
        frames.put(object, frame);
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    private void initDesktopPane() {
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        desktopPane = new JDesktopPane();
        mainFrame.getContentPane().add(desktopPane);
    }

    private JInternalFrame createFrame(final Object object) {
        JInternalFrame frame = new JInternalFrame();
        // добавление в группы происходит при вызове конструктора ObjectController
        ObjectController controller = new ObjectController(object);
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
            frame.setLocation(new Point((desktopPane.getWidth() - frame.getWidth()) / 2,
                    (desktopPane.getHeight() - frame.getHeight()) / 2));
        } else if (controller.isAnnotationPresent(FramePosition.class)) {
            FramePosition p = controller.getAnnotation(FramePosition.class);
            frame.setLocation(p.x(), p.y());//разместить по указанным координатам
        }
        frame.setClosable(true);
        frame.setResizable(true);
        frame.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                removeObject(object);
            }
        });
        // создание ObjectFrameUpdater'а и установка с помощью него параметров (заголовка).
        ObjectFrameUpdater frameUpdater = new JInternalFrameObjectFrameUpdater(frame, controller);
        controller.setFrameUpdater(frameUpdater);
        frameUpdater.updateFrame();
        return frame;
    }

    @Override
    public void removeObject(Object object) {
        JInternalFrame frame = frames.remove(object);
        if (frame != null) {
            desktopPane.remove(frame);
            frame.dispose();
        }
        // Удаление из групп
        GroupManager.getInstance().removeObjectsOfOwner(object);
        if (frames.isEmpty()) {
            mainFrame.dispose();
            ReflectionGUI.getInstance().shutdownApplication();
        }
    }

    /** ObjectFrameUpdater, используемый для обновления окон объектов. */
    private class JInternalFrameObjectFrameUpdater implements ObjectFrameUpdater {
        private final JInternalFrame frame;
        private final ObjectController controller;

        private JInternalFrameObjectFrameUpdater(JInternalFrame frame, ObjectController controller) {
            this.frame = frame;
            this.controller = controller;
        }

        @Override
        public void updateFrame() {
            frame.setTitle(controller.getTitle());
        }
    }
}
