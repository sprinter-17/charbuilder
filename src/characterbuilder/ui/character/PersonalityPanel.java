package characterbuilder.ui.character;

import characterbuilder.character.Character;
import characterbuilder.character.CharacteristicGenerator;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.StringAttribute;
import characterbuilder.utils.StringUtils;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class PersonalityPanel extends CharacterSubPanel {

    private final CharacteristicGenerator GENERATOR = new CharacteristicGenerator(new Random());
    private final JToolBar toolBar = new JToolBar();
    private final List<AddButton> addButtons = new ArrayList<>();
    private final JButton deleteButton = new JButton("Delete");
    private final JButton generateButton = new JButton("Generate Random");
    private final JTable table = new JTable();
    private final TableModel empty = new DefaultTableModel();
    private final PersonalityTable tableMode = new PersonalityTable();

    private class AddButton extends JButton {

        private final AttributeType type;

        public AddButton(AttributeType type) {
            super(type.toString());
            this.type = type;
            addActionListener(ev -> add());
        }

        private void add() {
            getCharacter().addAttribute(new StringAttribute(type, ""));
            tableMode.fireTableDataChanged();
            table.editCellAt(tableMode.getRowCount() - 1, 1);
            table.transferFocus();
            table.scrollRectToVisible(table.getCellRect(tableMode.getRowCount() - 1, 1, false));
            triggerUpdate(getCharacter());
        }

        public void setEnabled() {
            setEnabled(getCharacter() != null
                && getCharacter().getAttributes(type, StringAttribute.class)
                    .noneMatch(attr -> attr.toString().isEmpty()));
        }
    }

    private enum Column {
        TYPE(attr -> attr.getType()),
        DESCRIPTION(attr -> attr);

        private final Function<StringAttribute, Object> reader;

        private Column(Function<StringAttribute, Object> reader) {
            this.reader = reader;
        }

        public static Column column(int column) {
            return values()[column];
        }

    }

    private class PersonalityTable extends AbstractTableModel {

        @Override
        public int getRowCount() {
            return (int) getPersonality().count();
        }

        @Override
        public int getColumnCount() {
            return Column.values().length;
        }

        @Override
        public String getColumnName(int column) {
            return StringUtils.capitaliseEnumName(Column.column(column).name());
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            StringAttribute attr = getPersonality().skip(rowIndex).findAny().get();
            return Column.column(columnIndex).reader.apply(attr);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return Column.column(columnIndex) == Column.DESCRIPTION;
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            if (Column.column(columnIndex) == Column.DESCRIPTION) {
                StringAttribute attr = getPersonality().skip(rowIndex).findFirst().get();
                attr.setValue(value.toString());
                triggerUpdate(getCharacter());
            }
        }

        public void deleteRows(int[] rows) {
            List<Attribute> personality = getPersonality().collect(toList());
            for (int row : rows) {
                getCharacter().removeAttribute(personality.get(row));
            }
            triggerUpdate(getCharacter());
        }
    }

    public PersonalityPanel(CharacterUpdater updater) {
        super("Personality", new BorderLayout(), updater);
        addTools();
        addTable();
    }

    private void addTools() {
        toolBar.add(deleteButton);
        deleteButton.addActionListener(ev -> tableMode.deleteRows(table.getSelectedRows()));
        AttributeType.PERSONALITY.stream()
            .map(AddButton::new)
            .peek(addButtons::add)
            .forEach(toolBar::add);
        toolBar.add(generateButton);
        generateButton.addActionListener(ev -> {
            GENERATOR.generate(getCharacter());
            tableMode.fireTableDataChanged();
            triggerUpdate(getCharacter());
        });
        enableTools();
        add(toolBar, BorderLayout.NORTH);
    }

    private void enableTools() {
        deleteButton.setEnabled(table.getSelectedRows().length > 0);
        addButtons.forEach(AddButton::setEnabled);
        generateButton.setEnabled(getCharacter() != null
            && getCharacter().hasAttribute(AttributeType.BACKGROUND)
            && getCharacter().hasAttribute(AttributeType.ALIGNMENT));
    }

    private void addTable() {
        add(new JScrollPane(table), BorderLayout.CENTER);
        table.setModel(empty);
        table.setEnabled(false);
        table.getSelectionModel().addListSelectionListener(ev -> deleteButton.setEnabled(true));
        TableCellRenderer renderer = table.getDefaultRenderer(Object.class);
        table.setDefaultRenderer(Object.class, (tab, val, sel, foc, row, col) -> {
            Component comp = renderer.getTableCellRendererComponent(tab, val, sel, foc, row, col);
            if (col == 1 && val.toString().isEmpty())
                comp.setBackground(Color.yellow);
            else if (sel)
                comp.setBackground(Color.blue);
            else
                comp.setBackground(Color.white);
            return comp;
        });
    }

    @Override
    public void updateCharacter(Character character) {
        super.updateCharacter(character);
        enableTools();
        table.setEnabled(true);
        table.setModel(tableMode);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(1000);
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.GRAY);
    }

    private Stream<StringAttribute> getPersonality() {
        return getCharacter().getAllAttributes()
            .filter(attr -> AttributeType.PERSONALITY.contains(attr.getType()))
            .map(attr -> (StringAttribute) attr);
    }
}
