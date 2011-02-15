package reflectiongui.annotations;

import java.lang.annotation.*;

/**
 * Аннотация, указывающая, что окна аннотированного класса
 * следует отображать в центре экрана.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface InCenter {
}
