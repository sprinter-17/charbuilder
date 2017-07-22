package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Race;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.EnumMap;
import java.util.Optional;
import java.util.function.Consumer;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ArrangeAbilityScoresDialog extends JFrame {

    private final JLabel instruction = new JLabel();
    private final JPanel scorePanel = new JPanel(new GridBagLayout());
    private final EnumMap<AttributeType, JToggleButton> valueFields = new EnumMap<>(AttributeType.class);
    private final EnumMap<AttributeType, JLabel> modifierFields = new EnumMap<>(AttributeType.class);

    private Optional<AttributeType> scoreToSwap = Optional.empty();

    public ArrangeAbilityScoresDialog() throws HeadlessException {
        super("Arrange Ability Scores");
        setLayout(new BorderLayout());
        JPanel instructionPanel = new JPanel();
        instructionPanel.add(instruction);
        instructionPanel.setBorder(BorderFactory.createEtchedBorder());
        add(instructionPanel, BorderLayout.NORTH);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0;
        c.insets = new Insets(3, 10, 3, 10);
        for (AttributeType score : AttributeType.ABILITY_SCORES) {
            c.gridwidth = 1;
            c.gridx = 0;
            scorePanel.add(new JLabel(score.toString()), c);
            c.gridx = 1;
            valueFields.put(score, new JToggleButton());
            scorePanel.add(valueFields.get(score), c);
            c.gridx = 2;
            modifierFields.put(score, new JLabel());
            scorePanel.add(modifierFields.get(score), c);
            c.gridy++;
        }
        scorePanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        add(scorePanel, BorderLayout.CENTER);
        add(new JButton(new AbstractAction("Close") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        }), BorderLayout.SOUTH);
        pack();
    }

    public void showScoresForCharacter(Character character, Consumer<Character> updater) {
        Race race = character.getAttribute(AttributeType.RACE);
        for (AttributeType score : AttributeType.ABILITY_SCORES) {
            int racialModifier = race.getAttributeAdjustment(score);
            int rawValue = character.getIntAttribute(score) - racialModifier;
            valueFields.get(score).setSelected(false);
            valueFields.get(score).setAction(new AbstractAction(String.format("%d", rawValue)) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (scoreToSwap.isPresent()) {
                        character.swapAttributes(scoreToSwap.get(), score);
                        updater.accept(character);
                        showScoresForCharacter(character, updater);
                    } else {
                        instruction.setText("Select second score to swap");
                        scoreToSwap = Optional.of(score);
                    }
                }
            });
            modifierFields.get(score).setText(String.format("%+d", race.getAttributeAdjustment(score)));
        }
        instruction.setText("Select first score to swap");
        scoreToSwap = Optional.empty();
        setVisible(true);
    }

}
