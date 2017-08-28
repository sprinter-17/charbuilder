package characterbuilder.ui.ability;

import characterbuilder.character.Character;
import characterbuilder.character.choice.Option;
import characterbuilder.ui.CharacterSubPanel;
import characterbuilder.ui.CharacterUpdater;
import java.awt.BorderLayout;
import java.awt.Dimension;
import static java.util.stream.Collectors.joining;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

public class AbilityPanel extends CharacterSubPanel {

    private final JTree abilityTree = new JTree(new DefaultMutableTreeNode());

    public AbilityPanel(CharacterUpdater updater) {
        super("Abilities", new BorderLayout(), updater);
        setMinimumSize(new Dimension(150, 350));
        abilityTree.setRootVisible(false);
        TreeCellRenderer renderer = abilityTree.getCellRenderer();
        abilityTree.setCellRenderer((tree, value, selected, expanded, leaf, row, focus) -> {
            JComponent component = (JComponent) renderer
                .getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, focus);
            String description = "";
            if (value instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                if (node.getUserObject() instanceof Option) {
                    Option option = (Option) node.getUserObject();
                    description = option.getDescription(getCharacter()).collect(joining("<br>"));
                }
            }
            if (description.isEmpty())
                component.setToolTipText(null);
            else
                component.setToolTipText("<html>" + description + "</html>");
            return component;
        });
        ToolTipManager.sharedInstance().registerComponent(abilityTree);
        add(new JScrollPane(abilityTree), BorderLayout.CENTER);
    }

    @Override
    public void updateCharacter(Character character) {
        super.updateCharacter(character);
        abilityTree.setModel(new DefaultTreeModel(new AbilityTreeRoot(character)));
        for (int i = 0; i < abilityTree.getRowCount(); i++) {
            abilityTree.expandRow(i);
        }
    }

}
