package reflectiongui.annotations;

import reflectiongui.renderers.MethodRenderer;

import java.lang.annotation.*;

/**
 * Выбор класса, которым нужно отображать
 * аннотированный метод.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RenderMethodBy {
    Class<? extends MethodRenderer> value();
}
