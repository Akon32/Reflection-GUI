package reflectiongui.annotations;

import java.lang.annotation.*;

/**
 * Аннотация, указывающая, что поле или метод
 * должны отображаться с помощью Reflection-GUI.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Show {
}
