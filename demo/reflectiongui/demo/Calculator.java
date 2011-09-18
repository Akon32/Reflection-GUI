package reflectiongui.demo;

import reflectiongui.annotations.*;

@InCenter
@FrameSize(width = 300, height = 270)
@DefaultIgnore
public class Calculator {
    @Show
    Double a = 0d;
    @Show
    Double b = 0d;
    @Show
    Double result = 0d;
    @Show
    @Title("Number of clicks")
    int n;

    @Show
    void add() {
        result = a + b;
        n++;
    }

    @Show
    void sub() {
        result = a - b;
        n++;
    }

    @Show
    void mul() {
        result = a * b;
        n++;
    }

    @Show
    void div() {
        result = a / b;
        n++;
    }

    void emptyMethod() {
    }
}
