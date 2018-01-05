package characterbuilder.ui.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.SpellCasting;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.tree.DefaultMutableTreeNode;

public class AbilityTreeRoot extends DefaultMutableTreeNode {

    public AbilityTreeRoot(Character character) {
        Arrays.stream(AttributeType.values())
            .filter(character::hasAttribute)
            .forEach(at -> addAttributeType(character, at));
    }

    private void addAttributeType(Character character, AttributeType type) {
        switch (type) {
            case SPELLCASTING:
                addSpellCastings(character);
                break;
            case SPELL_ABILITY:
            case RACIAL_TALENT:
            case CLASS_TALENT:
            case DIVINE_DOMAIN_ABILITY:
            case ARCANE_TRADITION_ABILITY:
            case ELDRITCH_INVOCATION:
            case SKILL:
            case WEAPON_PROFICIENCY:
            case ARMOUR_PROFICIENCY:
            case LANGUAGE:
            case ARTISAN:
            case TOOLS:
            case FIGHTING_STYLE:
            case MANEUVER:
            case SPELL_MASTERY:
            case EXPERTISE:
            case MUSICAL_INSTRUMENT_PROFICIENCY:
            case FEAT:
            case BARD_ABILITY:
            case CLERIC_ABILITY:
            case ROGUE_ABILITY:
            case RANGER_ABILITY:
            case FIGHTER_ABILITY:
            case PALADIN_ABILITY:
            case WARLOCK_ABILITY:
            case SORCERER_ABILITY:
            case META_MAGIC:
                addAttributes(character, type);
                break;
        }
    }

    private void addSpellCastings(Character character) {
        character.getAttributes(SPELLCASTING, SpellCasting.class)
            .forEach(sc -> addSpellCasting(sc));
    }

    private void addSpellCasting(SpellCasting casting) {
        DefaultMutableTreeNode castingNode = new DefaultMutableTreeNode(casting);
        add(castingNode);
        casting.getLearntSpells()
            .collect(Collectors.groupingBy(ls -> ls.getSpell().getLevel()))
            .forEach((level, spells) -> addSpellLevel(castingNode, level, spells));
    }

    private void addSpellLevel(DefaultMutableTreeNode castingNode, int level,
        List<LearntSpell> spells) {
        String levelName = level == 0 ? "Cantrips" : "Level " + level;
        DefaultMutableTreeNode levelNode = new DefaultMutableTreeNode(levelName);
        castingNode.add(levelNode);
        spells.sort(Comparator.comparing(LearntSpell::toString));
        spells.forEach(spell -> {
            levelNode.add(new DefaultMutableTreeNode(spell));
        });
    }

    private void addAttributes(Character character, AttributeType type) {
        if (character.hasAttribute(type)) {
            DefaultMutableTreeNode typeNode = new DefaultMutableTreeNode(type);
            add(typeNode);
            character.getAttributes(type, Attribute.class)
                .sorted(Comparator.comparing(Attribute::toString))
                .map(DefaultMutableTreeNode::new)
                .forEach(typeNode::add);
        }
    }
}
