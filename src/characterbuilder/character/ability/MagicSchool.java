package characterbuilder.character.ability;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.EMPOWERED_EVOCATION;
import static characterbuilder.character.ability.Ability.EVOCATION_SAVANT;
import static characterbuilder.character.ability.Ability.OVERCHANNEL;
import static characterbuilder.character.ability.Ability.POTENT_CANTRIP;
import static characterbuilder.character.ability.Ability.SCULPT_SPELLS;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;
import org.w3c.dom.Node;

public enum MagicSchool implements Attribute {
    ABJURATION(gen -> {

    }),
    CONJURATION(gen -> {

    }),
    DIVINATION(gen -> {

    }),
    ENCHANTMENT(gen -> {

    }),
    EVOCATION(gen -> {
        gen.level(2).addAttributes(EVOCATION_SAVANT, SCULPT_SPELLS);
        gen.level(6).addAttributes(POTENT_CANTRIP);
        gen.level(10).addAttributes(EMPOWERED_EVOCATION);
        gen.level(14).addAttributes(OVERCHANNEL);
    }),
    ILLUSION(gen -> {

    }),
    NECROMANCY(gen -> {

    }),
    TRANSMUTATION(gen -> {

    });

    private final ChoiceGenerator generator = new ChoiceGenerator();

    private MagicSchool(Consumer<ChoiceGenerator> generator) {
        generator.accept(this.generator);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ARCANE_TRADITION;
    }

    @Override
    public void generateLevelChoices(Character character) {
        generator.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static MagicSchool load(Node node) {
        return MagicSchool.valueOf(node.getTextContent());
    }
}
