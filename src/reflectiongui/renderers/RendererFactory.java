package reflectiongui.renderers;

import reflectiongui.annotations.RenderMethodBy;
import reflectiongui.annotations.RenderObjectBy;
import reflectiongui.annotations.RenderPropertyBy;
import reflectiongui.annotations.RenderVariableBy;
import reflectiongui.util.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Фабрика для создания представлений (renderer'ов)
 * объектов, методов, свойств.
 */
public class RendererFactory {
    private static RendererFactory instance = new RendererFactory();

    /** Ресурс, содержащий стандартные соответствия Class -> VariableRenderer */
    private final String TYPE_RENDERERS_RESOURCE_PATH = "/META-INF/std-type-renderers.properties";
    /** Ресурс, содержащий стандартные имена классов renderer'ов (methodrenderer, propertyrenderer, objectrenderer) */
    private final String RENDERER_CLASSES_RESOURCE_PATH = "/META-INF/std-renderers.properties";
    /** Ресурс, содержащий пользовательские соответствия Class -> VariableRenderer */
    private final String CUSTOM_TYPE_RENDERERS_RESOURCE_PATH = "/META-INF/type-renderers.properties";
    /**
     * Ресурс, содержащий пользовательские имена классов renderer'ов
     * (methodrenderer, propertyrenderer, objectrenderer, desktoprenderer)
     */
    private final String CUSTOM_RENDERER_CLASSES_RESOURCE_PATH = "/META-INF/renderers.properties";

    /** Соответствие типов переменных renderer'ам типов. type -> rendererClass */
    @SuppressWarnings("unchecked")//ну что тут проверять? HashMap он и есть Map<....>.
    private Map<Class, Class<? extends VariableRenderer>> type2renderer = new HashMap();
    /** Используемый по умолчанию класс renderer'а методов. */
    private Class<? extends MethodRenderer> defaultMethodRendererClass;
    /** Используемый по умолчанию класс renderer'а свойств. */
    private Class<? extends PropertyRenderer> defaultPropertyRendererClass;
    /** Используемый по умолчанию класс renderer'а объектов. */
    private Class<? extends ObjectRenderer> defaultObjectRendererClass;
    /** Используемый по умолчанию класс renderer'а рабочего стола. */
    private Class<? extends DesktopRenderer> desktopRendererClass;

    protected RendererFactory() {
        initRendererClasses();
    }

    public static RendererFactory getInstance() {
        return instance;
    }

    /**
     * Создать renderer объекта в соответствии с аннотациями его класса.
     *
     * @param clazz класс объекта.
     * @return renderer объекта.
     */
    public ObjectRenderer createObjectRenderer(Class clazz) {
        RenderObjectBy renderBy = ((AnnotatedElement) clazz).getAnnotation(RenderObjectBy.class);
        Class<? extends ObjectRenderer> rc = renderBy == null ? defaultObjectRendererClass : renderBy.value();
        try {
            return rc.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Создать renderer свойства в соответствии с аннотациями поля.
     *
     * @param field поле.
     * @return renderer свойства.
     */
    public VariableRenderer createPropertyRenderer(Field field) {
        VariableRenderer variableRenderer = createVariableRenderer(field.getType(), field.getAnnotations());
        RenderPropertyBy renderBy = field.getAnnotation(RenderPropertyBy.class);
        Class<? extends PropertyRenderer> rc = renderBy == null ? defaultPropertyRendererClass : renderBy.value();
        try {
            PropertyRenderer propertyRenderer = rc.getConstructor(VariableRenderer.class).newInstance(variableRenderer);
            return propertyRenderer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Создать renderer метода в соответствии с аннотациями метода.
     *
     * @param method метод.
     * @return renderer метода.
     */
    public MethodRenderer createMethodRenderer(Method method) {
        RenderMethodBy renderBy = method.getAnnotation(RenderMethodBy.class);
        Class<? extends MethodRenderer> rc = renderBy == null ? defaultMethodRendererClass : renderBy.value();
        try {
            return rc.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Создать renderer переменной в соответствии с переданным типом и аннотациями.
     *
     * @param type        тип переменной
     * @param annotations аннотации переменной.
     * @return renderer свойства.
     * @throws IllegalArgumentException если не удалось найти renderer для переданного типа.
     */
    public VariableRenderer createVariableRenderer(Class type, Annotation[] annotations)
            throws IllegalArgumentException {
        RenderVariableBy renderBy = Utils.findObjectOfClass(annotations, RenderVariableBy.class);
        Class<? extends VariableRenderer> rc = renderBy == null ? type2renderer.get(type) : renderBy.value();
        if (rc == null) {
            throw new IllegalArgumentException("Can`t find type renderer for type: " + type.getName());
        }
        try {
            return rc.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Class<? extends DesktopRenderer> getDesktopRendererClass() {
        return desktopRendererClass;
    }

    public void setDesktopRendererClass(Class<? extends DesktopRenderer> desktopRendererClass) {
        this.desktopRendererClass = desktopRendererClass;
    }

    public DesktopRenderer createDesktopRenderer() {
        try {
            return desktopRendererClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //TODO определиться, нужны ли следующие 2 метода.
    public void registerRenderer(Class rendererClass) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void unregisterRenderer(Class rendererClass) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Загрузка из ресурсов
     * соответствия renderer'ов определенным классам.
     */
    private void initRendererClasses() {
        Properties renderers = loadPropertiesFromResources(RENDERER_CLASSES_RESOURCE_PATH, CUSTOM_RENDERER_CLASSES_RESOURCE_PATH);
        try {
            defaultMethodRendererClass = Class.forName(renderers.getProperty("methodrenderer")).asSubclass(MethodRenderer.class);
            defaultPropertyRendererClass = Class.forName(renderers.getProperty("propertyrenderer")).asSubclass(PropertyRenderer.class);
            defaultObjectRendererClass = Class.forName(renderers.getProperty("objectrenderer")).asSubclass(ObjectRenderer.class);
            desktopRendererClass = Class.forName(renderers.getProperty("desktoprenderer")).asSubclass(DesktopRenderer.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can`t find correct renderer classes", e);
        }
        // type renderer classes
        Properties typeRenderers = loadPropertiesFromResources(TYPE_RENDERERS_RESOURCE_PATH, CUSTOM_TYPE_RENDERERS_RESOURCE_PATH);
        for (String s : typeRenderers.stringPropertyNames()) {
            try {
                type2renderer.put(Class.forName(s), Class.forName(typeRenderers.getProperty(s)).asSubclass(VariableRenderer.class));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Can`t find correct type renderer class for type " + s, e);
            }
        }

    }

    /**
     * Последовательно загрузить свойства из ресурсов по указанным путям.
     * <p/>
     * Если есть несколько ресурсов, содержащих одинаковое свойство (с одним и тем же именем),
     * то возвращаемое значение свойства будет взято из последнего такого ресурса.
     * Если ресурс с указанным именем не найден, имя пропускается.
     *
     * @param resourceNames пути ресурсов. Отсчитываются от текущего класса.
     * @return свойства, считанные из ресурсов.
     * @throws IllegalArgumentException если какой-либо ресурс из resourceNames
     *                                  имеет неверный формат Properties.
     * @throws RuntimeException         при исключении {@link IOException}, возникшем при работе с ресурсом
     *                                  (что маловероятно).
     */
    private Properties loadPropertiesFromResources(String... resourceNames) throws IllegalArgumentException {
        Properties p = new Properties();
        for (String name : resourceNames) {
            InputStream stream = getClass().getResourceAsStream(name);
            if (stream == null) {
                //пропускаем имя, если ресурс не найден.
                continue;
            }
            //при любых других ошибках прерываем выполнение метода - выбрасываем  исключение.
            try {
                try {
                    p.load(stream);//здесь может быть IllegalArgumentException
                } finally {
                    stream.close();//закрытие ресурса по идее должно быть без исключений.
                }
            } catch (IOException e) {
                throw new RuntimeException("Exception while reading from resource " + name, e);
            }
        }
        return p;
    }
}
