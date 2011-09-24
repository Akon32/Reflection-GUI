package reflectiongui.annotations;

import java.lang.annotation.*;

/** Размер таблицы (число строк и столбцов). */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface TableSize {
    int rowCount();

    int columnCount();
}
