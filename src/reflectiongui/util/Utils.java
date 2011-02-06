package reflectiongui.util;

import reflectiongui.annotations.Title;

import java.lang.reflect.AnnotatedElement;

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

    /**
     * Получить заголовок для переданного элемента.
     *
     * @param element      аннотированный элемент.
     * @param defaultValue значение заголовка по умолчанию.
     * @return значение заголовка, взятое из аннотации {@link Title} элемента,
     *         или defaultValue, если элемент не имеет требуемой аннотации.
     * @see Title
     */
    public static String getTitleFromAnnotations(AnnotatedElement element, String defaultValue) {
        Title a = element.getAnnotation(Title.class);
        return a == null ? defaultValue : a.value();
    }
}
