package reflectiongui.grouping;

import java.util.*;

/**
 * Менеджер групп объектов.
 * Позволяет хранить произвольное количество именованных коллекций (групп)
 * различных объектов, и выбирать по имени группы объекты, принадлежащие группе.
 * <p/> Различаются локальные и глобальные группы.
 * <p/> Для поиска элементов локальной группы нужно указать не только её имя,
 * но и объект-владелец. Каждый объект, отображаемый Reflection-GUI, может иметь
 * свои локальные группы, независимые от одноименных групп, принадлежащих другим
 * объектам, и от одноименных глобальных групп.
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
    /** Хранение соответствия объект-владелец -> [(группа,элемент)] для глобальных групп */
    private Map<Object, Set<TableEntry>> globalGroupTables = new HashMap();

    /** Пустая немодифицируемая коллекция, которая возвращается методами поиска, если ничего не найдено */
    private final Collection EMPTY_COLLECTION = Collections.unmodifiableCollection(Collections.emptyList());

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
     *         Если объектов не найдено, возвращается пустая коллекция.
     */
    public Collection lookup(Object object, String name) {
        Map<String, Collection> collections = localGroups.get(object);
        if (collections == null) {
            return EMPTY_COLLECTION;
        }
        Collection res = collections.get(name);
        if (res == null) {
            return EMPTY_COLLECTION;
        }
        return Collections.unmodifiableCollection(res);
    }

    /**
     * Найти объекты, принадлежащие глобальной группе с заданным именем.
     *
     * @param name имя группы.
     * @return немодифицируемую коллекцию объектов, принадлежащих группе.
     *         Если объектов не найдено, возвращается пустая коллекция.
     */
    public Collection lookupGlobal(String name) {
        Collection res = globalGroups.get(name);
        if (res == null) {
            return EMPTY_COLLECTION;
        }
        return Collections.unmodifiableCollection(res);
    }

    /**
     * Добавить объект во все указанные (локальные) группы.
     *
     * @param owner      объект-владелец группы.
     * @param obj        добавляемый объект.
     * @param groupNames список имен групп.
     */
    public void addToGroups(Object owner, Object obj, String... groupNames) {
        if (groupNames.length == 0) {
            return;
        }
        // получаем группы нужного объекта-владельца,
        // при необходимости создаем коллекцию.
        Map<String, Collection> groups = localGroups.get(owner);
        if (groups == null) {
            localGroups.put(owner, groups = new HashMap<String, Collection>());
        }
        // добавляем объект в группы.
        for (String name : groupNames) {
            // получаем коллекцию объектов группы,
            // при необходимости создаем её.
            Collection c = groups.get(name);
            if (c == null) {
                groups.put(name, c = new ArrayList());
            }
            // добавление в группу.
            c.add(obj);
        }
    }

    /**
     * Добавить объект во все указанные глобальные группы.
     *
     * @param owner      объект, содержащий добавляемый объект.
     * @param obj        добавляемый объект.
     * @param groupNames список имен групп.
     */
    public void addToGlobalGroups(Object owner, Object obj, String... groupNames) {
        if (groupNames.length == 0) {
            return;
        }
        // получаем таблицу элементов для данного владельца.
        // если нужно, создаем таблицу
        Set<TableEntry> table = globalGroupTables.get(owner);
        if (table == null) {
            globalGroupTables.put(owner, table = new HashSet<TableEntry>());
        }
        // добавляем объект в группы.
        for (String name : groupNames) {
            // получаем коллекцию объектов группы,
            // при необходимости создаем её.
            Collection c = globalGroups.get(name);
            if (c == null) {
                globalGroups.put(name, c = new ArrayList());
            }
            // добавление объекта в группу.
            c.add(obj);
            // добавление записи об объекте в таблицу
            table.add(new TableEntry(name, obj));
        }
    }

    /**
     * Удалить из групп все объекты, принадлежащие данному объекту-владельцу.
     * <p/>
     * Удаление производится и из локальных, и из глобальных групп.
     *
     * @param owner объект-владелец.
     */
    public void removeObjects(Object owner) {
        removeLocal(owner);
        removeGlobal(owner);
    }

    private void removeLocal(Object owner) {
        localGroups.remove(owner);
    }

    private void removeGlobal(Object owner) {
        Set<TableEntry> entries = globalGroupTables.remove(owner);
        if (entries == null) {
            return; // nothing to do
        }
        // перечисляем все элементы таблицы, принадлежащие owner.
        for (TableEntry e : entries) {
            Collection c = globalGroups.get(e.groupName);
            if (c != null) {
                // удаление объекта из группы.
                c.remove(e.object);
                if (c.size() == 0) {
                    // удаление пустой группы.
                    globalGroups.remove(e.groupName);
                }
            }
        }
    }

    private static class TableEntry {
        public final String groupName;
        public final Object object;

        public TableEntry(String groupName, Object object) {
            this.groupName = groupName;
            this.object = object;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TableEntry)) return false;
            TableEntry entry = (TableEntry) o;
            if (groupName != null ? !groupName.equals(entry.groupName) : entry.groupName != null) {
                return false;
            }
            if (object != null ? !object.equals(entry.object) : entry.object != null) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int result = groupName != null ? groupName.hashCode() : 0;
            result = 31 * result + (object != null ? object.hashCode() : 0);
            return result;
        }
    }
}
