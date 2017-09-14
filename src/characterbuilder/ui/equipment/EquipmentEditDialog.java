package characterbuilder.ui.equipment;

import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.MagicItem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class EquipmentEditDialog extends JFrame {

    private final Equipment equipment;
    private final JTextField nameField = new JTextField();
    private final SpinnerNumberModel countModel;
    private final SpinnerNumberModel bonusModel;
    private final JTextArea abilityField = new JTextArea(4, 20);
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");
    private final Consumer<EquipmentSet> acceptAction;

    public EquipmentEditDialog(Equipment equipment, Consumer<EquipmentSet> acceptAction)
        throws HeadlessException {
        super("Edit " + equipment.toString());
        this.equipment = equipment.getBaseEquipment();
        this.acceptAction = acceptAction;
        this.countModel = new SpinnerNumberModel(equipment.getCount(), 1, 100000, 1);
        this.bonusModel = new SpinnerNumberModel(equipment.getBonus(), -5, +5, 1);
        equipment.asMagicItem().ifPresent(mi -> {
            mi.getName().ifPresent(nameField::setText);
            mi.getAbilityText().ifPresent(abilityField::setText);
        });
        setPreferredSize(new Dimension(400, 300));
        setLayout(new BorderLayout());
        addFields();
        addButtons();
        pack();
    }

    private class FieldPanel extends JPanel {

        private final GridBagConstraints labels = new GridBagConstraints();
        private final GridBagConstraints fields = new GridBagConstraints();

        public FieldPanel() {
            super(new GridBagLayout());
            labels.gridx = 0;
            labels.gridy = GridBagConstraints.RELATIVE;
            labels.anchor = GridBagConstraints.EAST;
            fields.gridx = 1;
            fields.gridy = GridBagConstraints.RELATIVE;
            fields.anchor = GridBagConstraints.WEST;
            fields.fill = GridBagConstraints.HORIZONTAL;
        }

        public void addField(String label, JComponent field) {
            add(new JLabel(label), labels);
            add(field, fields);
        }
    }

    private void addFields() {
        FieldPanel panel = new FieldPanel();
        panel.addField("Name", nameField);
        panel.addField("Count", new JSpinner(countModel));
        panel.addField("Bonus", new JSpinner(bonusModel));
        panel.addField("Ability", abilityField);
        abilityField.setLineWrap(true);
        abilityField.setWrapStyleWord(true);
        add(panel, BorderLayout.CENTER);
    }

    private void addButtons() {
        JPanel panel = new JPanel();
        panel.add(okButton);
        okButton.addActionListener(this::accept);
        panel.add(cancelButton);
        cancelButton.addActionListener(this::close);
        add(panel, BorderLayout.SOUTH);
        getRootPane().setDefaultButton(okButton);
    }

    private void accept(ActionEvent ev) {
        int bonus = bonusModel.getNumber().intValue();
        int count = countModel.getNumber().intValue();
        String ability = abilityField.getText();
        String name = nameField.getText();
        if (bonus > 0 || !ability.isEmpty() || !name.isEmpty()) {
            MagicItem item = new MagicItem(equipment, bonus);
            if (!name.isEmpty())
                item.setName(name);
            if (!ability.isEmpty())
                item.setAbility(ability);
            acceptAction.accept(item.makeSet(count));
        } else {
            acceptAction.accept(equipment.makeSet(count));
        }
        close(ev);
    }

    private void close(ActionEvent ev) {
        setVisible(false);
    }

}
