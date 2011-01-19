package reflectiongui.renderers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Фабрика для создания представлений объектов, методов, свойств.
 */
public class RendererFactory {
    private static RendererFactory instance;

    protected RendererFactory() {
    }

    public static RendererFactory getInstance() {
        if (instance == null) {
            instance = new RendererFactory();
        }
        return instance;
    }

    public ObjectRenderer createObjectRenderer(Class clazz) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public VariableRenderer createPropertyRenderer(Field variable) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public MethodRenderer createMethodRenderer(Method method) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public VariableRenderer createVariableRenderer(Class clazz, Annotation[] annotations) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public DesktopRenderer createDesktopRenderer(Class<? extends DesktopRenderer> rendererClass) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void registerRenderer(Class rendererClass) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void unregisterRenderer(Class rendererClass) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /*
     * Где-то здесь должны быть методы, считывающие список renderer'ов по умолчанию
     * и список пользовательских renderer'ов, и затем регистрирующие их.
     */

}
