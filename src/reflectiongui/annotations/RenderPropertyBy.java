package reflectiongui.annotations;

import reflectiongui.renderers.PropertyRenderer;

import java.lang.annotation.*;

/**
 * Выбор класса, которым нужно отображать
 * аннотированное свойство (поле).
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RenderPropertyBy {
    Class<? extends PropertyRenderer> value();
}
