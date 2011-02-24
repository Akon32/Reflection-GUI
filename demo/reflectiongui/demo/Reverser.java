package reflectiongui.demo;

import reflectiongui.annotations.*;
import reflectiongui.renderers.standard.PlainObjectRenderer;

@RenderObjectBy(PlainObjectRenderer.class)
@FramePosition(x = 20, y = 30)
@FrameSize(width = 300, height = 200)
@Title("StringReverser")
public class Reverser {
    @Position(2)
    String a;
    @Position(3)
    String b;
    @Multiline
    @Position(1)
    String r = "multiline\ntext";

    @Position(0)
    public String reverse(@Title("string") String string, @Title("upper case") Boolean upper) {
        StringBuilder b = new StringBuilder(upper ? string.toUpperCase() : string);
        return b.reverse().toString();
    }

    @Ignored
    private void a() {
    }
}
