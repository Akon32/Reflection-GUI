package reflectiongui.grouping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Менеджер групп объектов.
 * Позволяет хранить произвольное количество именованных коллекций (групп)
 * различных объектов, и выбирать по имени группы объекты, принадлежащие группе.
 * <p/> Различаются локальные и глобальные группы.
 * <p/> Для поиска элементов локальной группы нужно указать не только её имя,
 * но и объект-владелец. Каждый объект, отображаемый Reflection-GUI, может иметь
 * свои локальные группы, независимые от одноименных групп, принадлежащих другим
 * объектам или глобальным.
 * <p/> Глобальной группе могут принадлежать элементы, содержащиеся
 * в разных объектах. Для их поиска необходимо указать только имя группы.
 * <p/> При добавлении и удалении элементов (как в локальных группах, так и
 * в глобальных), необходимо указывать объект-владелец элемента.
 *
 * @see reflectiongui.annotations.Groups
 * @see reflectiongui.annotations.GlobalGroups
 */
public class GroupManager {

    private static GroupManager instance = new GroupManager();

    /**
     * Хранение локальных групп.
     * объект-владелец -> имя группы -> коллекция членов группы
     */
    private Map<Object, Map<String, Collection>> localGroups = new HashMap<Object, Map<String, Collection>>();
    /**
     * Хранение глобальных групп.
     * имя группы -> коллекция членов группы
     */
    private Map<String, Collection> globalGroups = new HashMap<String, Collection>();

    // TODO: хранение соответствия объект-владелец -> [(группа,элемент)] для глобальных групп
    private Map<Object, Collection> elements = new HashMap<Object, Collection>();


    private GroupManager() {
    }

    public static GroupManager getInstance() {
        return instance;
    }
    //TODO: не сделать ли следующие методы статическими?

    /**
     * Найти объекты указанной локальной группы для данного объекта.
     *
     * @param object объект-владелец группы.
     * @param name   имя группы.
     * @return немодифицируемую коллекцию объектов, принадлежащих группе.
     */
    public Collection lookup(Object object, String name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Найти объекты, принадлежащие глобальной группе с заданным именем.
     *
     * @param name имя группы.
     * @return немодифицируемую коллекцию объектов, принадлежащих группе.
     */
    public Collection lookupGlobal(String name) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Добавить объект во все указанные группы.
     *
     * @param owner      объект-владелец группы.
     * @param obj        добавляемый объект.
     * @param groupNames список имен групп.
     */
    public void addToGroups(Object owner, Object obj, String... groupNames) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Добавить объект во все указанные глобальные группы.
     *
     * @param owner      объект, содержащий добавляемый объект.
     * @param obj        добавляемый объект.
     * @param groupNames список имен групп.
     */
    public void addToGlobalGroups(Object owner, Object obj, String... groupNames) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Удалить из групп все объекты, принадлежащие данному объекту-владельцу.
     * <p/>
     * Удаление производится и из локальных, и из глобальных групп.
     *
     * @param owner объект-владелец.
     */
    public void removeObjects(Object owner) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
