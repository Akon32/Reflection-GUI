package reflectiongui.renderers.standard;

import reflectiongui.annotations.Renders;
import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Renderer для типа Boolean, представляющий выводимую переменную
 * в виде checkbox'а.
 */
@Renders(Boolean.class)
public class CheckBoxBooleanRenderer implements VariableRenderer {
    private JPanel panel;
    private JLabel label;
    private JCheckBox checkBox;

    public CheckBoxBooleanRenderer() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.add(checkBox = new JCheckBox());
        panel.add(label = new JLabel());
        label.setLabelFor(checkBox);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    checkBox.doClick();
                }
            }
        });
    }

    @Override
    public JComponent rootComponent() {
        return panel;
    }

    @Override
    public void setValue(Object value) {
        checkBox.setSelected((Boolean) value);
    }

    @Override
    public Object getValue() {
        return checkBox.isSelected();
    }

    @Override
    public void initialize(VariableController controller) {
        label.setText(controller.getTitle());
    }
}
