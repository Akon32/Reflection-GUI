package reflectiongui.annotations;

import java.lang.annotation.*;

/**
 * Указывает на принадлежность элемента к "глобальным" группам.
 * "Глобальная" группа идентифицируется только своим именем,
 * в отличие от "локальной" группы.
 *
 * @see Groups
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface GlobalGroups {
    String[] value();
}
