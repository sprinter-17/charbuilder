package characterbuilder.character.characterclass.monk;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.WISDOM;
import static characterbuilder.character.characterclass.CharacterClass.WIZARD;
import static characterbuilder.character.characterclass.monk.ElementalDiscipline.*;
import static characterbuilder.character.characterclass.monk.Monk.Ability.*;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.spell.Spell;
import static characterbuilder.character.spell.Spell.*;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;
import org.w3c.dom.Node;

public enum MonasticTradition implements Attribute {
    WAY_OF_THE_OPEN_HAND(gen -> {
        gen.level(3).addAttributes(OPEN_HAND_TECHNIQUE);
        gen.level(6).addAttributes(WHOLENESS_OF_BODY);
        gen.level(11).addAttributes(TRANQUILITY);
        gen.level(17).addAttributes(QUIVERING_PALM);
    }),
    WAY_OF_SHADOW(gen -> {
        gen.level(3)
            .addAttributes(SHADOW_ARTS)
            .addSpellCasting("Monk", WISDOM, WIZARD, "All")
            .addLearntSpells("Monk", DARKNESS, Spell.DARKVISION, PASS_WITHOUT_TRACE, SILENCE);
        gen.level(3).addSpellAbility(MINOR_ILLUSION, WISDOM);
        gen.level(6).addAttributes(SHADOW_STEP);
        gen.level(11).addAttributes(CLOAK_OF_SHADOWS);
        gen.level(17).addAttributes(OPPORTUNIST);
    }),
    WAY_OF_THE_FOUR_ELEMENTS(gen -> {
        gen.level(3).addAttributes(DISCIPLE_OF_THE_ELEMENTS, ELEMENTAL_ATTUNEMENT);
        gen.level(6, 11, 17).addChoice(replaceDiscipline());
        gen.level(3, 6, 11, 17).addChoice(chooseDiscipline());
    });

    private final ChoiceGenerator generator = new ChoiceGenerator();

    private MonasticTradition(Consumer<ChoiceGenerator> generator) {
        generator.accept(this.generator);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.MONASTIC_TRADITION;
    }

    @Override
    public void generateLevelChoices(Character character) {
        generator.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static MonasticTradition load(Node node) {
        return MonasticTradition.valueOf(node.getTextContent());
    }
}
