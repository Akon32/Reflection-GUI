package reflectiongui.demo;

import reflectiongui.annotations.Ignored;
import reflectiongui.annotations.Position;
import reflectiongui.annotations.RenderObjectBy;
import reflectiongui.annotations.Title;
import reflectiongui.renderers.standard.PlainObjectRenderer;

@RenderObjectBy(PlainObjectRenderer.class)
public class Reverser {
    @Position(2)
    String a;
    @Position(3)
    String b;
    @Position(1)
    String r;

    @Position(0)
    public String reverse(@Title("string") String string) {
        StringBuilder b = new StringBuilder(string);
        return b.reverse().toString();
    }

    @Ignored
    private void a() {
    }
}

