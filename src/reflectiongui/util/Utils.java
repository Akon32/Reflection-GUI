package reflectiongui.util;

/** Различные утилиты. */
public final class Utils {
    private Utils() {
    }

    /**
     * Найти в массиве объект заданного класса.
     *
     * @param array массив аннотаций.
     * @param clazz класс искомого объекта.
     * @param <T>   тип искомого объекта.
     * @return первый найденный объект, или null, если ничего не найдено.
     */
    public static <T> T findObjectOfClass(Object[] array, Class<T> clazz) {
        for (Object a : array)
            if (clazz.isAssignableFrom(a.getClass())) {
                return clazz.cast(a);
            }
        return null;
    }
}
