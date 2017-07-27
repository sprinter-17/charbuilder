package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.CharacterRandom;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.CharacterClass;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.choice.Choice;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.equipment.Equipment;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ChoicePanel extends JPanel implements ChoiceSelector {

    private final Runnable listener;
    private final ChoiceModel choiceModel = new ChoiceModel();
    private final JList<Choice> choiceList = new JList(choiceModel);
    private final JPanel detailPanel = new JPanel(new GridBagLayout());
    private final JButton selectButton = new JButton("Select");
    private Optional<Runnable> selectAction = Optional.empty();
    private Optional<Character> character = Optional.empty();

    public ChoicePanel(Runnable listener) {
        super(new BorderLayout());
        this.listener = listener;
        add(choiceList, BorderLayout.NORTH);
        add(new JScrollPane(detailPanel), BorderLayout.CENTER);
        add(selectButton, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(200, 500));
        selectButton.addActionListener(ev -> selectOption());
    }

    public void update(Character character) {
        this.character = Optional.of(character);
        choiceModel.setChoices(character.getChoices());
        selectFirstChoice();
        choiceList.addListSelectionListener(ev -> {
            showOptions(character);
        });
    }

    private void selectFirstChoice() {
        if (choiceModel.getSize() > 0) {
            choiceList.setSelectionInterval(0, 0);
            showOptions(character.get());
        }
    }

    @Override
    public void getAttribute(Stream<Attribute> attributes, Consumer<Attribute> consumer) {
        showOptions(attributes, consumer);
    }

    @Override
    public void getEquipment(Stream<Equipment> equipment, Consumer<Equipment> consumer) {
        showOptions(equipment, consumer);
    }

    @Override
    public void generateAbilityScores(Consumer<Stream<Attribute>> consumer) {
        detailPanel.removeAll();
        List<AttributeType> scores = generateOrderedScores();
        List<Integer> values = generateValues();
        List<JPanel> scorePanels = new ArrayList<>();
        IntStream.range(0, 6).forEach(i -> {
            scorePanel(i, scorePanels, scores);
            valueLabel(values.get(i));
        });
        setScoreLabels(scorePanels, scores);
        selectAction = Optional.of(() -> {
            consumer.accept(IntStream.range(0, 6)
                .mapToObj(i -> new IntAttribute(scores.get(i), values.get(i))));
        });
        detailPanel.revalidate();
        detailPanel.repaint();
    }

    private List<AttributeType> generateOrderedScores() {
        List<AttributeType> scores = new ArrayList<>(AttributeType.ABILITY_SCORES);
        CharacterClass characterClass = character.get().getAttribute(AttributeType.CHARACTER_CLASS);
        List<AttributeType> primaryScores = characterClass.getPrimaryAttributes().collect(toList());
        Collections.shuffle(scores);
        scores.sort(Comparator.comparing(as -> primaryScores.contains(as)
            ? primaryScores.indexOf(as) : 7));
        return scores;
    }

    private List<Integer> generateValues() {
        List<Integer> values = new ArrayList<>();
        CharacterRandom random = new CharacterRandom();
        IntStream.generate(random::nextAbilityScore).limit(6).forEach(values::add);
        values.sort(Comparator.reverseOrder());
        return values;
    }

    private JPanel scorePanel(int i, List<JPanel> scorePanels, List<AttributeType> scores) {
        JPanel scorePanel = new JPanel(new BorderLayout());
        scorePanel.setPreferredSize(new Dimension(140, 40));
        scorePanel.setBackground(Color.WHITE);
        scorePanel.setBorder(BorderFactory.createEtchedBorder());
        detailPanel.add(scorePanel, columnPosition(0));
        scorePanels.add(scorePanel);
        scorePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (i > 0) {
                    scores.add(i - 1, scores.remove(i));
                    setScoreLabels(scorePanels, scores);
                }
            }
        });
        return scorePanel;
    }

    private JLabel valueLabel(int value) {
        JLabel valLabel = new JLabel(String.valueOf(value));
        valLabel.setPreferredSize(new Dimension(50, 40));
        valLabel.setBorder(BorderFactory.createEtchedBorder());
        detailPanel.add(valLabel, columnPosition(1));
        return valLabel;
    }

    private void setScoreLabels(List<JPanel> panels, List<AttributeType> scores) {
        Race race = character.get().getAttribute(AttributeType.RACE);
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
            panel.repaint();
        });
    }

    @Override
    public void choiceMade() {
        choiceModel.update();
        listener.run();
    }

    private <T> void showOptions(Stream<T> options, Consumer<T> consumer) {
        detailPanel.removeAll();
        options.forEach(opt -> addOption(opt, () -> consumer.accept(opt)));
        detailPanel.repaint();
    }

    private <T> void addOption(T opt, Runnable action) {
        JLabel label = new JLabel("<html>" + opt.toString() + "</html>");
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(190, 40));
        panel.setBackground(Color.WHITE);
        panel.add(label, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEtchedBorder());
        panel.addMouseListener(optionMouseListener(opt, action, panel));
        detailPanel.add(panel, columnPosition(0));
    }

    private <T> MouseListener optionMouseListener(T opt, Runnable action, JPanel panel) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (opt instanceof Attribute) {
                    Attribute attribute = (Attribute) opt;
                    attribute.getDescription(character.get()).ifPresent(desc -> {
                        panel.setPreferredSize(new Dimension(190, 80));
                        panel.add(new JLabel(desc), BorderLayout.SOUTH);
                        panel.revalidate();
                        panel.repaint();
                    });
                }
                selectAction = Optional.of(action);
                if (e.getClickCount() == 2) {
                    selectOption();
                } else {
                    for (Component child : detailPanel.getComponents()) {
                        child.setBackground(child == panel ? Color.LIGHT_GRAY : Color.WHITE);
                    }
                }
            }
        };
    }

    private void showOptions(Character character) {
        if (choiceList.getModel().getSize() > 0 && choiceList.getSelectedIndex() > -1) {
            choiceList.getSelectedValue().makeChoice(character, this);
            choiceModel.update();
        }
    }

    private void selectOption() {
        if (selectAction.isPresent()) {
            selectAction.get().run();
            selectAction = Optional.empty();
            detailPanel.removeAll();
            detailPanel.repaint();
            choiceModel.update();
            listener.run();
            selectFirstChoice();
        }
    }

    private GridBagConstraints columnPosition(int column) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = column;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        return constraints;
    }
}
