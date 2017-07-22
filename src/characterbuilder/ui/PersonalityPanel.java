package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.CharacteristicGenerator;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.StringAttribute;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import javax.swing.AbstractAction;
import javax.swing.Action;
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
    private final List<JButton> addButtons = new ArrayList<>();
    private final JButton deleteButton = new JButton("Delete");
    private final JButton generateButton = new JButton("Generate Random");
    private final JTable table = new JTable();
    private final TableModel empty = new DefaultTableModel();
    private final AbstractTableModel model = new AbstractTableModel() {

        @Override
        public int getRowCount() {
            return (int) getPersonality().count();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Type";
                case 1:
                    return "Description";
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Attribute attr = getPersonality().skip(rowIndex).findAny().get();
            switch (columnIndex) {
                case 0:
                    return attr.getType();
                case 1:
                    return attr;
                default:
                    throw new AssertionError();
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 1;
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            if (columnIndex == 1) {
                StringAttribute attr = getPersonality().skip(rowIndex).findFirst().get();
                attr.setValue(value.toString());
                triggerUpdate(getCharacter());
            }
        }

    };

    public PersonalityPanel(CharacterUpdater updater) {
        super("Personality", new BorderLayout(), updater);
        addTools();
        addTable();
    }

    private void addTools() {
        toolBar.add(deleteButton);
        deleteButton.addActionListener(this::deleteCharacteristic);
        AttributeType.PERSONALITY.stream()
            .map(this::makeButtonForType).map(JButton::new)
            .peek(addButtons::add)
            .forEach(toolBar::add);
        toolBar.add(generateButton);
        generateButton.addActionListener(ev -> {
            GENERATOR.generate(getCharacter());
            model.fireTableDataChanged();
            triggerUpdate(getCharacter());
        });
        enableTools();
        add(toolBar, BorderLayout.NORTH);
    }

    private void enableTools() {
        deleteButton.setEnabled(table.getSelectedRows().length > 0);
        addButtons.forEach(button -> button.setEnabled(getCharacter() != null));
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

    private void deleteCharacteristic(ActionEvent ev) {
        int selected = table.getSelectedRow();
        List<Attribute> personality = getPersonality().collect(toList());
        for (int row : table.getSelectedRows()) {
            getCharacter().removeAttribute(personality.get(row));
        }
        model.fireTableDataChanged();
        selected = Math.min(selected, model.getRowCount() - 1);
        if (selected >= 0)
            table.addRowSelectionInterval(selected, selected);
        triggerUpdate(getCharacter());
    }

    private Action makeButtonForType(AttributeType type) {
        return new AbstractAction(type.toString()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                getCharacter().addAttribute(new StringAttribute(type, ""));
                model.fireTableDataChanged();
                table.editCellAt(model.getRowCount() - 1, 1);
                table.transferFocus();
                table.scrollRectToVisible(table.getCellRect(model.getRowCount() - 1, 1, false));
                triggerUpdate(getCharacter());
            }
        };
    }

    @Override
    public void updateCharacter(Character character) {
        super.updateCharacter(character);
        enableTools();
        table.setEnabled(true);
        table.setModel(model);
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
