package reflectiongui.demo;


import reflectiongui.annotations.FileDialog;
import reflectiongui.annotations.Multiline;
import reflectiongui.annotations.Title;
import reflectiongui.annotations.WithSetButton;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Types {
    @Title("Matrix of double")
    @WithSetButton
    double[][] matrix = new double[3][4];
    @Title("Vector of double")
    @WithSetButton
    double[] vector = new double[5];
    @Title("double")
    @WithSetButton
    double d = 15.36;
    @Title("Double")
    @WithSetButton
    Double D = 16.35;
    @Title("int")
    @WithSetButton
    int i;
    @Title("Integer")
    @WithSetButton
    Integer I;
    @Title("long")
    @WithSetButton
    long l;
    @Title("Long")
    @WithSetButton
    long L;
    @Title("String")
    @WithSetButton
    String s = "text";
    @Title("String (multiline)")
    @Multiline
    @WithSetButton("Установить")
    String S = "multiline\ntext";
    @Title("Unknown type")
    List list = Arrays.asList("text", 25, 30.0);
    @Title("Enum")
    EnumDemo e = EnumDemo.Element1;
    @Title("File open")
    @FileDialog(type = FileDialog.Type.OPEN,
            approveText = "Открыть файл",
            title = "Открыть")
    File open;
    @Title("File save")
    @FileDialog(type = FileDialog.Type.SAVE,
            title = "сохранить")
    File save;
    @Title("Directory")
    @FileDialog(selectionMode = FileDialog.DIRECTORIES_ONLY,
            approveText = "select directory",
            useCurrentDir = false)
    File dir;


    public static enum EnumDemo {
        Element1, Element2 {
            @Override
            public String toString() {
                return "Element #2";
            }
        }
    }
}
