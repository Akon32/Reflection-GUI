package reflectiongui.demo;

import reflectiongui.ReflectionGUI;
import reflectiongui.annotations.Title;

/** Простейшее демо ReflectionGUI - программа, работающая со строками. */
public class StringReverser {

    public static void main(String[] args) {
        ReflectionGUI.getInstance().addObjects(new Reverser());
    }
}

class Reverser {
    String a, b, r;
    private Double ax = 0d, bx = 0d, cx = 0d;

    public String reverse(@Title("string") String string) {
        StringBuilder b = new StringBuilder(string);
        return b.reverse().toString();
    }

    @Title("concat")
    String add() {
        return r = a + b;
    }

    Double sum() {
        return cx = ax + bx;
    }
}
