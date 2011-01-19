package reflectiongui.annotations;

import java.lang.annotation.*;

/**
 * Выбор класса, которым нужно отображать аннотированный объект, поле, метод или параметр.
 * Класс должен быть Renderer'ом соответствующего типа (объекта, поля или переменной).
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface RenderBy {
    Class value();
}
