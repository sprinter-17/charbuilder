package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import java.awt.event.ActionEvent;
import java.util.EnumMap;
import javax.swing.JButton;
import javax.swing.JLabel;

public class AbilityScorePanel extends CharacterSubPanel {

    private final JButton rearrangeAbilityScoreButton = new JButton("Rearrange Scores");
    private final EnumMap<AttributeType, JLabel> abilityScores = new EnumMap<>(AttributeType.class);
    private final ArrangeAbilityScoresDialog arrangeDialog = new ArrangeAbilityScoresDialog();

    public AbilityScorePanel(CharacterUpdater updater) {
        super("Ability Scores", 2, updater);
        addInColumn(0, 2, rearrangeAbilityScoreButton);
        rearrangeAbilityScoreButton.setEnabled(false);
        rearrangeAbilityScoreButton.addActionListener(this::rearrangeAbilityScores);
        for (AttributeType abilityScore : AttributeType.ABILITY_SCORES) {
            abilityScores.put(abilityScore, new JLabel());
            addInColumns(abilityScore.toString(), abilityScores.get(abilityScore));
        }
    }

    @Override
    public void updateCharacter(Character character) {
        super.updateCharacter(character);
        rearrangeAbilityScoreButton.setEnabled(character.hasChoice("Generate hit points"));
        for (AttributeType abilityScore : AttributeType.ABILITY_SCORES) {
            abilityScores.get(abilityScore).setText(attributeValue(abilityScore));
        }
    }

    private void rearrangeAbilityScores(ActionEvent ev) {
        arrangeDialog.setLocationRelativeTo(this);
        arrangeDialog.showScoresForCharacter(getCharacter(), this::updateCharacter);
        arrangeDialog.setVisible(true);
    }

}
