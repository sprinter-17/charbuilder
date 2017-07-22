package characterbuilder.ui;

import characterbuilder.character.Character;
import java.util.stream.Collectors;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class EquipmentTreeModel extends DefaultTreeModel {

    public EquipmentTreeModel(Character character) {
        super(new DefaultMutableTreeNode());
        addNodes(character);
    }

    private void addNodes(Character character) {
        character.getItemisedEquipment()
            .collect(Collectors.groupingBy(eq -> eq.getCategory()))
            .forEach((type, equipment) -> {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(type);
                ((DefaultMutableTreeNode) getRoot()).add(node);
                equipment.stream().forEach(item -> node.add(new DefaultMutableTreeNode(item)));
            });
    }
}
