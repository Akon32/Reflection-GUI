package reflectiongui.annotations;

import java.lang.annotation.*;

/** Аннатация, указывающая порядковый номер элемента при отображении. */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Position {
    int value();
}
