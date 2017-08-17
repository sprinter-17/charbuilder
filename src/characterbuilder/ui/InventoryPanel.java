package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.equipment.Armour;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.EquipmentType;
import characterbuilder.character.equipment.MusicalInstrument;
import characterbuilder.character.equipment.Weapon;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class InventoryPanel extends CharacterSubPanel {

    private final JButton addButton = new JButton("Add");
    private final JButton removeButton = new JButton("Remove");
    private final JButton editButton = new JButton("Edit");
    private final JLabel statusField = new JLabel();
    private final JTree inventory = new JTree(new DefaultMutableTreeNode());
    private final JScrollPane scroller = new JScrollPane(inventory);
    private final EnumMap<EquipmentCategory, List<Equipment>> equipmentList
        = new EnumMap<>(EquipmentCategory.class);

    public InventoryPanel(CharacterUpdater updater) {
        super("Inventory", new BorderLayout(), updater);
        addButtons();
        addInventoryTree();
        addStatusField();
    }

    private void addButtons() {
        JToolBar toolBar = new JToolBar();
        add(toolBar, BorderLayout.NORTH);
        toolBar.add(addButton);
        toolBar.add(removeButton);
        toolBar.add(editButton);
        addButton.addActionListener(this::addItem);
        removeButton.addActionListener(this::removeItem);
        editButton.addActionListener(this::editItem);
    }

    private void addStatusField() {
        add(statusField, BorderLayout.SOUTH);
        setStatus();
    }

    private void setStatus() {
        StringBuilder status = new StringBuilder();
        if (getCharacter() != null) {
            Weight weight = getCharacter().getInventoryWeight();
            Value worth = getCharacter().getTreasureValue();
            status
                .append("Weight: ")
                .append(weight.toString())
                .append("; Treasure: ")
                .append(worth.toString());
            addButton.setEnabled(worth.isGreaterThan(Value.ZERO));
            removeButton.setEnabled(getSelectedItem().isPresent());
            editButton.setEnabled(getSelectedItem().isPresent());
        } else {
            addButton.setEnabled(false);
            removeButton.setEnabled(false);
            editButton.setEnabled(false);
        }
        statusField.setText(status.toString());
    }

    private Optional<Equipment> getSelectedItem() {
        return Optional.ofNullable(inventory.getSelectionPath())
            .map(TreePath::getLastPathComponent)
            .map(node -> (DefaultMutableTreeNode) node)
            .filter(node -> node.isLeaf())
            .map(node -> (Equipment) node.getUserObject());
    }

    private void addItem(ActionEvent event) {
        Value wealth = getCharacter().getTreasureValue();
        JPopupMenu popup = new JPopupMenu();
        for (EquipmentCategory category : equipmentList.keySet()) {
            JMenu equipmentMenu = new JMenu(category.toString());
            equipmentList.getOrDefault(category, new ArrayList<>())
                .forEach(eq -> equipmentMenu.add(addItemAction(eq))
                .setEnabled(!eq.getValue().isGreaterThan(wealth)));
            if (category.equals(EquipmentCategory.TREASURE))
                equipmentMenu.add(new AbstractAction("Custom...") {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = (JFrame) SwingUtilities.
                            getWindowAncestor(InventoryPanel.this);
                        CustomTreasureDialog dialog = new CustomTreasureDialog(frame, tr -> {
                            getCharacter().addEquipment(tr);
                            triggerUpdate(getCharacter());
                        });
                        dialog.setVisible(true);
                    }
                });
            popup.add(equipmentMenu);
        }
        popup.show(addButton, 0, 0);
    }

    private Action addItemAction(Equipment equipment) {
        return new AbstractAction(equipment.toString()) {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEquipment(equipment);
            }
        };
    }

    private void addEquipment(Equipment equipment) {
        getCharacter().addEquipment(equipment);
        if (equipment.getCategory() != EquipmentCategory.TREASURE)
            getCharacter().spendTreasure(equipment.getValue());
        triggerUpdate(getCharacter());
    }

    private void removeItem(ActionEvent event) {
        getSelectedItem().ifPresent(this::processRemoveEquipment);
    }

    private void processRemoveEquipment(Equipment equipment) {
        if (equipment.getCount() == 1) {
            getCharacter().removeEquipment(new EquipmentSet(equipment));
            triggerUpdate(getCharacter());
        } else {
            EquipmentCountDialog dialog = new EquipmentCountDialog("Equipment Removal",
                equipment, count -> {
                getCharacter().removeEquipment(new EquipmentSet(equipment, count));
                triggerUpdate(getCharacter());
            });
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
        }
    }

    private void editItem(ActionEvent ev) {
        getSelectedItem().ifPresent(eq -> {
            EquipmentEditDialog dialog = new EquipmentEditDialog(eq, es -> {
                getCharacter().removeEquipment(new EquipmentSet(eq, eq.getBonus(), eq.getCount()));
                getCharacter().addEquipment(es);
                triggerUpdate(getCharacter());
            });
            dialog.setLocationRelativeTo(scroller);
            dialog.setVisible(true);
        });
    }

    private void addInventoryTree() {
        add(scroller, BorderLayout.CENTER);
        inventory.setRootVisible(false);
        inventory.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                if (ev.getClickCount() == 2)
                    editItem(null);
                setStatus();
            }
        });
        List<EquipmentSet> sets = new ArrayList<>();
        Arrays.stream(EquipmentType.values())
            .map(et -> new EquipmentSet(et, et.getPreferredCount()))
            .forEach(sets::add);
        Stream.of(Weapon.values(), MusicalInstrument.values(), Armour.values())
            .flatMap(Arrays::stream).map(eq -> new EquipmentSet(eq))
            .forEach(sets::add);
        sets.stream().map(Equipment::getCategory).distinct()
            .forEach(cat -> equipmentList.put(cat, new ArrayList<>()));
        sets.forEach(eq -> equipmentList.get(eq.getCategory()).add(eq));
    }

    @Override
    public void updateCharacter(Character character) {
        super.updateCharacter(character);
        inventory.setModel(new EquipmentTreeModel(character));
        for (int i = 0; i < inventory.getRowCount(); i++) {
            inventory.expandRow(i);
        }
        setStatus();
    }
}
