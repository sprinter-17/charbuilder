package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.EquipmentType;
import characterbuilder.character.equipment.Weapon;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

public class InventoryPanel extends CharacterSubPanel {

    private final JButton addButton = new JButton("Add");
    private final JButton removeButton = new JButton("Remove");
    private final JLabel wealthField = new JLabel();
    private final JLabel weightField = new JLabel();
    private final JTree inventory = new JTree(new DefaultMutableTreeNode());
    private final EnumMap<EquipmentCategory, List<Equipment>> equipmentList
        = new EnumMap<>(EquipmentCategory.class);

    public InventoryPanel(CharacterUpdater updater) {
        super("Inventory", new BorderLayout(), updater);
        setPreferredSize(new Dimension(250, 350));
        addFields();
        addInventoryTree();
        addButton.setEnabled(false);
        removeButton.setEnabled(false);
        for (Equipment equipment : EquipmentType.values()) {
            equipmentList.putIfAbsent(equipment.getCategory(), new ArrayList<>());
            equipmentList.get(equipment.getCategory()).add(equipment);
        }
        for (Equipment weapon : Weapon.values()) {
            equipmentList.putIfAbsent(weapon.getCategory(), new ArrayList<>());
            equipmentList.get(weapon.getCategory()).add(weapon);
        }
    }

    private void addFields() {
        JPanel fields = new JPanel(new GridLayout(0, 2));
        addButton.addActionListener(this::addItem);
        removeButton.addActionListener(this::removeItem);
        fields.add(addButton);
        fields.add(removeButton);
        fields.add(new JLabel("Wealth"));
        fields.add(wealthField);
        fields.add(new JLabel("Weight"));
        fields.add(weightField);
        add(fields, BorderLayout.NORTH);
    }

    private void addItem(ActionEvent event) {
        Value wealth = getCharacter().getTreasureValue();
        JPopupMenu popup = new JPopupMenu();
        for (EquipmentCategory category : EquipmentCategory.values()) {
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
        getCharacter().addEquipment(new EquipmentSet(equipment));
        getCharacter().spendTreasure(equipment.getValue());
        triggerUpdate(getCharacter());
    }

    private void removeItem(ActionEvent event) {
        if (inventory.getSelectionPath() != null) {
            Object selection = inventory.getSelectionPath().getLastPathComponent();
            if (selection != null) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) selection;
                if (node.isLeaf()) {
                    getCharacter().removeEquipment(new EquipmentSet((EquipmentType) node.
                        getUserObject()));
                    triggerUpdate(getCharacter());
                }
            }
        }
    }

    private void addInventoryTree() {
        inventory.setRootVisible(false);
        add(new JScrollPane(inventory), BorderLayout.CENTER);
    }

    @Override
    public void updateCharacter(Character character) {
        super.updateCharacter(character);
        addButton.setEnabled(character.getTreasureValue().isGreaterThan(Value.ZERO));
        removeButton.setEnabled(true);
        update(wealthField, character.getTreasureValue().toString());
        update(weightField, character.getInventoryWeight().toString());
        inventory.setModel(new EquipmentTreeModel(character));
        for (int i = 0; i < inventory.getRowCount(); i++) {
            inventory.expandRow(i);
        }
    }
}
