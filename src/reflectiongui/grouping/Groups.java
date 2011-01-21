package reflectiongui.grouping;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Группировка объектов.
 * Объект этого класса хранит произвольное количество
 * именованных коллекций (групп) различных объектов, и позволяет выбирать по имени группы
 * объекты, принадлежащие группе.
 */
//TODO: продумать области видимости групп.
public class Groups {

    private static Groups instance = new Groups();

    // наличие ссылок из этого класса на хранимый объект
    // не должно препятствовать его удалению сборщиком мусора.
    private Map<String, Collection<WeakReference>> groups = new HashMap();

    protected Groups() {
    }

    public static Groups getInstance() {
        return instance;
    }

    /**
     * Добавить объект во все указанные группы. Добавлять объект следует только 1 раз.
     *
     * @param obj        объект.
     * @param groupNames список имен групп.
     */
    public void addObject(Object obj, String... groupNames) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Список объектов в группе.
     *
     * @param groupName имя группы.
     * @return немодифицируемую коллекцию, содержащую объекты группы.
     */
    public Collection getMembers(String groupName) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
