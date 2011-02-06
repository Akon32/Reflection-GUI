package reflectiongui.demo;

import reflectiongui.ReflectionGUI;

/** Простейшее демо ReflectionGUI - программа, работающая со строками. */
public class StringReverser {

    public static void main(String[] args) {
        ReflectionGUI.getInstance().addObjects(new Reverser());
    }
}

class Reverser {
    String a, b, r;

    public String reverse(String string) {
        StringBuilder b = new StringBuilder(string);
        return b.reverse().toString();
    }

    String add() {
        return r = a + b;
    }
}