package characterbuilder.ui.choice;

import characterbuilder.character.Character;
import characterbuilder.character.CharacterRandom;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.characterclass.CharacterClass;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
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

    private final Consumer<Stream<AbilityScore>> consumer;
    private final SelectionAction action;
    private final List<AttributeType> scores = new ArrayList<>(AbilityScore.SCORES);
    private final List<Integer> values = new ArrayList<>();
    private final Race race;

    public AbilityScoreChoice(Character character, Consumer<Stream<AbilityScore>> consumer,
        SelectionAction action) {
        this.consumer = consumer;
        this.action = action;
        this.race = character.getAttribute(AttributeType.RACE);
        generateOrderedScores(character);
        generateValues();
    }

    private void generateOrderedScores(Character character) {
        CharacterClass characterClass = character.getAttribute(AttributeType.CHARACTER_CLASS);
        List<AttributeType> primaryScores = characterClass.getPrimaryAttributes().collect(toList());
        Collections.shuffle(scores);
        scores.sort(Comparator.comparing(as -> primaryScores.contains(as)
            ? primaryScores.indexOf(as) : 7));
    }

    private void generateValues() {
        CharacterRandom random = new CharacterRandom();
        IntStream.generate(random::nextAbilityScore).limit(6).forEach(values::add);
        values.sort(Comparator.reverseOrder());
    }

    public void showInPanel(DetailPanel detailPanel) {
        List<JPanel> scorePanels = new ArrayList<>();

        detailPanel.removeAll();
        IntStream.range(0, 6).forEach(i -> {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setPreferredSize(new Dimension(140, 40));
            panel.setBackground(Color.WHITE);
            panel.setBorder(BorderFactory.createEtchedBorder());
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (i > 0) {
                        scores.add(i - 1, scores.remove(i));
                        setScoreLabels(detailPanel, scorePanels, scores);
                    }
                }
            });
            detailPanel.add(panel, detailPanel.columnPosition(0));
            scorePanels.add(panel);
            valueLabel(detailPanel, values.get(i));
        });
        setScoreLabels(detailPanel, scorePanels, scores);
        detailPanel.fill();
        detailPanel.revalidate();
        action.setSelectAction("Accept Scores", () -> {
            consumer.accept(IntStream.range(0, 6)
                .mapToObj(i -> new AbilityScore(scores.get(i), values.get(i))));
            action.clear();
        });
    }

    private JLabel valueLabel(DetailPanel detailPanel, int value) {
        JLabel valLabel = new JLabel(String.valueOf(value));
        valLabel.setPreferredSize(new Dimension(50, 40));
        valLabel.setBorder(BorderFactory.createEtchedBorder());
        detailPanel.add(valLabel, detailPanel.columnPosition(1));
        return valLabel;
    }

    private void setScoreLabels(DetailPanel detailPanel, List<JPanel> panels,
        List<AttributeType> scores) {
        IntStream.range(0, 6).forEach(i -> {
            JPanel panel = panels.get(i);
            panel.removeAll();
            AttributeType score = scores.get(i);
            panel.add(new JLabel(score.toString()), BorderLayout.WEST);
            int adjustment = race.getAttributeAdjustment(score);
            JLabel adjustmentLabel = new JLabel(String.format("%+d", adjustment));
            adjustmentLabel.setPreferredSize(new Dimension(30, 30));
            panel.add(adjustmentLabel, BorderLayout.EAST);
            panel.revalidate();
            detailPanel.repaint();
        });
    }
}
