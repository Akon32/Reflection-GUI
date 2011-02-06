package reflectiongui.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация, указывающая,
 * под каким заголовком следует отображать аннотированный элемент.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Title {
    String value();
}
