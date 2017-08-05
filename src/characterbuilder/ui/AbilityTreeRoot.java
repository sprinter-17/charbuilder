package characterbuilder.ui;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Spell;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;

public class AbilityTreeRoot extends DefaultMutableTreeNode {

    private final EnumMap<AttributeType, DefaultMutableTreeNode> typeNodes
        = new EnumMap<>(AttributeType.class);
    private final Map<Integer, DefaultMutableTreeNode> spellLevelNodes = new HashMap<>();

    public AbilityTreeRoot(Character character) {
        character.getAllAttributes()
            .filter(att -> ABILITIES.stream().anyMatch(att::hasType))
            .forEach(ab -> {
                typeNodes.computeIfAbsent(ab.getType(), DefaultMutableTreeNode::new);
                if (ab.hasType(SPELL)) {
                    Spell spell = (Spell) ab;
                    int level = spell.getLevel();
                    if (!spellLevelNodes.containsKey(level)) {
                        String levelName = level == 0 ? "Cantrips" : "Level " + level;
                        DefaultMutableTreeNode levelNode = new DefaultMutableTreeNode(levelName);
                        spellLevelNodes.put(level, levelNode);
                        typeNodes.get(ab.getType()).add(levelNode);
                    }
                    spellLevelNodes.get(level).add(new DefaultMutableTreeNode(ab));
                } else {
                    typeNodes.get(ab.getType()).add(new DefaultMutableTreeNode(ab));
                }
            });
        typeNodes.values().forEach(this::add);
    }
}
