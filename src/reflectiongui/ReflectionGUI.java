package reflectiongui;

import reflectiongui.controllers.DesktopController;
import reflectiongui.renderers.DesktopRenderer;
import reflectiongui.renderers.RendererFactory;

/**
 * Класс-синглетон, создающий графический интерфейс
 * на основе данных о типе объекта.
 */
public class ReflectionGUI {
    private static ReflectionGUI instance;
    private DesktopController desktopController;

    protected ReflectionGUI() {
        desktopController = new DesktopController();
        DesktopRenderer renderer = RendererFactory.getInstance().createDesktopRenderer();
        desktopController.setDesktopRenderer(renderer);
        renderer.initialize(desktopController);
    }

    /**
     * Установить класс, объект которого будет использоваться в качестве DesktopRenderer'а.
     *
     * @param rendererClass класс используемого DesktopRenderer'а
     * @deprecated можно устанавливать класс непосредственно через RendererFactory,
     *             можно указывать его в renderers.properties.
     */
    // TODO: подумать, не удалить ли этот метод?
    @Deprecated
    public static void setDesktopRendererClass(Class<? extends DesktopRenderer> rendererClass) {
        RendererFactory.getInstance().setDesktopRendererClass(rendererClass);
    }

    /**
     * Ленивая инициализация синглетона ReflectionGUI
     *
     * @return экземпляр класса ReflectionGUI.
     */
    public static ReflectionGUI getInstance() {
        if (instance == null) {
            instance = new ReflectionGUI();
        }
        return instance;
    }

    /**
     * Добавить объекты в систему
     *
     * @param objects объекты
     */
    public void addObjects(Object... objects) {
        for (Object o : objects) {
            desktopController.addObject(o);
        }
    }

    /**
     * Удалить объекты из системы
     *
     * @param objects объекты
     */
    public void removeObjects(Object... objects) {
        for (Object o : objects) {
            desktopController.removeObject(o);
        }
    }
}
