package reflectiongui.demo;

import reflectiongui.annotations.Ignored;
import reflectiongui.annotations.Title;

public class Reverser {
    String a, b, r;

    public String reverse(@Title("string") String string) {
        StringBuilder b = new StringBuilder(string);
        return b.reverse().toString();
    }

    @Ignored
    private void a() {
    }
}

