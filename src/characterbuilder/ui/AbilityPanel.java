package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.ability.AttributeChoice;
import characterbuilder.character.attribute.Attribute;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import static java.util.stream.Collectors.joining;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

public class AbilityPanel extends CharacterSubPanel {

    private final JTree abilityTree = new JTree(new DefaultMutableTreeNode());

    public AbilityPanel(CharacterUpdater updater) {
        super("Abilities", new BorderLayout(), updater);
        setMinimumSize(new Dimension(350, 350));
        abilityTree.setRootVisible(false);
        abilityTree.addTreeSelectionListener(this::selectNode);
        TreeCellRenderer renderer = abilityTree.getCellRenderer();
        abilityTree.setCellRenderer((tree, value, selected, expanded, leaf, row, focus) -> {
            JComponent component = (JComponent) renderer
                .getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, focus);
            String description = "";
            if (value instanceof DefaultMutableTreeNode) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
                if (node.getUserObject() instanceof Attribute) {
                    Attribute attribute = (Attribute) node.getUserObject();
                    description = attribute.getDescription(getCharacter())
                        .collect(joining("<br>"));
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

    private void selectNode(TreeSelectionEvent ev) {
        if (abilityTree.getSelectionPath() != null) {
            Object selection = abilityTree.getSelectionPath().getLastPathComponent();
            if (selection != null) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) selection;
                if (node.getUserObject() instanceof AttributeChoice) {
                    AttributeChoice choice = (AttributeChoice) node.getUserObject();
                    JPopupMenu menu = new JPopupMenu();
                    choice.getAttributes().forEach(ab -> menu
                        .add(new AbstractAction(ab.toString()) {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                getCharacter().addAttribute(ab);
                                triggerUpdate(getCharacter());
                            }
                        }));
                    menu.show(abilityTree, 0, 0);
                }
            }
        }
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
