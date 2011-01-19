package reflectiongui;

import reflectiongui.controllers.DesktopController;
import reflectiongui.renderers.DesktopRenderer;

/**
 * Класс-синглетон, создающий графический интерфейс на основе данных о типе объекта.
 */
public class ReflectionGUI {
    private static ReflectionGUI instance;
    private DesktopController desktopController;

    protected ReflectionGUI() {
    }

    /**
     * Установить класс, объект которого будет использоваться в качестве DesktopRenderer'а.
     *
     * @param rendererClass класс используемого DesktopRenderer'а
     * @see #getDesktopController()
     */
    public void setDesktopRendererClass(Class<? extends DesktopRenderer> rendererClass) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Получить контроллер рабочего стола.
     * Выполняется ленивая инициализация поля контроллера.
     * Контроллер будет использовать DesktopRenderer определенного класса,
     * этот класс можно указать методом {@link ReflectionGUI#setDesktopRendererClass(Class)}
     * <u>до</u> первого вызова данного метода.
     * Если метод {@link ReflectionGUI#setDesktopRendererClass(Class)}
     * не вызывать, будет использоваться некоторый класс по умолчанию.
     *
     * @return контроллер рабочего стола.
     */
    public DesktopController getDesktopController() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * @return возвращает экземпляр объекта ReflectionGUI.
     */
    public static ReflectionGUI getInstance() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Добавить объекты в систему
     *
     * @param objects объект
     */
    public void addObjects(Object... objects) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Удалить объекты из системы
     *
     * @param objects
     */
    public void removeObjects(Object... objects) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
