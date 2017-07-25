package characterbuilder.ui;

import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.equipment.CustomTreasure;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CustomTreasureDialog extends JDialog {

    private final Consumer<CustomTreasure> consumer;
    private final JTextField descriptionField = new JTextField(20);
    private final JTextField valueField = new JTextField(10);
    private final JTextField weightField = new JTextField(10);
    private final JButton okButton = new JButton("OK");
    private final JButton cancelButton = new JButton("Cancel");

    private String description;
    private Value value;
    private Weight weight;

    public CustomTreasureDialog(Frame owner, Consumer<CustomTreasure> consumer) {
        super(owner, "Custom Treasure", true);
        this.consumer = consumer;
        setLayout(new BorderLayout());
        addFields();
        addButtons();
        processData();
        pack();
    }

    private void addFields() {
        JPanel panel = new JPanel(new GridBagLayout());
        DocumentListener listener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                processData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                processData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                processData();
            }
        };
        GridBagConstraints labels = new GridBagConstraints();
        labels.gridx = 0;
        labels.gridy = GridBagConstraints.RELATIVE;
        labels.anchor = GridBagConstraints.EAST;
        GridBagConstraints fields = new GridBagConstraints();
        fields.gridx = 1;
        fields.anchor = GridBagConstraints.WEST;
        fields.gridy = GridBagConstraints.RELATIVE;
        panel.add(new JLabel("Description"), labels);
        panel.add(descriptionField, fields);
        descriptionField.getDocument().addDocumentListener(listener);
        panel.add(new JLabel("Value"), labels);
        panel.add(valueField, fields);
        valueField.getDocument().addDocumentListener(listener);
        panel.add(new JLabel("Weight"), labels);
        panel.add(weightField, fields);
        weightField.getDocument().addDocumentListener(listener);
        add(panel, BorderLayout.CENTER);
    }

    private void addButtons() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.add(okButton);
        okButton.addActionListener(ev -> {
            consumer.accept(new CustomTreasure(description, value, weight));
            setVisible(false);
        });
        panel.add(cancelButton);
        cancelButton.addActionListener(ev -> {
            setVisible(false);
        });
        add(panel, BorderLayout.SOUTH);
    }

    private void processData() {
        okButton.setEnabled(true);
        description = descriptionField.getText();
        if (description.isEmpty()) {
            descriptionField.setBackground(Color.YELLOW);
            okButton.setEnabled(false);
        } else {
            descriptionField.setBackground(Color.WHITE);
        }
        try {
            value = Value.valueOf(valueField.getText());
            valueField.setBackground(Color.WHITE);
        } catch (Value.ValueFormatException ex) {
            valueField.setBackground(Color.YELLOW);
            okButton.setEnabled(false);
        }
        try {
            weight = Weight.valueOf(weightField.getText());
            weightField.setBackground(Color.WHITE);
        } catch (Weight.WeightFormatException ex) {
            weightField.setBackground(Color.YELLOW);
            okButton.setEnabled(false);
        }
    }
}
