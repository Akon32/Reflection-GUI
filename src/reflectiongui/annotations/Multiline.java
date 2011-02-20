package reflectiongui.annotations;

import java.lang.annotation.*;

/**
 * Аннотированный элемент следует отображать как многострочный.
 *
 * @see reflectiongui.renderers.standard.StringRenderer
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
public @interface Multiline {
}
