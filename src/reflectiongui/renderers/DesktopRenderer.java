package reflectiongui.renderers;

/**
 * Объект, отвечающий за графическое представление "рабочего стола".
 * На "рабочем столе" находятся объекты, с которыми работает ReflectionGUI.
 */
public interface DesktopRenderer {

    /**
     * Добавить объект на "рабочий стол" программы.
     * <p/>
     * В этом методе должен создаваться контроллер объекта,
     * а следовательно, контроллеры и renderer'ы всех его полей и методов.
     *
     * @param object объект, который должен быть отображен
     *               в графический интерфейс посредством ReflectionGUI.
     */
    void addObject(Object object);

    /**
     * Удалить объект с "рабочего стола".
     * <p/>
     * При удалении всех объектов некоторые реализации DesktopRenderer могут завершать программу.
     * <p/>
     * Вызов этого метода также должен вызывать удаление из всех групп объектов,
     * которыми "владеет" переданный объект.
     *
     * @param object объект, ранее отображённый вызовом {@link #addObject(Object)}.
     * @see reflectiongui.grouping.GroupManager#removeObjectsOfOwner(Object)
     */
    void removeObject(Object object);
}
