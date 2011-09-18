package reflectiongui.annotations;

import reflectiongui.controllers.IgnoringPolicy;

import java.lang.annotation.*;

/**
 * Аннотация, указывающая политику игнорирования элементов,
 * применяемую по умолчанию.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefaultIgnore {
    /** Игнорировать ли элементы без аннотаций {@link Ignored} и {@link Show} */
    IgnoringPolicy value() default IgnoringPolicy.DEFAULT_IGNORE;
}
