package characterbuilder.ui.character;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Height;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Weight;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AppearancePanel extends CharacterSubPanel {

    private final JLabel sex = new JLabel();
    private final JTextField age = new JTextField(10);
    private final JTextField height = new JTextField(10);
    private final JTextField weight = new JTextField(10);

    public AppearancePanel(CharacterUpdater updater) {
        super("Appearance", 2, updater);
        getConstraints(1).weightx = 1.0;
        addInColumns("Sex", sex);
        addInColumns("Age", age);
        age.setMinimumSize(new Dimension(120, 22));
        age.getDocument().addDocumentListener(updateTextField((ch, txt) -> {
            try {
                ch.removeAttributesOfType(AttributeType.AGE);
                if (!txt.isEmpty()) {
                    int value = Integer.valueOf(txt);
                    ch.addAttribute(new IntAttribute(AttributeType.AGE, value));
                }
                age.setBackground(Color.WHITE);
            } catch (NumberFormatException ex) {
                age.setBackground(Color.YELLOW);
            }
        }));
        addInColumns("Height", height);
        height.setMinimumSize(new Dimension(120, 22));
        height.getDocument().addDocumentListener(updateTextField((ch, txt) -> {
            try {
                Height value = Height.valueOf(txt);
                height.setBackground(Color.white);
                ch.removeAttributesOfType(AttributeType.HEIGHT);
                if (!value.equals(Height.ZERO))
                    ch.addAttribute(value);
            } catch (Height.HeightFormatException ex) {
                height.setBackground(Color.YELLOW);
            }
        }));
        addInColumns("Weight", weight);
        weight.setMinimumSize(new Dimension(120, 22));
        weight.getDocument().addDocumentListener(updateTextField((ch, txt) -> {
            try {
                Weight value = Weight.valueOf(txt);
                weight.setBackground(Color.white);
                ch.removeAttributesOfType(AttributeType.WEIGHT);
                if (!value.equals(Weight.ZERO))
                    ch.addAttribute(value);
            } catch (Weight.WeightFormatException ex) {
                weight.setBackground(Color.YELLOW);
            }
        }));
    }

    @Override
    public void updateCharacter(Character character) {
        super.updateCharacter(character);
        sex.setText(character.getAttribute(AttributeType.SEX).toString());
        age.setEnabled(character.hasAttribute(AttributeType.AGE));
        age.setText(character.getAttribute(AttributeType.AGE).toString());
        height.setEnabled(character.hasAttribute(AttributeType.HEIGHT));
        height.setText(character.getAttribute(AttributeType.HEIGHT).toString());
        weight.setEnabled(character.hasAttribute(AttributeType.WEIGHT));
        weight.setText(character.getAttribute(AttributeType.WEIGHT).toString());
        finishUpdate();
    }

}
