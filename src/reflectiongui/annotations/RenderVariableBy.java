package reflectiongui.annotations;

import reflectiongui.renderers.VariableRenderer;

import java.lang.annotation.*;

/**
 * Выбор класса, которым нужно отображать
 * аннотированную переменную (поле или параметр метода).
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface RenderVariableBy {
    Class<? extends VariableRenderer> value();
}
