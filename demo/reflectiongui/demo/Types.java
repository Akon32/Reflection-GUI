package reflectiongui.demo;


import reflectiongui.annotations.Multiline;
import reflectiongui.annotations.Title;

import java.util.Arrays;
import java.util.List;

public class Types {
    @Title("Matrix of double")
    double[][] matrix = new double[3][4];
    @Title("Vector of double")
    double[] vector = new double[5];
    @Title("double")
    double d = 15.36;
    @Title("Double")
    Double D = 16.35;
    @Title("int")
    int i;
    @Title("Integer")
    Integer I;
    @Title("long")
    long l;
    @Title("Long")
    long L;
    @Title("String")
    String s = "text";
    @Title("String (multiline)")
    @Multiline
    String S = "multiline\ntext";
    @Title("Unknown type")
    List list = Arrays.asList("text", 25, 30.0);
}
