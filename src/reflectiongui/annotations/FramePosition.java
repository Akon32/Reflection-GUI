package reflectiongui.annotations;

import java.lang.annotation.*;

/** Позиция окна на экране. */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FramePosition {
    int x();

    int y();
}
