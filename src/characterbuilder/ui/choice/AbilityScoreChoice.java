package characterbuilder.ui.choice;

import characterbuilder.character.Character;
import characterbuilder.character.CharacterRandom;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.characterclass.CharacterClass;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AbilityScoreChoice {

    private static final int PANEL_HEIGHT = 20;

    private final SelectionAction action;
    private final List<AttributeType> scores = new ArrayList<>(AbilityScore.SCORES);
    private final List<Integer> values = new ArrayList<>();
    private final List<ScorePanel> scorePanels = new ArrayList<>();
    private CharacterClass characterClass;
    private Race race;

    private class ScorePanel extends JPanel {

        private final int index;

        public ScorePanel(int index) {
            super(new BorderLayout());
            this.index = index;
            if (index > 0) {
                addSwapAction();
            }
            setPreferredSize(new Dimension(140, PANEL_HEIGHT));
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createEtchedBorder());
        }

        private void addSwapAction() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    scores.add(index - 1, scores.remove(index));
                    setLabels();
                    scorePanels.get(index - 1).setLabels();
                    getParent().repaint();
                }
            });
        }

        public void setLabels() {
            removeAll();
            AttributeType score = scores.get(index);
            addScoreLabel(score);
            addAdjustmentLabel(score);
            revalidate();
        }

        private void addScoreLabel(AttributeType score) {
            JLabel scoreLabel = new JLabel(score.toString());
            add(scoreLabel, BorderLayout.WEST);
        }

        private void addAdjustmentLabel(AttributeType score) {
            int adjustment = race.getAttributeAdjustment(score);
            JLabel adjustmentLabel = new JLabel(String.format("%+d", adjustment));
            adjustmentLabel.setPreferredSize(new Dimension(30, 30));
            add(adjustmentLabel, BorderLayout.EAST);
        }

        public AbilityScore getAbilityScore() {
            return new AbilityScore(scores.get(index), values.get(index));
        }
    }

    public AbilityScoreChoice(SelectionAction action) {
        this.action = action;
        generateValues();
        generateScorePanels();
    }

    private void generateValues() {
        final CharacterRandom random = new CharacterRandom();
        do {
            values.clear();
            IntStream.generate(random::nextAbilityScore).limit(6).forEach(values::add);
        } while (!valuesViable());
        values.sort(Comparator.reverseOrder());
    }

    private boolean valuesViable() {
        return averageViable() && minimumViable() && maximumViable();
    }

    private boolean averageViable() {
        return valueStream().average().getAsDouble() > 9.5;
    }

    private boolean minimumViable() {
        return valueStream().filter(v -> v < 10).count() < 3L;
    }

    private boolean maximumViable() {
        return valueStream().max().getAsInt() > 14;
    }

    private IntStream valueStream() {
        return values.stream().mapToInt(i -> i);
    }

    private void generateScorePanels() {
        IntStream.range(0, 6)
            .mapToObj(ScorePanel::new)
            .forEach(scorePanels::add);
    }

    public void showInPanel(DetailPanel detailPanel, Character character,
        Consumer<Stream<AbilityScore>> consumer) {
        this.race = character.getAttribute(AttributeType.RACE);
        this.characterClass = character.getAttribute(AttributeType.CHARACTER_CLASS);
        generateOrderedScores();
        detailPanel.removeAll();
        scorePanels.forEach(panel -> addScorePanel(panel, detailPanel));
        detailPanel.fill();
        detailPanel.revalidate();
        action.setSelectAction("Accept Scores", () -> {
            consumer.accept(scorePanels.stream().map(ScorePanel::getAbilityScore));
            action.clear();
        });
    }

    private void generateOrderedScores() {
        List<AttributeType> primaryScores = characterClass.getPrimaryAttributes().collect(toList());
        Collections.shuffle(scores);
        scores.sort(Comparator.comparing(as -> primaryScores.contains(as)
            ? primaryScores.indexOf(as) : primaryScores.size()));
    }

    private void addScorePanel(ScorePanel scorePanel, DetailPanel detailPanel) {
        scorePanel.setLabels();
        detailPanel.add(scorePanel, detailPanel.columnPosition(0));
        detailPanel.add(valueLabel(values.get(scorePanel.index)), detailPanel.columnPosition(1));
    }

    private JLabel valueLabel(int value) {
        JLabel valLabel = new JLabel(String.valueOf(value));
        valLabel.setPreferredSize(new Dimension(50, PANEL_HEIGHT));
        valLabel.setBorder(BorderFactory.createEtchedBorder());
        return valLabel;
    }
}
