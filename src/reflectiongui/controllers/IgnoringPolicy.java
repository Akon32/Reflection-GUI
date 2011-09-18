package reflectiongui.controllers;

import reflectiongui.annotations.Ignored;
import reflectiongui.annotations.Show;

import java.lang.reflect.AnnotatedElement;

/** Политика игнорирования/отображения элементов. */
public enum IgnoringPolicy {
    /** Игнорировать элементы, если они не имеют аннотации {@link Show} */
    DEFAULT_IGNORE {
        @Override
        public boolean needShow(AnnotatedElement e) {
            exceptionIfConflicts(e);
            return e.isAnnotationPresent(Show.class);
        }
    },
    /** Показывать элементы, если они не имеют аннотации {@link Ignored} */
    DEFAULT_SHOW {
        @Override
        public boolean needShow(AnnotatedElement e) {
            exceptionIfConflicts(e);
            return !e.isAnnotationPresent(Ignored.class);
        }
    };

    /**
     * Проверить, следует ли отображать элемент в соответствии с данной политикой.
     *
     * @param e аннотированный элемент.
     * @return true, если элемент необходимо отображать.
     * @throws IllegalArgumentException если обе аннотации {@link Ignored} и {@link Show} присутствуют.
     */
    public abstract boolean needShow(AnnotatedElement e) throws IllegalArgumentException;

    /**
     * Выбросить исключение если есть противоречия в аннотациях.
     *
     * @param e аннотированный элемент.
     * @throws IllegalArgumentException если обе аннотации {@link Ignored} и {@link Show} присутствуют.
     */
    protected void exceptionIfConflicts(AnnotatedElement e) throws IllegalArgumentException {
        if (e.isAnnotationPresent(Ignored.class) && e.isAnnotationPresent(Show.class)) {
            throw new IllegalArgumentException("Both @Ignored and @Show are present");
        }
    }
}
