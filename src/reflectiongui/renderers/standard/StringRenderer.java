package reflectiongui.renderers.standard;

import reflectiongui.annotations.Renders;

/** Renderer типа для String. */
@Renders(java.lang.String.class)
public class StringRenderer extends AbstractSimpleVariableRenderer {
    @Override
    protected String objectToString(Object obj) {
        return (String) obj;
    }

    @Override
    protected Object stringToObject(String str) {
        return str;
    }
}
