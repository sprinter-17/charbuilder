package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import org.w3c.dom.Node;

public enum MagicSchool implements Attribute {
    ABJURATION(ARCANE_WARD, PROJECTED_WARD, IMPROVED_ABJURATION, SPELL_RESISTANCE),
    CONJURATION(MINOR_CONJURATION, BENIGN_TRANSPOSITION, FOCUSED_CONJURATION, DURABLE_SUMMONS),
    DIVINATION(PORTENT, EXPERT_DIVINATION, THE_THIRD_EYE, GREATER_PORTENT),
    ENCHANTMENT(HYPNOTIC_GAZE, INSTINCTIVE_CHARM, SPLIT_ENCHANTMENT, ALERT_MEMORIES),
    EVOCATION(SCULPT_SPELLS, POTENT_CANTRIP, EMPOWERED_EVOCATION, OVERCHANNEL),
    ILLUSION(IMPROVED_MINOR_ILLUSION, MALLEABLE_ILLUSIONS, ILLUSORY_SELF, ILLUSORY_REALITY),
    NECROMANCY(GRIM_HARVEST, UNDEAD_THRALLS, INURED_TO_UNDEATH, COMMAND_UNDEAD),
    TRANSMUTATION(MINOR_ALCHEMY, TRANSMUTERS_STONE, SHAPECHANGER, MASTER_TRANSMUTER);

    // Normal AttributeDelegate method causes initialisation errors due to mutual references
    // between ability, character class and magic school
    private final Ability ability2;
    private final Ability ability6;
    private final Ability ability10;
    private final Ability ability14;

    private MagicSchool(Ability ability2, Ability ability6, Ability ability10, Ability ability14) {
        this.ability2 = ability2;
        this.ability6 = ability6;
        this.ability10 = ability10;
        this.ability14 = ability14;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ARCANE_TRADITION;
    }

    @Override
    public void generateLevelChoices(Character character) {
        switch (character.getLevel()) {
            case 2:
                ability2.choose(character);
                break;
            case 6:
                ability6.choose(character);
                break;
            case 10:
                ability10.choose(character);
                break;
            case 14:
                ability14.choose(character);
                break;
        }
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static MagicSchool load(Node node) {
        return MagicSchool.valueOf(node.getTextContent());
    }
}
