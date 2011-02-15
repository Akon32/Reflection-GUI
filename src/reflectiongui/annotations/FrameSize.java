package reflectiongui.annotations;


import java.lang.annotation.*;

/** Размер окна. */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FrameSize {
    int width();

    int height();
}
