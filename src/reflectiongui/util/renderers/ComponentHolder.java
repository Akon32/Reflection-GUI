package reflectiongui.util.renderers;

/**
 * Хранитель компонента, реализующий интерфейс {@link Comparable}.
 * <p/>
 * Используется в renderer'ах для упорядочивания созданных компонентов JComponent
 * по значениям аннотаций {@link reflectiongui.annotations.Position}.
 *
 * @param <T> тип хранимого компонента.
 */
public interface ComponentHolder<T> extends Comparable<ComponentHolder<T>> {
    /** Хранимый компонент. */
    T getComponent();

    /**
     * Позиция компонента.
     * <p/>
     * Берётся из значения аннотации {@link reflectiongui.annotations.Position},
     * которой аннотирован объект, соответствующий компоненту.
     * Если такой аннотации нет, то возвращается -1.
     */
    int getPositionValue();

    /**
     * Уникальный номер хранителя.
     * <p/>
     * Этот номер может быть уникальным в пределах фабрики хранителей.
     * Нужен, чтобы различать хранители с одинаковыми значениями позиции.
     *
     * @see #getPositionValue()
     */
    int getIndex();
}
