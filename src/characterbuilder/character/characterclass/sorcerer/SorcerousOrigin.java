package characterbuilder.character.characterclass.sorcerer;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.DraconicAncestory;
import static characterbuilder.character.characterclass.sorcerer.Sorcerer.Ability.*;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum SorcerousOrigin implements Attribute {
    DRACONIC_BLOODLINE(gen -> {
        gen.level(1).addChoice(new AttributeChoice("Draconic Ancestry", DraconicAncestory.values()));
        gen.level(1).addAttributes(Proficiency.DRACONIC, DRACONIC_RESILIENCE);
        gen.level(6).addAttributes(ELEMENTAL_AFFINITY);
        gen.level(14).addAttributes(DRAGON_WINGS);
        gen.level(18).addAttributes(DRACONIC_PRESENCE);
    }),
    WILD_MAGIC(gen -> {
        gen.level(1).addAttributes(WILD_MAGIC_SURGE, TIDES_OF_CHAOS);
        gen.level(6).addAttributes(BEND_LUCK);
        gen.level(14).addAttributes(CONTROLLED_CHAOS);
        gen.level(18).addAttributes(SPELL_BOMBARDMENT);
    });

    private final ChoiceGenerator generator = new ChoiceGenerator();

    private SorcerousOrigin(Consumer<ChoiceGenerator> gen) {
        gen.accept(generator);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SORCEROUS_ORIGIN;
    }

    @Override
    public void generateLevelChoices(Character character) {
        generator.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return generator.getDescription(character);
    }

    public static SorcerousOrigin load(Node node) {
        return valueOf(node.getTextContent());
    }

}
