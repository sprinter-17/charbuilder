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
    private final SelectionAction action = new SelectionAction();
    private final JSplitPane splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    private Optional<Character> character = Optional.empty();

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
            choiceModel.update();
            splitter.repaint();
        }
    }

    private void addSelectButton() {
        action.addButton(this);
    }

    public void update(Character character) {
        this.character = Optional.of(character);
        choiceModel.setCharacter(character);
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
            .map(opt -> new OptionPanel(opt.getOptionName(), opt.getDescription(character.get()),
            () -> selectOption(() -> followUp.accept(opt)), action))
            .forEach(panel -> detailPanel.add(panel, detailPanel.columnPosition(0)));
        detailPanel.fill();
        detailPanel.revalidate();
        splitter.repaint();
        action.clear();
    }

    @Override
    public void generateAbilityScores(Consumer<Stream<AbilityScore>> consumer) {
        AbilityScoreChoice abilityScoreChoice = new AbilityScoreChoice(character.get(), consumer, action);
        abilityScoreChoice.showInPanel(detailPanel);
    }

    @Override
    public void choiceMade() {
        choiceModel.update();
        listener.run();
        action.clear();
    }

    private void selectOption(Runnable optionAction) {
        OptionChoice current = choiceModel.getElementAt(choiceList.getSelectedIndex());
        optionAction.run();
        detailPanel.removeAll();
        detailPanel.repaint();
        choiceModel.update();
        action.clear();
        selectChoice(choiceModel.indexOf(current).orElse(0));
        listener.run();
    }

}
