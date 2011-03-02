package reflectiongui.annotations;

import java.lang.annotation.*;

/** Аннотация, указывающая, что поле содержит заголовок объекта. */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TitleField {
}
