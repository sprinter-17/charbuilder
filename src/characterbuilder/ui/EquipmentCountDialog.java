package characterbuilder.ui;

import characterbuilder.character.equipment.Equipment;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class EquipmentCountDialog extends JFrame {

    private final Consumer<Integer> action;
    private final SpinnerNumberModel countModel;
    private final JPanel fieldPanel = new JPanel(new BorderLayout());
    private final JPanel buttonPanel = new JPanel();
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");

    public EquipmentCountDialog(String title, Equipment equipment, Consumer<Integer> action)
        throws HeadlessException {
        super(title);
        this.action = action;
        int count = equipment.getCount();
        countModel = new SpinnerNumberModel(count, 1, count, 1);
        JSpinner countSpinner = new JSpinner(countModel);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Select " + equipment.getBaseEquipment().toString() + " count");
        fieldPanel.add(label, BorderLayout.WEST);
        fieldPanel.add(countSpinner, BorderLayout.EAST);
        add(fieldPanel, BorderLayout.CENTER);

        buttonPanel.add(okButton);
        okButton.addActionListener(this::accept);
        buttonPanel.add(cancelButton);
        cancelButton.addActionListener(this::close);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    private void accept(ActionEvent ev) {
        action.accept(countModel.getNumber().intValue());
        close(ev);
    }

    private void close(ActionEvent ev) {
        setVisible(false);
    }

}
