package reflectiongui.demo;

import reflectiongui.annotations.FrameSize;
import reflectiongui.annotations.InCenter;

@InCenter
@FrameSize(width = 300, height = 250)
public class Calculator {
    Double a = 0d;
    Double b = 0d;
    Double result = 0d;

    void add() {
        result = a + b;
    }

    void sub() {
        result = a - b;
    }

    void mul() {
        result = a * b;
    }

    void div() {
        result = a / b;
    }
}