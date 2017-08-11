package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.spell.Spell;
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
        gen.level(3).addAttributes(SHADOW_ARTS);
        gen.level(3).addSpellCasting("Monk", AttributeType.WISDOM);
        gen.level(3).addLearntSpells("Monk", Spell.DARKNESS, Spell.DARKVISION,
            Spell.PASS_WITHOUT_TRACE, Spell.SILENCE);
        gen.level(3).addCantrip(Spell.MINOR_ILLUSION, AttributeType.WISDOM);
        gen.level(6).addAttributes(SHADOW_STEP);
        gen.level(11).addAttributes(CLOAK_OF_SHADOWS);
        gen.level(17).addAttributes(OPPORTUNIST);
    }),
    WAY_OF_THE_FOUR_ELEMENTS(gen -> {
        gen.level(3).addAttributes(DISCIPLE_OF_THE_ELEMENTS, ELEMENTAL_ATTUNEMENT);
//        gen.level(3).addChoice(new AttributeChoice("Elemental Discipline",
//            FANGS_OF_THE_FIRE_SNAKE, FIST_OF_FOUR_THUNDERS, FIST_OF_UNBROKEN_AIR,
//            SHAPE_OF_THE_FLOWING_RIVER, WATER_WHIP, RUSH_OF_THE_GALE_SPIRITS,
//            SWEEPING_CINDER_STRIKE));
//        gen.level(6).addChoice(new AttributeChoice("Elemental Discipline",
//            FANGS_OF_THE_FIRE_SNAKE, FIST_OF_FOUR_THUNDERS, FIST_OF_UNBROKEN_AIR,
//            SHAPE_OF_THE_FLOWING_RIVER, WATER_WHIP, RUSH_OF_THE_GALE_SPIRITS,
//            SWEEPING_CINDER_STRIKE, GONG_OF_THE_SUMMIT, CLENCH_OF_THE_NORTH_WIND));
//        gen.level(11).addChoice(new AttributeChoice("Elemental Discipline",
//            FANGS_OF_THE_FIRE_SNAKE, FIST_OF_FOUR_THUNDERS, FIST_OF_UNBROKEN_AIR,
//            SHAPE_OF_THE_FLOWING_RIVER, WATER_WHIP, RUSH_OF_THE_GALE_SPIRITS,
//            SWEEPING_CINDER_STRIKE, GONG_OF_THE_SUMMIT, CLENCH_OF_THE_NORTH_WIND,
//            FLAMES_OF_THE_PHOENIX, MIST_STANCE, RIDE_THE_WIND));
//        gen.level(17).addChoice(new AttributeChoice("Elemental Discipline",
//            FANGS_OF_THE_FIRE_SNAKE, FIST_OF_FOUR_THUNDERS, FIST_OF_UNBROKEN_AIR,
//            SHAPE_OF_THE_FLOWING_RIVER, WATER_WHIP, RUSH_OF_THE_GALE_SPIRITS,
//            SWEEPING_CINDER_STRIKE, GONG_OF_THE_SUMMIT, CLENCH_OF_THE_NORTH_WIND,
//            FLAMES_OF_THE_PHOENIX, MIST_STANCE, RIDE_THE_WIND, BREATH_OF_WINTER,
//            ENTERNAL_MOUNTAIN_DEFENCE, RIVER_OF_HUNGRY_FLAME, WAVE_OF_ROLLING_EARTH));
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
