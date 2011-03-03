package reflectiongui.annotations;

import java.lang.annotation.*;

/**
 * Аннотация указывает PropertyRenderer'у,
 * что нужно отображать кнопку установки свойства.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface WithSetButton {
    /** Подпись кнопки */
    String value() default "Set";
}
