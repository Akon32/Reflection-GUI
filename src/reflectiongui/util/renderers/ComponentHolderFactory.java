package reflectiongui.util.renderers;

import reflectiongui.annotations.Position;

import java.lang.reflect.AnnotatedElement;

/**
 * Фабрика для создания хранителей компонентов.
 * <p/>
 * Хранители, созданные одной фабрикой, имеют различные значения индекса.
 *
 * @see reflectiongui.util.renderers.ComponentHolder
 * @see reflectiongui.util.renderers.ComponentHolder#getIndex()
 */
public class ComponentHolderFactory<T> {

    private int componentHolderNextIndex;

    public ComponentHolderFactory() {
    }

    /**
     * Создать хранитель компонента.
     *
     * @param component   компонент.
     * @param annotations список аннотаций объекта, соответствующего компоненту.
     * @return хранитель компонента.
     */
    public ComponentHolder<T> createHolder(T component, AnnotatedElement annotations) {
        return new ComponentHolderImpl<T>(component, annotations);
    }

    /** Реализация интерфейса {@link ComponentHolder}} */
    private class ComponentHolderImpl<T> implements ComponentHolder<T> {
        public final T component;
        public final int positionValue;
        public final int index;

        public ComponentHolderImpl(T component, AnnotatedElement annotations) {
            this.component = component;
            index = componentHolderNextIndex++;
            Position position = annotations.getAnnotation(Position.class);
            // если аннотация Position отсутствует, считаем её значение равным -1,
            // т.е. помещаем неаннотированный элемент в начало.
            positionValue = position == null ? -1 : position.value();
        }

        @Override
        public T getComponent() {
            return component;
        }

        @Override
        public int getPositionValue() {
            return positionValue;
        }

        @Override
        public int getIndex() {
            return index;
        }

        @Override
        public int compareTo(ComponentHolder<T> o) {
            if (this.getPositionValue() > o.getPositionValue()) {
                return 1;
            } else if (this.getPositionValue() < o.getPositionValue()) {
                return -1;
            } else if (this.getIndex() > o.getIndex()) {
                return 1;
            } else if (this.getIndex() < o.getIndex()) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
