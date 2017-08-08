package characterbuilder.character.characterclass;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.ability.Ability.*;
import static characterbuilder.character.spell.Spell.*;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;
import org.w3c.dom.Node;

public enum SacredOath implements Attribute {
    OATH_OF_DEVOTION(gen -> {
        gen.level(3).addAttributes(PROTECTION_FROM_EVIL_AND_GOOD, SANCTUARY);
        gen.level(3).addAttributes(SACRED_WEAPON, TURN_THE_UNHOLY);
        gen.level(5).addAttributes(LESSER_RESTORATION, ZONE_OF_TRUTH);
        gen.level(7).addAttributes(AURA_OF_DEVOTION);
        gen.level(9).addAttributes(BEACON_OF_HOPE, DISPEL_MAGIC);
        gen.level(13).addAttributes(FREEDOM_OF_MOVEMENT, GUARDIAN_OF_FAITH);
        gen.level(15).addAttributes(PURITY_OF_SPIRIT);
        gen.level(17).addAttributes(COMMUNE, FLAME_STRIKE);
        gen.level(20).addAttributes(HOLY_NIMBUS);
    }),
    OATH_OF_THE_ANCIENTS(gen -> {
        gen.level(3).addAttributes(ENSNARING_STRIKE, SPEAK_WITH_ANIMALS);
        gen.level(3).addAttributes(NATURES_WRATH, TURN_THE_FAITHLESS);
        gen.level(5).addAttributes(MISTY_STEP, MOONBEAM);
        gen.level(7).addAttributes(AURA_OF_WARDING);
        gen.level(9).addAttributes(PLANT_GROWTH, PROTECTION_FROM_ENERGY);
        gen.level(13).addAttributes(ICE_STORM, STONESKIN);
        gen.level(15).addAttributes(UNDYING_SENTINEL);
        gen.level(17).addAttributes(COMMUNE_WITH_NATURE, TREE_STRIDE);
        gen.level(20).addAttributes(ELDER_CHAMPION);
    }),
    OATH_OF_VENGEANCE(gen -> {
        gen.level(3).addAttributes(BANE, HUNTERS_MARK);
        gen.level(3).addAttributes(ABJURE_ENERGY, VOW_OF_ENMITY);
        gen.level(5).addAttributes(MISTY_STEP, HOLD_PERSON);
        gen.level(7).addAttributes(RELENTLESS_AVENGER);
        gen.level(9).addAttributes(HASTE, PROTECTION_FROM_ENERGY);
        gen.level(13).addAttributes(BANISHMENT, DIMENSION_DOOR);
        gen.level(15).addAttributes(SOUL_OF_VENGEANCE);
        gen.level(17).addAttributes(HOLD_MONSTER, SCRYING);
        gen.level(20).addAttributes(AVENGING_ANGEL);
    }),;

    private final ChoiceGenerator gen = new ChoiceGenerator();

    private SacredOath(Consumer<ChoiceGenerator> gen) {
        gen.accept(this.gen);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SACRED_OATH;
    }

    public static SacredOath load(Node node) {
        return valueOf(node.getTextContent());
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
