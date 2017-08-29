package characterbuilder.ui.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.Option;
import characterbuilder.character.choice.OptionChoice;
import java.awt.BorderLayout;
import java.awt.Dimension;
import static java.util.Comparator.comparing;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;
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
    private final DetailPanel detailPanel = new DetailPanel();
    private final SelectionAction action = new SelectionAction(this::choiceMade);
    private final JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

    private Optional<AbilityScoreChoice> scoreChoice = Optional.empty();
    private Optional<Character> character = Optional.empty();
    private Optional<OptionChoice> currentChoice = Optional.empty();

    public ChoicePanel(Runnable listener) {
        super(new BorderLayout());
        this.listener = listener;
        addSplitter();
        addChoiceList();
        addSelectButton();
    }

    private void addSplitter() {
        splitter.setLeftComponent(new JScrollPane(choiceList));
        splitter.setRightComponent(new JScrollPane(detailPanel));
        splitter.setPreferredSize(new Dimension(200, 500));
        splitter.setDividerLocation(180);
        add(splitter, BorderLayout.CENTER);
    }

    private void addChoiceList() {
        choiceList.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListCellRenderer renderer = choiceList.getCellRenderer();
        choiceList.setCellRenderer((list, val, i, sel, foc) -> renderer.
            getListCellRendererComponent(list, val.toString(), i, sel, foc));
        choiceList.addListSelectionListener(ev -> showOptions());
    }

    private void showOptions() {
        if (choiceList.getModel().getSize() > 0 && choiceList.getSelectedIndex() > -1) {
            choiceModel.select(choiceList.getSelectedIndex());
            currentChoice = Optional.of(choiceModel.getElementAt(choiceList.getSelectedIndex()));
            choiceModel.update();
            splitter.repaint();
        }
    }

    private void addSelectButton() {
        action.addButton(this);
    }

    public void update(Character character) {
        if (this.character.equals(Optional.of(character))) {
            choiceModel.update();
        } else {
            this.character = Optional.of(character);
            scoreChoice = Optional.empty();
            choiceModel.setCharacter(character);
        }
        selectChoice(0);
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
        options
            .sorted(comparing(Option::getOptionName))
            .map(opt -> new OptionPanel(opt, character.get(), () -> followUp.accept(opt), action,
            getWidth() - 70))
            .forEach(panel -> detailPanel.add(panel, detailPanel.columnPosition(0)));
        detailPanel.fill();
        detailPanel.revalidate();
        splitter.repaint();
        action.clear();
    }

    @Override
    public void generateAbilityScores(Consumer<Stream<AbilityScore>> consumer) {
        if (!scoreChoice.isPresent())
            scoreChoice = Optional.of(new AbilityScoreChoice(character.get(), consumer, action));
        scoreChoice.get().showInPanel(detailPanel);
    }

    @Override
    public void choiceMade() {
        detailPanel.removeAll();
        detailPanel.repaint();
        choiceModel.update();
        action.clear();
        selectChoice(currentChoice.map(choiceModel::indexOf).map(oi -> oi.orElse(0)).orElse(0));
        listener.run();
    }
}
