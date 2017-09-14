package characterbuilder.ui.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.equipment.AdventureGear;
import characterbuilder.character.equipment.Armour;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentCategory.TREASURE;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.MusicalInstrument;
import characterbuilder.character.equipment.Weapon;
import characterbuilder.ui.character.CharacterSubPanel;
import characterbuilder.ui.character.CharacterUpdater;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
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

    private final JButton buyButton = new JButton("Buy");
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
        toolBar.add(buyButton);
        buyButton.addActionListener(this::buyItem);
        toolBar.add(addButton);
        addButton.addActionListener(this::addItem);
        toolBar.add(removeButton);
        removeButton.addActionListener(this::removeItem);
        toolBar.add(editButton);
        editButton.addActionListener(this::editItem);
    }

    private void addStatusField() {
        add(statusField, BorderLayout.SOUTH);
        setStatus();
    }

    private void setStatus() {
        if (getCharacter() != null)
            setValues();
        else
            clearValues();
    }

    private void setValues() {
        StringBuilder status = new StringBuilder();
        Weight weight = getCharacter().getInventoryWeight();
        Value worth = getCharacter().getTreasureValue();
        status.append("Weight: ").append(weight.toString())
            .append("; Treasure: ").append(worth.toString());
        buyButton.setEnabled(worth.isGreaterThan(Value.ZERO));
        addButton.setEnabled(true);
        removeButton.setEnabled(getSelectedItem().isPresent());
        editButton.setEnabled(getSelectedItem().isPresent());
        statusField.setText(status.toString());
    }

    private void clearValues() {
        buyButton.setEnabled(false);
        addButton.setEnabled(false);
        removeButton.setEnabled(false);
        editButton.setEnabled(false);
        statusField.setText(null);
    }

    private Optional<Equipment> getSelectedItem() {
        return Optional.ofNullable(inventory.getSelectionPath())
            .map(TreePath::getLastPathComponent)
            .map(node -> (DefaultMutableTreeNode) node)
            .filter(node -> node.isLeaf())
            .map(node -> (Equipment) node.getUserObject());
    }

    private void buyItem(ActionEvent event) {
        Value wealth = getCharacter().getTreasureValue();
        JPopupMenu popup = new JPopupMenu();
        equipmentList.forEach((category, equipment) -> {
            JMenu equipmentMenu = new JMenu(category.toString());
            equipment.forEach(eq -> equipmentMenu.add(addItemAction(eq, true))
                .setEnabled(!eq.getValue().isGreaterThan(wealth)));
            if (category != TREASURE)
                popup.add(equipmentMenu);
        });
        popup.show(buyButton, 0, 0);
    }

    private void addItem(ActionEvent event) {
        JPopupMenu popup = new JPopupMenu();
        equipmentList.forEach((category, equipment) -> {
            JMenu equipmentMenu = new JMenu(category.toString());
            if (category.equals(EquipmentCategory.TREASURE)) {
                equipmentMenu.add(addTreasure(AdventureGear.COPPER_PIECE));
                equipmentMenu.add(addTreasure(AdventureGear.SILVER_PIECE));
                equipmentMenu.add(addTreasure(AdventureGear.GOLD_PIECE));
                equipmentMenu.add(addCustomTreasure());
            } else {
                equipment.forEach(eq -> equipmentMenu.add(addItemAction(eq, false)));
            }
            popup.add(equipmentMenu);
        });
        popup.show(addButton, 0, 0);
    }

    private Action addTreasure(AdventureGear treasure) {
        return new AbstractAction(treasure.toString() + "s") {
            @Override
            public void actionPerformed(ActionEvent e) {
                editItem(treasure.makeSet(100), false);
            }
        };
    }

    private Action addItemAction(Equipment equipment, boolean buy) {
        return new AbstractAction(equipmentDescription(equipment)) {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEquipment(equipment, buy);
            }
        };
    }

    private Action addCustomTreasure() {
        return new AbstractAction("Custom...") {
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
        };
    }

    private String equipmentDescription(Equipment equipment) {
        return String.format("%s (%s/%s)", equipment.toString(), equipment.getValue().toString(),
            equipment.getWeight().toString());
    }

    private void addEquipment(Equipment equipment, boolean buy) {
        getCharacter().addEquipment(equipment);
        if (buy)
            getCharacter().spendTreasure(equipment.getValue());
        triggerUpdate(getCharacter());
    }

    private void removeItem(ActionEvent event) {
        getSelectedItem().ifPresent(this::processRemoveEquipment);
    }

    private void processRemoveEquipment(Equipment equipment) {
        if (equipment.getCount() == 1)
            removeSingleItem(equipment);
        else
            removeMultipleItems(equipment);
    }

    private void removeSingleItem(Equipment equipment) {
        getCharacter().removeEquipment(equipment.makeSet(1));
        triggerUpdate(getCharacter());
    }

    private void removeMultipleItems(Equipment equipment) throws HeadlessException {
        EquipmentCountDialog dialog = new EquipmentCountDialog("Equipment Removal", equipment,
            count -> {
            getCharacter().removeEquipment(equipment.makeSet(count));
            triggerUpdate(getCharacter());
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void editItem(ActionEvent ev) {
        getSelectedItem().ifPresent(eq -> editItem(eq, true));
    }

    private void editItem(Equipment item, boolean remove) {
        EquipmentEditDialog dialog = new EquipmentEditDialog(item, es -> {
            if (remove) {
                EquipmentSet toRemove = item.makeSet(item.getCount());
                getCharacter().removeEquipment(toRemove);
            }
            getCharacter().addEquipment(es);
            triggerUpdate(getCharacter());
        });
        dialog.setLocationRelativeTo(scroller);
        dialog.setVisible(true);
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
        Arrays.stream(AdventureGear.values())
            .map(et -> et.makeSet(et.getPreferredCount()))
            .forEach(sets::add);
        Stream.of(Weapon.values(), MusicalInstrument.values(), Armour.values())
            .flatMap(Arrays::stream).map(eq -> eq.makeSet(1))
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
