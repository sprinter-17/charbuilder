package characterbuilder.ui.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.ui.CharacterSubPanel;
import characterbuilder.ui.CharacterUpdater;
import java.util.EnumMap;
import javax.swing.JLabel;

public class AbilityScorePanel extends CharacterSubPanel {

    private final EnumMap<AttributeType, JLabel> abilityScores = new EnumMap<>(AttributeType.class);

    public AbilityScorePanel(CharacterUpdater updater) {
        super("Ability Scores", 2, updater);
        for (AttributeType abilityScore : AbilityScore.SCORES) {
            abilityScores.put(abilityScore, new JLabel());
            addInColumns(abilityScore.toString(), abilityScores.get(abilityScore));
        }
    }

    @Override
    public void updateCharacter(Character character) {
        super.updateCharacter(character);
        for (AttributeType abilityScore : AbilityScore.SCORES) {
            abilityScores.get(abilityScore).setText(attributeValue(abilityScore));
        }
    }
}
