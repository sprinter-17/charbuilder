package characterbuilder.ui;

import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentSet;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class EquipmentEditDialog extends JFrame {

    private final Equipment equipment;
    private final SpinnerNumberModel countModel;
    private final SpinnerNumberModel bonusModel;
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");
    private final Consumer<EquipmentSet> acceptAction;

    public EquipmentEditDialog(Equipment equipment, Consumer<EquipmentSet> acceptAction)
        throws HeadlessException {
        super("Edit " + equipment.getBaseEquipment().toString());
        this.equipment = equipment.getBaseEquipment();
        this.acceptAction = acceptAction;
        this.countModel = new SpinnerNumberModel(equipment.getCount(), 1, 100000, 1);
        this.bonusModel = new SpinnerNumberModel(equipment.getBonus(), -5, +5, 1);
        setLayout(new BorderLayout());
        addFields();
        addButtons();
        pack();
    }

    private void addFields() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints labels = new GridBagConstraints();
        labels.gridx = 0;
        labels.gridy = GridBagConstraints.RELATIVE;
        labels.anchor = GridBagConstraints.EAST;
        GridBagConstraints fields = new GridBagConstraints();
        fields.gridx = 1;
        fields.gridy = GridBagConstraints.RELATIVE;
        fields.anchor = GridBagConstraints.WEST;
        fields.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JLabel("Count"), labels);
        panel.add(new JSpinner(countModel), fields);
        panel.add(new JLabel("Bonus"), labels);
        panel.add(new JSpinner(bonusModel), fields);
        panel.add(new JLabel("Ability"), labels);
        panel.add(new JTextField(10), fields);
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
        acceptAction.accept(new EquipmentSet(equipment, bonus, count));
        close(ev);
    }

    private void close(ActionEvent ev) {
        setVisible(false);
    }

}
