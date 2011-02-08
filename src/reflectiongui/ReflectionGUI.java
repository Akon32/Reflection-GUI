package reflectiongui;

import reflectiongui.renderers.DesktopRenderer;
import reflectiongui.renderers.RendererFactory;

/**
 * Класс-синглетон, создающий графический интерфейс
 * на основе данных о типе объекта.
 */
public class ReflectionGUI {
    private static ReflectionGUI instance;
    private DesktopRenderer desktopRenderer;

    private ReflectionGUI() {
        desktopRenderer = RendererFactory.getInstance().createDesktopRenderer();
    }

    /**
     * Установить класс, объект которого будет использоваться в качестве DesktopRenderer'а.
     *
     * @param rendererClass класс используемого DesktopRenderer'а
     */
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
            desktopRenderer.addObject(o);
        }
    }

    /**
     * Удалить объекты из системы
     *
     * @param objects объекты
     */
    public void removeObjects(Object... objects) {
        for (Object o : objects) {
            desktopRenderer.removeObject(o);
        }
    }

    /** Завершить приложение. */
    public void shutdownApplication() {
        System.exit(0);
    }
}
