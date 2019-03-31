package characterbuilder.ui.character;

import characterbuilder.character.attribute.AttributeType;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DescriptionPanel extends CharacterSubPanel {

    private final JTextArea description = new JTextArea(4, 20);

    public DescriptionPanel(CharacterUpdater updater) {
        super("Physical Description", new BorderLayout(), updater);
        JScrollPane descScroller = new JScrollPane(description);
        add(descScroller, BorderLayout.CENTER);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEnabled(false);
        description.getDocument()
            .addDocumentListener(updateTextAttribute(AttributeType.PHYSICAL_DESCRIPTION));
    }

    @Override
    public void updateCharacter(characterbuilder.character.Character character) {
        super.updateCharacter(character);
        description.setText(character.getStringAttribute(AttributeType.PHYSICAL_DESCRIPTION));
        description.setEnabled(true);
        finishUpdate();
    }
}
