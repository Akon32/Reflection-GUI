package reflectiongui.demo;

import reflectiongui.annotations.FrameSize;
import reflectiongui.annotations.InCenter;
import reflectiongui.annotations.Title;

@InCenter
@FrameSize(width = 300, height = 270)
public class Calculator {
    Double a = 0d;
    Double b = 0d;
    Double result = 0d;
    @Title("Number of clicks")
    int n;

    void add() {
        result = a + b;
        n++;
    }

    void sub() {
        result = a - b;
        n++;
    }

    void mul() {
        result = a * b;
        n++;
    }

    void div() {
        result = a / b;
        n++;
    }
}