package reflectiongui.demo;

import reflectiongui.annotations.*;
import reflectiongui.renderers.standard.PlainObjectRenderer;

import java.util.Arrays;
import java.util.List;

@RenderObjectBy(PlainObjectRenderer.class)
@FramePosition(x = 20, y = 30)
@FrameSize(width = 300, height = 250)
@Title("StringReverser")
public class Reverser {
    @Position(2)
    String a;
    @Position(3)
    String b;
    @Multiline
    @Position(1)
    String r = "multiline\ntext";
    List<Double> list = Arrays.asList(1d, 2d, 3d);

    @Position(0)
    public String reverse(@Title("string") String string, @Title("upper case") boolean upper) {
        StringBuilder b = new StringBuilder(upper ? string.toUpperCase() : string);
        return b.reverse().toString();
    }

    @Ignored
    private void a() {
    }
}
