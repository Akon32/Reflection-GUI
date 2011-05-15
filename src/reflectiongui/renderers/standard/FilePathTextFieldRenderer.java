package reflectiongui.renderers.standard;

import reflectiongui.annotations.FileDialog;
import reflectiongui.annotations.Renders;
import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/** Renderer типа для пути файла ({@link File}) */
@Renders(File.class)
public class FilePathTextFieldRenderer implements VariableRenderer {
    private JTextField path;
    private JButton selectFileButton;
    private JFileChooser fileChooser;
    private JPanel rootPanel;
    private JLabel title;
    /** Тип диалога выбора файлов */
    private FileDialog.Type dialogType;
    private String dialogApproveButton;

    public FilePathTextFieldRenderer() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.LINE_AXIS));
        rootPanel.add(title = new JLabel());
        rootPanel.add(path = new JTextField());
        rootPanel.add(selectFileButton = new JButton("..."));
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showDialog() == JFileChooser.APPROVE_OPTION) {
                    setValue(fileChooser.getSelectedFile());
                }
            }
        });
        fileChooser = new JFileChooser();
    }

    @Override
    public JComponent rootComponent() {
        return rootPanel;
    }

    @Override
    public void setValue(Object value) {
        File file = (File) value;
        path.setText(file == null ? "" : file.toString());
    }

    @Override
    public Object getValue() {
        return new File(path.getText());
    }

    private int showDialog() {
        switch (dialogType) {
            case OPEN:
                return fileChooser.showOpenDialog(rootPanel);
            case SAVE:
                return fileChooser.showSaveDialog(rootPanel);
            default:
                return fileChooser.showDialog(rootPanel, null);
        }
    }

    @Override
    public void initialize(VariableController controller) {
        // заголовок
        title.setText(controller.getTitle());
        // создание диалога выбора файлов
        // TODO: добавить возможность брать FileChooser из группы.
        fileChooser = new JFileChooser();
        FileDialog annotation = controller.getAnnotation(FileDialog.class);
        if (annotation != null) {
            if (annotation.useCurrentDir()) {
                // устанавливать ли текущий каталог
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            }
        }
        // тип диалога выбора файла
        dialogType = annotation.type();
        fileChooser.setFileSelectionMode(annotation.selectionMode());
        // надписи в диалоге выбора файла
        fileChooser.setDialogTitle(annotation.title().isEmpty() ? null : annotation.title());
        fileChooser.setApproveButtonText(annotation.approveText().isEmpty() ? null : annotation.approveText());
    }
}
