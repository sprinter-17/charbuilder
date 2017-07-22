package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.CharacterRandom;
import characterbuilder.character.CharacterSaver;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.Choice;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.InitialChoiceGenerator;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.sheet.CharacterSheet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.xml.parsers.ParserConfigurationException;

public class MainWindow {

    private final CharacterSaver saver = new CharacterSaver();
    private final JFrame frame = new JFrame("Character Builder");
    private final LoadDialog loadDialog;
    private final JToolBar tools = new JToolBar();
    private final ChoiceModel choiceModel = new ChoiceModel();
    private final JList<Choice> choiceList = new JList(choiceModel);
    private final CharacterPanel panel;
    private final ChoiceSelector selector;
    private Optional<Character> character = Optional.empty();

    private final List<Runnable> toolEnablers = new ArrayList<>();

    public MainWindow() throws ParserConfigurationException {
        this.panel = new CharacterPanel();
        this.loadDialog = new LoadDialog(frame, this::setCharacter);
        this.panel.addChangeListener(this::enableTools);
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        addTools();
        frame.add(panel, BorderLayout.CENTER);
        JScrollPane choiceScroll = new JScrollPane(choiceList);
        selector = new ChoiceSelector() {
            @Override
            public void getName(Consumer<String> consumer) {
                choiceMade();
            }

            @Override
            public void getAttribute(Stream<Attribute> attributes, Consumer<Attribute> consumer) {
                popupMenu(attributes, consumer);
            }

            @Override
            public void getEquipment(Stream<Equipment> equipment, Consumer<Equipment> consumer) {
                popupMenu(equipment, consumer);
            }

            private <T> void popupMenu(Stream<T> values, Consumer<T> consumer) {
                JPopupMenu menu = new JPopupMenu();
                values.map(attr -> new AbstractAction(attr.toString()) {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        consumer.accept(attr);
                        choiceMade();
                    }
                }).forEach(menu::add);
                int x = MouseInfo.getPointerInfo().getLocation().x - frame.getX();
                int y = MouseInfo.getPointerInfo().getLocation().y - frame.getY();
                menu.show(frame, x, y);
            }

            @Override
            public void choiceMade() {
                panel.updateCharacterData(character.get());
                choiceModel.update();
                enableTools();
            }
        };
        choiceList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makeChoice();
            }

        });
        choiceList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    makeChoice();
            }
        });
        choiceScroll.setPreferredSize(new Dimension(200, 600));
        frame.add(choiceScroll, BorderLayout.WEST);
        frame.pack();
    }

    private void makeChoice() {
        if (choiceList.getModel().getSize() > 0 && choiceList.getSelectedIndex() > -1) {
            Choice choice = choiceList.getSelectedValue();
            choice.makeChoice(character.get(), selector);
            choiceModel.update();
        }
    }

    private void addTools() {
        addTool("New Character", this::newCharacter);
        addTool("Load Character", this::loadCharacter);
        addTool("Save Character", this::saveCharacter,
            () -> character.isPresent(),
            () -> character.get().isDirty(),
            () -> character.get().hasAttribute(AttributeType.NAME),
            () -> character.get().getChoices().isEmpty());
        addTool("Level Up", this::levelUpCharacter,
            () -> character.isPresent(), () -> character.get().getChoices().isEmpty(),
            () -> character.get().getLevel() < 20);
        addTool("Show Character Sheet", this::showCharacterSheet,
            () -> character.isPresent(), () -> character.get().getChoices().isEmpty());
        addTool("Exit", this::exit);
        enableTools();
        frame.add(tools, BorderLayout.NORTH);
    }

    private void addTool(String name, Runnable runnable, Supplier<Boolean>... conditions) {
        Action action = new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                runnable.run();
                enableTools();
            }
        };
        toolEnablers.add(() -> {
            action.setEnabled(Arrays.stream(conditions).allMatch(Supplier::get));
        });
        tools.add(action);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void enableTools() {
        toolEnablers.forEach(Runnable::run);
    }

    private void newCharacter() {
        if (canDiscardCharacter()) {
            setCharacter(new Character());
            new InitialChoiceGenerator().generateChoices(character.get());
        }
    }

    private void setCharacter(Character character) {
        this.character = Optional.of(character);
        choiceModel.setChoices(character.getChoices());
        panel.updateCharacterData(character);
        choiceModel.update();
    }

    private void loadCharacter() {
        if (canDiscardCharacter()) {
            loadDialog.setVisible(true);
        }
    }

    private void exit() {
        if (canDiscardCharacter())
            System.exit(0);
    }

    private boolean canDiscardCharacter() {
        if (!character.isPresent() || !character.get().isDirty())
            return true;
        if (character.get().hasAttribute(AttributeType.NAME)) {
            switch (JOptionPane.showConfirmDialog(panel,
                "Do you want to save the character?",
                "Confirm Save", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                case JOptionPane.CANCEL_OPTION:
                    return false;
                case JOptionPane.YES_OPTION:
                    saveCharacter();
                case JOptionPane.NO_OPTION:
                    return true;
                default:
                    throw new AssertionError("Illegal return from confirmation dialog");
            }
        } else {
            switch (JOptionPane.showConfirmDialog(panel,
                "The character has not been saved.\nDo you want to discard it?",
                "Confirm Save", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE)) {
                case JOptionPane.CANCEL_OPTION:
                    return false;
                case JOptionPane.OK_OPTION:
                    return true;
                default:
                    throw new AssertionError("Illegal return from confirmation dialog");
            }
        }
    }

    private void levelUpCharacter() {
        assert character.isPresent();
        character.get().increaseLevel(new CharacterRandom());
        panel.updateCharacterData(character.get());
        choiceModel.update();
    }

    public void saveCharacter() {
        try {
            if (Files.notExists(Paths.get("characters")))
                Files.createDirectory(Paths.get("characters"));
            String fileName = character.get().getAttribute(AttributeType.NAME).toString().toLowerCase()
                .replaceAll("\\s+", "_").replaceAll("\\W", "") + ".xml";
            Path filePath = Paths.get("characters", fileName);
            if (Files.exists(filePath))
                if (JOptionPane.showConfirmDialog(frame,
                    "Overwrite previous file for character?",
                    "Overwrite Character?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                    Files.delete(filePath);
                else
                    return;
            saver.save(character.get(), Files.newOutputStream(filePath, StandardOpenOption.CREATE_NEW));
            character.get().clearDirty();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.toString(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showCharacterSheet() {
        new CharacterSheet(character.get()).setVisible(true);
    }
}
