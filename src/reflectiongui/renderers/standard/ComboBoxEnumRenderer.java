package reflectiongui.renderers.standard;

import reflectiongui.controllers.VariableController;
import reflectiongui.renderers.VariableRenderer;

import javax.swing.*;

/** Renderer для enum в виде выпадающего списка. */
public class ComboBoxEnumRenderer implements VariableRenderer {
    private JComponent root;
    private JLabel nameLabel;
    private JComboBox comboBox;

    public ComboBoxEnumRenderer() {
        root = new JPanel();
        nameLabel = new JLabel();
        comboBox = new JComboBox();
        root.setLayout(new BoxLayout(root, BoxLayout.LINE_AXIS));
        root.add(nameLabel);
        root.add(comboBox);
    }

    @Override
    public JComponent rootComponent() {
        return root;
    }

    @Override
    public void setValue(Object value) {
        comboBox.setSelectedItem(value);
    }

    @Override
    public Object getValue() {
        return comboBox.getSelectedItem();
    }

    @Override
    public void initialize(VariableController controller) {
        nameLabel.setText(controller.getTitle());
        Class<? extends Enum> type = controller.getType().asSubclass(Enum.class);
        Enum[] enumConstants = type.getEnumConstants();
        comboBox.setModel(new DefaultComboBoxModel(enumConstants));
    }
}
