package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import org.w3c.dom.Node;

public enum MagicSchool implements Attribute {
    ABJURATION(magicSchool()),
//        .withLevelAttributes(2, ARCANE_WARD)
//        .withLevelAttributes(6, PROJECTED_WARD)
//        .withLevelAttributes(10, IMPROVED_ABJURATION)
//        .withLevelAttributes(14, SPELL_RESISTANCE)),
    CONJURATION(magicSchool()),
    DIVINATION(magicSchool()),
    ENCHANTMENT(magicSchool()),
    EVOCATION(magicSchool()),
//        .withLevelAttributes(2, SCULPT_SPELLS)
//        .withLevelAttributes(6, POTENT_CANTRIP)
//        .withLevelAttributes(10, EMPOWERED_EVOCATION)
//        .withLevelAttributes(14, OVERCHANNEL)),
    ILLUSION(magicSchool()),
    NECROMANCY(magicSchool()),
    TRANSMUTATION(magicSchool());

    private final AttributeDelegate delegate;

    private static AttributeDelegate magicSchool() {
        return new AttributeDelegate();
    }

    private MagicSchool(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ARCANE_TRADITION;
    }

    @Override
    public void generateLevelChoices(Character character) {
        delegate.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static MagicSchool load(Node node) {
        return MagicSchool.valueOf(node.getTextContent());
    }
}
