package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.CharacterRandom;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.Option;
import characterbuilder.character.choice.OptionChoice;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

public class ChoicePanel extends JPanel implements ChoiceSelector {

    private final Runnable listener;
    private final ChoiceModel choiceModel = new ChoiceModel();
    private final JList<OptionChoice> choiceList = new JList(choiceModel);
    private final JPanel detailPanel = new JPanel(new GridBagLayout());
    private final JButton selectButton = new JButton("Select");
    private final JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private Optional<Character> character = Optional.empty();

    public ChoicePanel(Runnable listener) {
        super(new BorderLayout());
        splitter.setLeftComponent(new JScrollPane(choiceList));
        splitter.setRightComponent(new JScrollPane(detailPanel));
        this.listener = listener;
        add(splitter, BorderLayout.CENTER);
        add(selectButton, BorderLayout.SOUTH);
        splitter.setPreferredSize(new Dimension(200, 500));
        splitter.setDividerLocation(180);
        choiceList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListCellRenderer renderer = choiceList.getCellRenderer();
        choiceList.setCellRenderer((list, val, i, sel, foc) -> renderer.
            getListCellRendererComponent(list, val.toString(), i, sel, foc));
        selectButton.setEnabled(false);
    }

    public void update(Character character) {
        this.character = Optional.of(character);
        choiceModel.setCharacter(character);
        selectChoice(0);
        choiceList.addListSelectionListener(ev -> {
            showOptions();
        });
    }

    private void selectChoice(int index) {
        if (choiceModel.getSize() > index) {
            choiceList.setSelectionInterval(index, index);
            showOptions();
        }
    }

    @Override
    public <T extends Option> void chooseOption(Stream<T> options, Consumer<T> followUp) {
        detailPanel.removeAll();
        options.map(opt -> new OptionPanel(opt.toString(), opt.getDescription(character.get()),
            () -> selectOption(() -> followUp.accept(opt)), selectButton))
            .forEach(panel -> detailPanel.add(panel, columnPosition(0)));
        padDetailPanel();
        detailPanel.revalidate();
        splitter.repaint();
    }

    private void padDetailPanel() {
        GridBagConstraints c = columnPosition(0);
        c.weighty = 1.0;
        detailPanel.add(new JPanel(), c);
    }

    private void showOptions() {
        if (choiceList.getModel().getSize() > 0 && choiceList.getSelectedIndex() > -1) {
            choiceModel.select(choiceList.getSelectedIndex());
            choiceModel.update();
            splitter.repaint();
        }
    }

    @Override
    public void generateAbilityScores(Consumer<Stream<AbilityScore>> consumer) {
        detailPanel.removeAll();
        List<AttributeType> scores = generateOrderedScores();
        List<Integer> values = generateValues();
        List<JPanel> scorePanels = new ArrayList<>();
        IntStream.range(0, 6).forEach(i -> {
            scorePanel(i, scorePanels, scores);
            valueLabel(values.get(i));
        });
        setScoreLabels(scorePanels, scores);
        selectButton.setEnabled(true);
        selectButton.setAction(new AbstractAction("Accept Scores") {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectOption(() -> consumer.accept(IntStream.range(0, 6)
                    .mapToObj(i -> new AbilityScore(scores.get(i), values.get(i)))));
            }
        });
        padDetailPanel();
        detailPanel.revalidate();
        splitter.repaint();
    }

    private List<AttributeType> generateOrderedScores() {
        List<AttributeType> scores = new ArrayList<>(AbilityScore.SCORES);
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
            splitter.repaint();
        });
    }

    @Override
    public void choiceMade() {
        choiceModel.update();
        listener.run();
    }

    private void selectOption(Runnable action) {
        OptionChoice current = choiceModel.getElementAt(choiceList.getSelectedIndex());
        action.run();
        detailPanel.removeAll();
        detailPanel.repaint();
        choiceModel.update();
        selectButton.setEnabled(false);
        selectButton.setText("Select");
        selectChoice(choiceModel.indexOf(current).orElse(0));
        listener.run();
    }

    private GridBagConstraints columnPosition(int column) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = column;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1.0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.ipady = 10;
        return constraints;
    }
}
