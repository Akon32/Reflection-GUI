package reflectiongui.demo;

import reflectiongui.annotations.*;
import reflectiongui.renderers.standard.ArrayRenderer;
import reflectiongui.renderers.standard.PlainObjectRenderer;

import java.util.Arrays;

@RenderObjectBy(PlainObjectRenderer.class)
public class ArraysDemo {
    @TableSize(columnCount = 2, rowCount = 2)
    //@RenderVariableBy(ArrayRenderer.class)
            EnumDemo[] enums = {EnumDemo.Element1, EnumDemo.Element2, EnumDemo.Element2};

    public static enum EnumDemo {
        Element1, Element2 {
            @Override
            public String toString() {
                return "Element #2";
            }
        }
    }

    @TableSize(columnCount = 3, rowCount = 3)
    @RenderVariableBy(ArrayRenderer.class)
    String[] str = new String[9];

    {
        for (int i = 0; i < str.length; i++) {
            str[i] = String.valueOf(i);
        }
    }

    Object strPlain;

    @Title("Транспонировать")
    void transpose() {
        strPlain = Arrays.toString(str);
        swapStr(1, 3);
        swapStr(2, 6);
        swapStr(5, 7);
    }

    @Ignored
    void swapStr(int i, int j) {
        String tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

}
