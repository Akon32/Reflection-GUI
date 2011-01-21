package reflectiongui.annotations;

import java.lang.annotation.*;

/** Указывает на принадлежность элемента к группам. */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface Group {
    /** Имена групп, к которым принадлежит аннотированный элемент */
    String[] value();
}
