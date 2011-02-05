package reflectiongui.annotations;

import reflectiongui.renderers.VariableRenderer;

import java.lang.annotation.*;

/**
 * Выбор класса, которым нужно отображать
 * аннотированную переменную (поле или параметр метода).
 * <p/>
 * Если аннотирован метод, то аннотация относится к его типу результата.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
public @interface RenderVariableBy {
    Class<? extends VariableRenderer> value();
}
