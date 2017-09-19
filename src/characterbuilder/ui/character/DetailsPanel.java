package characterbuilder.ui.character;

import characterbuilder.character.Character;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.attribute.StringAttribute;
import characterbuilder.character.characterclass.CharacterClassLevel;
import java.awt.Color;
import java.awt.Dimension;
import static java.util.stream.Collectors.joining;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DetailsPanel extends CharacterSubPanel {

    private final JTextField nameField = new JTextField(10);
    private final JLabel raceField = new JLabel();
    private final JLabel classField = new JLabel();
    private final JLabel backgroundField = new JLabel();
    private final JLabel alignmentField = new JLabel();
    private final JLabel hpField = new JLabel();
    private final JLabel xpField = new JLabel();
    private final JLabel classAttributeName = new JLabel();
    private final JLabel classAttributeValue = new JLabel();

    public DetailsPanel(CharacterUpdater updater) {
        super("Personal Details", 2, updater);
        addInColumns("Name", nameField);
        addInColumns("Race", raceField);
        addInColumns("Classes", classField);
        addInColumns("Hit Points", hpField);
        addInColumns("Experience Points", xpField);
        addInColumns("Background", backgroundField);
        addInColumns("Alignment", alignmentField);
        addInColumn(0, 1, classAttributeName);
        addInColumn(1, 1, classAttributeValue);
        addNameFieldAttributes();
    }

    private void addNameFieldAttributes() {
        nameField.getDocument().addDocumentListener(
            updateTextField((ch, name) -> {
                if (ch.hasAttribute(NAME)) {
                    ch.getAttribute(NAME, StringAttribute.class).setValue(name);
                } else {
                    ch.addAttribute(new StringAttribute(NAME, name));
                }
                ch.setDirty();
                if (nameField.getText().isEmpty())
                    nameField.setBackground(Color.YELLOW);
                else
                    nameField.setBackground(Color.WHITE);
            }));
        nameField.setEnabled(false);
        nameField.setMinimumSize(new Dimension(100, 22));
    }

    @Override
    public void updateCharacter(Character character) {
        super.updateCharacter(character);
        nameField.setText(attributeValue(NAME));
        if (nameField.getText().isEmpty())
            nameField.setBackground(Color.YELLOW);
        else
            nameField.setBackground(Color.WHITE);
        nameField.setEnabled(true);
        raceField.setText(attributeValue(RACE));
        if (character.hasAttribute(CHARACTER_CLASS))
            classField.setText(character.getCharacterClassLevels()
                .map(CharacterClassLevel::toString).collect(joining("/")));
        else
            classField.setText(null);
        xpField.setText(attributeValue(EXPERIENCE_POINTS));
        backgroundField.setText(attributeValue(BACKGROUND));
        alignmentField.setText(attributeValue(ALIGNMENT));
        hpField.setText(attributeValue(HIT_POINTS));
        classAttributeName.setText(null);
        classAttributeValue.setText(null);
        if (character.hasAttribute(CHARACTER_CLASS)) {
            CharacterClassLevel charClassLevel = character.getAttribute(CHARACTER_CLASS);
            classAttributeName.setText(
                charClassLevel.getCharacterClass().getClassAttribute().toString());
            classAttributeValue.setText(
                character.getAttribute(charClassLevel.getCharacterClass().getClassAttribute())
                    .toString());
        }
        finishUpdate();
    }
}
