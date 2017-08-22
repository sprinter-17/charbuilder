package characterbuilder.character.characterclass.monk;

import static characterbuilder.character.ability.Ability.EVASION;
import static characterbuilder.character.ability.Ability.EXTRA_ATTACK;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_SIMPLE_WEAPONS;
import static characterbuilder.character.ability.Skill.ACROBATICS;
import static characterbuilder.character.ability.Skill.ATHLETICS;
import static characterbuilder.character.ability.Skill.HISTORY;
import static characterbuilder.character.ability.Skill.INSIGHT;
import static characterbuilder.character.ability.Skill.RELIGION;
import static characterbuilder.character.ability.Skill.STEALTH;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.monk.Monk.Ability.*;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.equipment.EquipmentCategory;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import characterbuilder.character.equipment.MusicalInstrument;
import static characterbuilder.character.equipment.Weapon.DART;
import static characterbuilder.character.equipment.Weapon.SHORTSWORD;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public class Monk extends AbstractCharacterClass {

    public enum Ability implements Attribute {
        UNARMORED_DEFENCE(ability().withDescription("Unarmoured AC[10+$dex_mod+$wis_mod]")),
        MARTIAL_ARTS(ability()
            .withDescription("Use Dex. instead of Str. for unarmed strikes and monk weapons; "
                + "can roll [max($level 1:1d4,5:1d6,11:1d8,17:1d10)] for damage; "
                + "unarmed strike as bonus action")),
        KI(ability()
            .withDescription("[$level] Ki points to use between each rest; Ki Save DC[8+$prof+$wis_mod].")),
        FLURRY_OF_BLOWS(ability()
            .withDescription("Spend 1 Ki point to make two unarmed strikes as a bonus action.")),
        PATIENT_DEFENCE(ability()
            .withDescription("Spend 1 Ki point to Dodge as a bonus action.")),
        STEP_OF_THE_WIND(ability()
            .withDescription("Spend 1 Ki point to Disengage or Dash as a bonus action, jump distance doubled.")),
        UNARMOURED_MOVEMENT(ability()
            .withDescription("Speed increases by [max($level 2:10,6:15,10:20,14:25,18:30)]' when not armoured. "
                + "[if($level >= 9:Move along vertical surfaces and across liquids)]")),
        DEFLECT_MISSILES(ability()
            .withDescription("Use reaction when hit by ranged attack reducing damage by 1d10+[$dex_mod+$level]. "
                + "If damage is 0, can spend 1 Ki point to make ranged attack to hit +[$prof], "
                + "martial arts damage range 20'/60'.")),
        SLOW_FALL(ability()
            .withDescription("Use reaction to reduce falling damage by [5*$level].")),
        STUNNING_STRIKE(ability()
            .withDescription("Spend 1 Ki point on melee weapon hit. "
                + "Target Con. save or stunned until end of next turn.")),
        KI_EMPOWERED_STRIKES(ability()
            .withDescription("Unarmed strikes count as magical.")),
        STILLNESS_OF_MIND(ability()
            .withDescription("Use action to end charm or fear effect.")),
        PURITY_OF_BODY(ability()
            .withDescription("Immune to disease and poison.")),
        TONGUE_OF_THE_SUN_AND_MOON(ability()
            .withDescription("Understand all spoken languages.")),
        DIAMOND_SOUL(ability()
            .withDescription("Proficiency in all saves.")),
        TIMELESS_BODY(ability()
            .withDescription("Cannot be magically aged. Need no food or water.")),
        EMPTY_BODY(ability()
            .withDescription("Spend 4 Ki points to become invisible for 1 minute.")),
        PERFECT_SELF(ability()
            .withDescription("Regain 4 Ki points on initiative if none remaining.")),
        OPEN_HAND_TECHNIQUE(ability()
            .withDescription("When hitting a creature with Flurry of Blows, impose one effect: "
                + "Dex. save or knocked prone; Str save or push 15'; no reactons until end of next turn.")),
        WHOLENESS_OF_BODY(ability()
            .withDescription("As an action, regain [$level*3]HP once between each long rest.")),
        TRANQUILITY(ability()
            .withDescription("At the end of a long rest, gain sanctuary. "
                + "Attacker must make Wis. save DC[8+$wis_mod+$prof] or target another. "
                + "Attacking ends the effect.")),
        QUIVERING_PALM(ability()
            .withDescription("Spend 3 Ki points to start vibrations that last for [$level] days. "
                + "At end, Con. save or reduce to 0HP. Succeed on save 10d10 necrotic dam.")),
        SHADOW_STEP(ability()
            .withDescription("When in dim light or darkness, as a bonus action teleport up to 60'.")),
        CLOAK_OF_SHADOWS(ability()
            .withDescription("When in dim light or darkness, as an action become invisible.")),
        OPPORTUNIST(ability()
            .withDescription("As a reaction, when a creature within 5' that is hit, make a melee attack.")),
        DISCIPLE_OF_THE_ELEMENTS(ability()
            .withDescription("Spend up to [max($level 3:2,5:3,9:4,13:5,17:7)] Ki points to cast an elemental spell.")),;

        private final AttributeDelegate delegate;

        private Ability(AttributeDelegate delegate) {
            this.delegate = delegate;
        }

        @Override
        public AttributeType getType() {
            return AttributeType.MONK_ABILITY;
        }
    }

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.MONASTIC_TRADITION;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.STRENGTH, AttributeType.DEXTERITY);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.DEXTERITY, AttributeType.WISDOM).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        gen.level(1).addWeaponProficiencies(SHORTSWORD);
        gen.level(1).addAttributes(ALL_SIMPLE_WEAPONS);
        gen.level(1).addChoice(new AttributeChoice("Tools",
            Stream.concat(
                MusicalInstrument.getAllProficiencies(),
                Proficiency.allOfType(AttributeType.TOOLS))));
        gen.level(1).addChoice(2, new AttributeChoice("Skill", ACROBATICS, ATHLETICS, HISTORY,
            INSIGHT, RELIGION, STEALTH));
        gen.level(1).addChoice(new EquipmentChoice("Weapon").with(SHORTSWORD)
            .with(EquipmentCategory.SIMPLE_MELEE).with(EquipmentCategory.SIMPLE_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
            DUNGEONEER_PACK, EXPLORER_PACK));
        gen.level(1).addEquipment(DART, 10);
        gen.level(1).addAttributes(UNARMORED_DEFENCE, MARTIAL_ARTS);
        gen.level(2).addAttributes(KI, FLURRY_OF_BLOWS, PATIENT_DEFENCE, STEP_OF_THE_WIND,
            UNARMOURED_MOVEMENT);
        gen.level(3).addChoice(
            new AttributeChoice("Monastic Tradition", MonasticTradition.values()));
        gen.level(3).addAttributes(DEFLECT_MISSILES);
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(4).addAttributes(SLOW_FALL);
        gen.level(5).addAttributes(EXTRA_ATTACK, STUNNING_STRIKE);
        gen.level(6).addAttributes(KI_EMPOWERED_STRIKES);
        gen.level(7).addAttributes(EVASION);
        gen.level(7).addAttributes(STILLNESS_OF_MIND);
        gen.level(10).addAttributes(PURITY_OF_BODY);
        gen.level(13).addAttributes(TONGUE_OF_THE_SUN_AND_MOON);
        gen.level(14).addAttributes(DIAMOND_SOUL);
        gen.level(15).addAttributes(TIMELESS_BODY);
        gen.level(18).addAttributes(EMPTY_BODY);
        gen.level(20).addAttributes(PERFECT_SELF);
    }

    public static Ability loadAbility(Element element) {
        return Ability.valueOf(element.getTextContent());
    }
}
