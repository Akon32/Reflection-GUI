package reflectiongui.annotations;

import reflectiongui.renderers.ObjectRenderer;

import java.lang.annotation.*;

/**
 * Выбор класса, которым нужно отображать
 * аннотированный объект.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RenderObjectBy {
    Class<? extends ObjectRenderer> value();
}
