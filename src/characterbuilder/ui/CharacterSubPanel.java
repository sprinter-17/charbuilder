package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.StringAttribute;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class CharacterSubPanel extends JPanel {

    private final List<GridBagConstraints> constraints = new ArrayList<>();
    private final CharacterUpdater updater;
    private Character character;
    private boolean isUpdating = false;

    public CharacterSubPanel(String title, LayoutManager layout, CharacterUpdater updater) {
        super(layout);
        setBorder(BorderFactory.createTitledBorder(title));
        this.updater = updater;
    }

    protected CharacterSubPanel(String title, int columns, CharacterUpdater updater) {
        super(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder(title));
        for (int i = 0; i < columns; i++) {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = i;
            c.weightx = 1.0f;
            c.gridy = GridBagConstraints.RELATIVE;
            c.insets = new Insets(2, 5, 2, 5);
            constraints.add(c);
        }
        constraints.get(0).anchor = GridBagConstraints.WEST;
        constraints.get(columns - 1).anchor = GridBagConstraints.EAST;
        this.updater = updater;
    }

    protected final void addInColumns(String label, Component... fields) {
        add(new JLabel(label), constraints.get(0));
        IntStream.range(0, fields.length).forEach(i -> addInColumn(i + 1, 1, fields[i]));
    }

    protected final GridBagConstraints getConstraints(int column) {
        return constraints.get(column);
    }

    protected final void addInColumn(int column, int span, Component field) {
        constraints.get(column).gridwidth = span;
        add(field, constraints.get(column));
        constraints.get(column).gridwidth = 1;
    }

    protected void updateCharacter(Character character) {
        this.character = character;
        isUpdating = true;
    }

    protected void finishUpdate() {
        isUpdating = false;
    }

    protected Character getCharacter() {
        return character;
    }

    protected String attributeValue(AttributeType attribute) {
        if (character.hasAttribute(attribute))
            return character.getAttribute(attribute).toString();
        else
            return null;
    }

    protected void attributeValue(JComboBox combo, Object value) {
        combo.setEnabled(true);
        combo.setSelectedItem(value);
    }

    protected void update(JSpinner spinner, int value) {
        spinner.setEnabled(true);
        spinner.getModel().setValue(value);
    }

    protected void update(JLabel field, int value) {
        update(field, NumberFormat.getNumberInstance().format(value));
    }

    protected void update(JLabel field, String value) {
        field.setEnabled(true);
        field.setText(value);
    }

    protected void update(JTextComponent field, String value) {
        field.setEnabled(true);
        if (!field.getText().equals(value))
            field.setText(value);
    }

    protected final DocumentListener updateTextAttribute(AttributeType attribute) {
        return updateTextField((ch, txt) -> {
            if (txt == null || txt.isEmpty())
                ch.removeAttributesOfType(attribute);
            else if (ch.hasAttribute(attribute))
                ch.getAttribute(attribute, StringAttribute.class).setValue(txt);
            else
                ch.addAttribute(new StringAttribute(attribute, txt));
        });
    }

    protected final DocumentListener updateTextField(BiConsumer<Character, String> updater) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!isUpdating)
                    update(e.getDocument());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (!isUpdating)
                    update(e.getDocument());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (!isUpdating)
                    update(e.getDocument());
            }

            private void update(Document document) {
                try {
                    String text = document.getText(0, document.getLength());
                    updater.accept(character, text);
                    triggerChange();
                } catch (BadLocationException ex) {
                    Logger.getLogger(CharacterSubPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
    }

    protected void triggerUpdate(Character character) {
        updater.update(character);
    }

    protected void triggerChange() {
        updater.triggerChange();
    }
}
