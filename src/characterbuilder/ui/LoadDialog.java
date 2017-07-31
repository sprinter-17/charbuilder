package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.saveload.CharacterSaver;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class LoadDialog extends JDialog {

    private final Consumer<Character> selector;
    private final CharacterSaver loader;
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JList characterList = new JList();
    private final List<ListItem> listItems = new ArrayList<>();

    private abstract class ListItem {

        protected final Path path;

        public ListItem(Path path) {
            this.path = path;
        }

        public void delete() throws IOException {
            Files.delete(path);
        }

        public abstract Optional<Character> getCharacter();

        public abstract Color getColor();
    }

    private class CharacterReference extends ListItem {

        private final Character character;

        public CharacterReference(Path path, Character character) {
            super(path);
            this.character = character;
        }

        @Override
        public Optional<Character> getCharacter() {
            return Optional.of(character);
        }

        @Override
        public Color getColor() {
            return Color.BLACK;
        }

        @Override
        public void delete() throws IOException {
            if (JOptionPane.showConfirmDialog(LoadDialog.this,
                "Are you sure you wish to delete "
                + character.getAttribute(AttributeType.NAME) + "?",
                "Delete Character",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)
                == JOptionPane.YES_OPTION)
                super.delete();
        }

        @Override
        public String toString() {
            StringBuilder description = new StringBuilder();
            description.append(character.getStringAttribute(NAME))
                .append(": ");
            description.append(character.getStringAttribute(RACE))
                .append(" ");
            description.append(character.getStringAttribute(CHARACTER_CLASS))
                .append("/")
                .append(character.getStringAttribute(LEVEL));
            return description.toString();
        }
    }

    private class Error extends ListItem {

        private final String error;

        public Error(Path path, String error) {
            super(path);
            this.error = error;
        }

        @Override
        public Optional<Character> getCharacter() {
            return Optional.empty();
        }

        @Override
        public Color getColor() {
            return Color.RED;
        }

        @Override
        public String toString() {
            return path.toString() + ": " + error;
        }
    }

    public LoadDialog(Frame parent, Consumer<Character> selector) throws ParserConfigurationException {
        super(parent, "Load Character", true);
        this.selector = selector;
        this.loader = new CharacterSaver();
        addList();
        addButtons();
        add(panel);
        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    public void setVisible(boolean b) {
        if (b)
            readCharacters();
        super.setVisible(b);
    }

    private void readCharacters() {
        try {
            listItems.clear();
            Files.newDirectoryStream(Paths.get("characters"), f -> f.toString().endsWith(".xml"))
                .forEach(path -> listItems.add(addItem(path)));
            characterList.repaint();
        } catch (IOException ex) {
            Logger.getLogger(LoadDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ListItem addItem(Path path) {
        try {
            return new CharacterReference(path, loader.load(Files.newInputStream(path)));
        } catch (SAXException | IOException ex) {
            return new Error(path, ex.toString());
        }
    }

    private void addList() {
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createEtchedBorder());
        characterList.setModel(new AbstractListModel() {
            @Override
            public int getSize() {
                return listItems.size();
            }

            @Override
            public Object getElementAt(int index) {
                return listItems.get(index);
            }
        });
        characterList.setPreferredSize(new Dimension(300, 400));
        characterList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index,
                boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index,
                    isSelected, cellHasFocus);
                component.setForeground(listItems.get(index).getColor());
                return component;
            }
        });
        characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        characterList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2)
                    choose(new ActionEvent(characterList, ActionEvent.ACTION_FIRST, "Double Click"));
            }
        });
        listPanel.add(characterList, BorderLayout.CENTER);
        panel.add(listPanel);
    }

    private void addButtons() {
        JPanel buttons = new JPanel(new FlowLayout());
        JButton delete = new JButton("Delete");
        delete.addActionListener(this::delete);
        buttons.add(delete);
        JButton choose = new JButton("Choose");
        choose.addActionListener(this::choose);
        buttons.add(choose);
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(this::cancel);
        buttons.add(cancel);
        panel.add(buttons, BorderLayout.SOUTH);
    }

    private void delete(ActionEvent ev) {
        try {
            for (int i : characterList.getSelectedIndices()) {
                listItems.get(i).delete();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error deleting: " + ex);
        }
        readCharacters();
        characterList.setSelectedIndex(-1);
    }

    private void choose(ActionEvent ev) {
        if (characterList.getSelectedIndex() >= 0) {
            listItems.get(characterList.getSelectedIndex())
                .getCharacter().ifPresent(selector::accept);
            setVisible(false);
        }
    }

    private void cancel(ActionEvent ev) {
        setVisible(false);
    }

}
