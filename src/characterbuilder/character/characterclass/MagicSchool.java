package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import static characterbuilder.character.ability.Ability.ARCANE_WARD;
import static characterbuilder.character.ability.Ability.EMPOWERED_EVOCATION;
import static characterbuilder.character.ability.Ability.IMPROVED_ABJURATION;
import static characterbuilder.character.ability.Ability.OVERCHANNEL;
import static characterbuilder.character.ability.Ability.POTENT_CANTRIP;
import static characterbuilder.character.ability.Ability.PROJECTED_WARD;
import static characterbuilder.character.ability.Ability.SCULPT_SPELLS;
import static characterbuilder.character.ability.Ability.SPELL_RESISTANCE;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Node;

public enum MagicSchool implements Attribute {
    ABJURATION(ARCANE_WARD, PROJECTED_WARD, IMPROVED_ABJURATION, SPELL_RESISTANCE),
    CONJURATION,
    DIVINATION,
    ENCHANTMENT,
    EVOCATION(SCULPT_SPELLS, POTENT_CANTRIP, EMPOWERED_EVOCATION, OVERCHANNEL),
    ILLUSION,
    NECROMANCY,
    TRANSMUTATION;

    // Normal AttributeDelegate method causes initialisation errors due to mutual references
    // between ability, character class and magic school
    private final Map<Integer, Ability> abilities = new HashMap<>();

    private MagicSchool() {
    }

    private MagicSchool(Ability ability2, Ability ability6, Ability ability10, Ability ability14) {
        abilities.put(2, ability2);
        abilities.put(6, ability6);
        abilities.put(10, ability10);
        abilities.put(14, ability14);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ARCANE_TRADITION;
    }

    @Override
    public void generateLevelChoices(Character character) {
        int level = character.getLevel();
        if (abilities.containsKey(level))
            abilities.get(level).choose(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static MagicSchool load(Node node) {
        return MagicSchool.valueOf(node.getTextContent());
    }
}
