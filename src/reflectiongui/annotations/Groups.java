package reflectiongui.annotations;

import java.lang.annotation.*;

/**
 * Указывает на принадлежность элемента к "локальным" группам.
 * "Локальная" группа идентифицируется именем и ссылкой на объект-владелец,
 * в котором объявлены её элементы.
 *
 * @see GlobalGroups
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface Groups {
    /** Имена групп, к которым принадлежит аннотированный элемент */
    String[] value();
}
