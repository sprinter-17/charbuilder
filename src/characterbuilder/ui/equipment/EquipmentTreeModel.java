package characterbuilder.ui.equipment;

import characterbuilder.character.Character;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
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
            .entrySet().stream().sorted(comparingInt(e -> e.getKey().ordinal()))
            .forEach(e -> {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(e.getKey());
                ((DefaultMutableTreeNode) getRoot()).add(node);
                e.getValue().stream()
                    .sorted(comparing(eq -> eq.getBaseEquipment().toString()))
                    .map(DefaultMutableTreeNode::new)
                    .forEach(node::add);
            });
    }
}
