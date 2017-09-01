package characterbuilder.ui.character;

import characterbuilder.ui.character.CharacterUpdater;
import characterbuilder.ui.character.CharacterSubPanel;
import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PersonalHistoryPanel extends CharacterSubPanel {

    private final JTextArea historyField = new JTextArea(4, 10);

    public PersonalHistoryPanel(CharacterUpdater updater) {
        super("Personal History", 1, updater);
        getConstraints(0).fill = GridBagConstraints.BOTH;
        getConstraints(0).weightx = 1.0;
        getConstraints(0).weighty = 1.0;
        addInColumn(0, 1, new JScrollPane(historyField));
        historyField.setLineWrap(true);
        historyField.setWrapStyleWord(true);
        historyField.setEnabled(false);
        historyField.getDocument()
            .addDocumentListener(updateTextAttribute(AttributeType.PERSONAL_HISTORY));
    }

    @Override
    protected void updateCharacter(Character character) {
        super.updateCharacter(character);
        historyField.setText(character.getStringAttribute(AttributeType.PERSONAL_HISTORY));
        historyField.setEnabled(true);
        finishUpdate();
    }

}
