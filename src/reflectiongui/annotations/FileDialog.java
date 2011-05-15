package reflectiongui.annotations;

import javax.swing.*;
import java.lang.annotation.*;

/** Аннотация для указания типа диалога выбора файла. */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FileDialog {
    /** Тип диалога */
    Type type() default FileDialog.Type.CUSTOM;

    /** Заголовок диалога */
    String title() default "";

    /** Текст на кнопке подтверждения */
    String approveText() default "";

    /** Показывать ли вначале текущий каталог */
    boolean useCurrentDir() default true;

    /**
     * Режим выбора файлов.
     * <p/>
     * Используйте константы {@link FileDialog#FILES_ONLY}, {@link FileDialog#DIRECTORIES_ONLY},
     * {@link FileDialog#FILES_AND_DIRECTORIES} (они равны {@link JFileChooser#FILES_ONLY},
     * {@link JFileChooser#DIRECTORIES_ONLY}, {@link JFileChooser#FILES_AND_DIRECTORIES} соответственно).
     */
    int selectionMode() default JFileChooser.FILES_AND_DIRECTORIES;

    /** Тип диалога выбора файла */
    public static enum Type {
        OPEN, SAVE, CUSTOM
    }

    // режимы выбора файлов
    public static final int DIRECTORIES_ONLY = JFileChooser.DIRECTORIES_ONLY;
    public static final int FILES_ONLY = JFileChooser.FILES_ONLY;
    public static final int FILES_AND_DIRECTORIES = JFileChooser.FILES_AND_DIRECTORIES;
}
