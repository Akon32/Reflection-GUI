package reflectiongui.controllers;

import reflectiongui.annotations.TitleField;
import reflectiongui.util.Utils;

import java.lang.reflect.Field;

/**
 * Класс, способный получать заголовок объекта из данных объекта.
 * <p/>
 * Заголовок может быть считан из
 * <ol><li>имени класса</li>
 * <li>аннотации {@link reflectiongui.annotations.Title} класса</li>
 * <li>поля типа {@link String} с аннотацией {@link reflectiongui.annotations.TitleField}
 * (значение поля может меняться в процессе выполнения программы)</li><ol>
 * Если присутствуют оба типа аннотаций, заголовок берется из поля.
 * <p/>
 * Аннотацией {@link reflectiongui.annotations.TitleField} может быть аннотировано
 * только одно поле, и оно должно иметь тип {@link String}.
 */
public class TitleGetter {
    private Object object;
    private String title;
    private Field titleField;

    public TitleGetter(Object object) {
        this.object = object;
        Class clazz = object.getClass();
        title = Utils.getTitleFromAnnotations(clazz, clazz.getSimpleName());
        titleField = findTitleField(clazz);
    }

    /**
     * Найти поле с аннотацией {@link reflectiongui.annotations.TitleField}.
     * Также разрешается доступ к полю, если оно найдено.
     *
     * @param clazz класс объекта.
     * @return поле с аннотацией {@link reflectiongui.annotations.TitleField},
     *         или null, если ни одного поля с такой аннотацией не найдено.
     * @throws IllegalArgumentException если найдено более одного поля с аннотацией
     *                                  {@link reflectiongui.annotations.TitleField},
     *                                  или найдено поле с такой аннотацией, но
     *                                  типа не {@link String}.
     */
    private Field findTitleField(Class clazz) throws IllegalArgumentException {
        Field res = null;
        for (Field f : clazz.getDeclaredFields()) {
            if (f.isAnnotationPresent(TitleField.class)) {
                if (res != null) {
                    throw new IllegalArgumentException("More than 1 @TitleField in " + clazz.getCanonicalName());
                } else if (!String.class.isAssignableFrom(f.getType())) {
                    throw new IllegalArgumentException(
                            "Type of field " + f.getName() + " in " + clazz.getCanonicalName() +
                                    "with @TitleField is not String");
                } else {
                    res = f;
                }

            }
        }
        if (res != null) {
            res.setAccessible(true);
        }
        return res;
    }

    private boolean titleFieldExists() {
        return titleField != null;
    }

    public String getTitle() {
        try {
            return titleFieldExists() ? (String) titleField.get(object) : title;
        } catch (IllegalAccessException e) {
            // этого исключения появляться не должно
            throw new RuntimeException("Unexpected exception", e);
        } catch (IllegalArgumentException e) {
            // этого исключения появляться не должно
            throw new RuntimeException("Unexpected exception", e);
        }
    }
}
