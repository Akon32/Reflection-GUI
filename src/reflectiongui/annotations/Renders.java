package reflectiongui.annotations;

import java.lang.annotation.*;

/**
 * Указание, объект какого класса может отображать аннотированный Renderer.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Renders {
    Class value();
}
