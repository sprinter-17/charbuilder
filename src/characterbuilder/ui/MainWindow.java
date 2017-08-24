package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.CharacterRandom;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.InitialChoiceGenerator;
import characterbuilder.character.saveload.CharacterSaver;
import characterbuilder.sheet.CharacterSheet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.xml.parsers.ParserConfigurationException;

public class MainWindow {

    private final CharacterSaver saver = new CharacterSaver();
    private final JFrame frame = new JFrame("Character Builder");
    private final LoadDialog loadDialog = new LoadDialog(frame, this::setCharacter);
    private final JToolBar tools = new JToolBar();
    private Optional<Character> character = Optional.empty();
    private Optional<String> name = Optional.empty();
    private final ChoicePanel choices = new ChoicePanel(this::updateFromChoices);
    private final CharacterPanel panel = new CharacterPanel();

    private final List<Runnable> toolEnablers = new ArrayList<>();

    public MainWindow() throws ParserConfigurationException {
        this.panel.addChangeListener(() -> toolEnablers.forEach(Runnable::run));
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        addTools();
        frame.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, choices, panel),
            BorderLayout.CENTER);
        frame.pack();
    }

    private void addTools() {
        addTool("New Character", this::newCharacter);
        addTool("Load Character", this::loadCharacter);
        addTool("Save Character", this::saveCharacter,
            () -> character.isPresent(),
            () -> character.get().isDirty(),
            () -> character.get().hasAttribute(AttributeType.NAME),
            () -> character.get().getChoiceCount() == 0);
        addTool("Level Up", this::levelUpCharacter,
            () -> character.isPresent(), () -> character.get().getChoiceCount() == 0,
            () -> character.get().getLevel() < 20);
        addTool("Show Character Sheet", this::showCharacterSheet,
            () -> character.isPresent(), () -> character.get().getChoiceCount() == 0);
        addTool("Exit", this::exit);
        frame.add(tools, BorderLayout.NORTH);
    }

    private void addTool(String name, Runnable runnable, Supplier<Boolean>... conditions) {
        Action action = new AbstractAction(name) {
            @Override
            public void actionPerformed(ActionEvent e) {
                runnable.run();
                update();
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

    private void newCharacter() {
        if (canDiscardCharacter()) {
            setCharacter(new Character());
            new InitialChoiceGenerator().generateChoices(character.get());
            update();
        }
    }

    private void setCharacter(Character character) {
        this.character = Optional.of(character);
        if (character.hasAttribute(AttributeType.NAME))
            this.name = Optional.of(character.getStringAttribute(AttributeType.NAME));
        character.addChoiceList(choices);
        update();
    }

    private void updateFromChoices() {
        character.ifPresent(panel::updateCharacterData);
        toolEnablers.forEach(Runnable::run);
    }

    private void update() {
        character.ifPresent(panel::updateCharacterData);
        character.ifPresent(choices::update);
        toolEnablers.forEach(Runnable::run);
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
        update();
    }

    public void saveCharacter() {
        try {
            if (Files.notExists(Paths.get("characters")))
                Files.createDirectory(Paths.get("characters"));
            String fileName = character.get().getAttribute(AttributeType.NAME).toString().
                toLowerCase()
                .replaceAll("\\s+", "_").replaceAll("\\W", "") + ".xml";
            Path filePath = Paths.get("characters", fileName);
            if (Files.exists(filePath))
                if (nameNotChanged() || JOptionPane.showConfirmDialog(frame,
                    "Overwrite previous file for character?",
                    "Overwrite Character?", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                    Files.delete(filePath);
                else
                    return;
            saver.save(character.get(), Files.
                newOutputStream(filePath, StandardOpenOption.CREATE_NEW));
            character.get().clearDirty();
            name = Optional.of(character.get().getStringAttribute(AttributeType.NAME));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, ex.toString(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean nameNotChanged() {
        return name.isPresent()
            && name.get().equals(character.get().getStringAttribute(AttributeType.NAME));
    }

    public void showCharacterSheet() {
        new CharacterSheet(character.get()).setVisible(true);
    }
}
