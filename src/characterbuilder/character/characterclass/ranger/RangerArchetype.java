package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.characterclass.CharacterClass.RANGER;
import static characterbuilder.character.characterclass.ranger.RangerAbility.*;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.ClassSpecificChoiceGenerator;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;
import org.w3c.dom.Node;

public enum RangerArchetype implements Attribute {
    HUNTER(gen -> {
        gen.level(3).addChoice(new AttributeChoice("Hunter's Prey",
            COLOSSUS_SLAYER, GIANT_KILLER, HORDE_BREAKER));
        gen.level(7).addChoice(new AttributeChoice("Defensive Tactics",
            ESCAPE_THE_HORDE, MULTIATTACK_DEFENSE, STEEL_WILL));
        gen.level(11).addChoice(new AttributeChoice("Multiattack",
            VOLLEY, WHIRLWIND_ATTACK));
        gen.level(15).addChoice(new AttributeChoice("Superior Hunter's Defense",
            EVASION, STAND_AGAINST_THE_TIDE, UNCANNY_DODGE));
    }),
    BEASTMASTER(gen -> {
        gen.level(3).addChoice(new AttributeChoice("Ranger's Companion",
            RangerCompanion.values()));
        gen.level(7).addAttributes(EXCEPTIONAL_TRAINING);
        gen.level(11).addAttributes(BESTIAL_FURY);
        gen.level(15).addAttributes(SHARE_SPELLS);
    });

    private final ChoiceGenerator generator = new ClassSpecificChoiceGenerator(RANGER);

    private RangerArchetype(Consumer<ChoiceGenerator> generator) {
        generator.accept(this.generator);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.RANGER_ARCHETYPE;
    }

    @Override
    public void generateLevelChoices(Character character) {
        generator.generateChoices(character);
    }

    public static RangerArchetype load(Node node) {
        return valueOf(node.getTextContent());
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
