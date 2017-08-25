package characterbuilder.character.characterclass.paladin;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.EXTRA_ATTACK;
import characterbuilder.character.ability.FightingStyle;
import static characterbuilder.character.ability.Proficiency.ALL_ARMOUR;
import static characterbuilder.character.ability.Proficiency.ALL_WEAPONS;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.paladin.Paladin.Ability.*;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.equipment.Armour;
import static characterbuilder.character.equipment.Armour.CHAIN_MAIL_ARMOUR;
import static characterbuilder.character.equipment.EquipmentCategory.HOLY_SYMBOL;
import static characterbuilder.character.equipment.EquipmentCategory.MARTIAL_MELEE;
import static characterbuilder.character.equipment.EquipmentCategory.MARTIAL_RANGED;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_MELEE;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.PRIEST_PACK;
import static characterbuilder.character.equipment.Weapon.JAVELIN;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public class Paladin extends AbstractCharacterClass {

    public enum Ability implements Attribute {
        DIVINE_SMITE(delegate()
            .withDescription("On hitting with melee attack, expend 1 spell slot "
                + "to deal +1d8 radiant damage "
                + "or 2d8 to undead. +1d8 damage / extra spell slot up to 5.")),
        IMPROVED_DIVINE_SMITE(delegate()
            .withDescription("On hitting with melee attack, deal +1d8 radiant damage.")),
        AURA_OF_PROTECTION(delegate()
            .withDescription("Paladin and allies within [max($level 6:10,18:30)] "
                + "feet gain +[$chr_mod] to saves.")),
        AURA_OF_COURAGE(delegate()
            .withDescription("Paladin and allies within [max($level 6:10,18:30)] "
                + "feet cannot be frightened.")),
        CLEANSING_TOUCH(delegate()
            .withDescription("As action, end one spell on self or willing creature touched. "
                + "Use [$chr_mod] times between long rests.")),
        DIVINE_SENSE(delegate()
            .withDescription("As an action, know location of celestial, "
                + "fiend or undead within 60 feet.")),
        LAY_ON_HANDS(delegate()
            .withDescription("As an action, restore HP. "
                + "Restore a total of [$level * 5] HPs between each long rest. "
                + "Cure disease or neutralise poison requires 5 HP.")),
        DIVINE_HEALTH(delegate()
            .withDescription("Immune to disease.")),
        SACRED_WEAPON(delegate()
            .withName("Channel Divinity: Sacred Weapon")
            .withDescription("For 1 minute weapon attack +[$chr_mod] and emits bright light.")),
        TURN_THE_UNHOLY(delegate()
            .withName("Channel Divinity: Turn the Unholy")
            .withDescription("Each fiend and undead within 30 feet Wis. save or turned for 1 minute.")),
        AURA_OF_DEVOTION(delegate()
            .withDescription("Paladin and allies within [max($level 7:10,18:30)] feet cannot be charmed.")),
        PURITY_OF_SPIRIT(delegate()
            .withDescription("Aberrations, celestials, elementals, fey, fiends, "
                + "and undead have disadvantage on attacks against Paladin.")),
        HOLY_NIMBUS(delegate()
            .withDescription("As an action emanate aura of sunlight.")
            .withDescription("For 1 minute, within 30 feet bright light shines "
                + "and enemies take 10 radiant damage. Paladin has advantage on saves against "
                + "fiend and undead spells. Use once between each long rest. ")),
        NATURES_WRATH(delegate()
            .withName("Channel Divinity: Nature's Wrath")
            .withDescription("1 creature within 10 feet restrained until Str. or Dex. save.")),
        TURN_THE_FAITHLESS(delegate()
            .withDescription("Channel Divinity: Turn the Faithless"
                + "Fey and fiend within 30 feet Wis. save or turned for 1 minute.")),
        AURA_OF_WARDING(delegate()
            .withDescription("Paladin and allies within [max($level 7:10,18:30)] "
                + "feet have resistance to damage from spells.")),
        UNDYING_SENTINEL(delegate()
            .withDescription("When paladin would drop to 0 HP, drop to 1 HP.")),
        ELDER_CHAMPION(delegate()
            .withDescription("As an action, tranform into force of nature. "
                + "For 1 minute regain 10 HP each turn,"
                + "cast spells as bonus action, enemies within 10 feet have disadvantage against "
                + "paladin spells and Channel Divinity. Use once between each long rest.")),
        ABJURE_ENERGY(delegate()
            .withName("Channel Divinity: Abjure Energy")
            .withDescription("1 creature within 60 feet Wis. save or frightened for 1 minute speed 0. "
                + "On save speed halved.")),
        VOW_OF_ENMITY(delegate()
            .withName("Channel Divinity: Vow of Enmity")
            .withDescription("As a bonus action mark 1 creature within 10 feet gaining "
                + "advantage on attacks for 1 minute.")),
        RELENTLESS_AVENGER(delegate()
            .withDescription("When opportunity attack hits, move up to half speed as part "
                + "of reaction. Move does not provoke opportunity attacks.")),
        SOUL_OF_VENGEANCE(delegate()
            .withDescription("As a reaction to an attack by a creature marked by "
                + "Vow of Enmity attacks, make a melee attack.")),
        AVENGING_ANGEL(delegate()
            .withDescription("As an action, transform into angelic avenger. "
                + "For 1 hour fly at speed 60, enemies coming within 30 feet Wis. save "
                + "or frightened for 1 minute and grant advantage on attacks.")),;

        private final AttributeDelegate delegate;

        private Ability(AttributeDelegate delegate) {
            this.delegate = delegate;
        }

        @Override
        public AttributeType getType() {
            return AttributeType.PALADIN_ABILITY;
        }

        @Override
        public void generateLevelChoices(Character character) {
            delegate.generateChoices(character);
        }

        @Override
        public String toString() {
            return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
        }

        @Override
        public Stream<String> getDescription(Character character) {
            return delegate.getDescription(character);
        }

        public static Ability load(Element element) {
            return valueOf(element.getTextContent());
        }
    }

    @Override
    public int getHitDie() {
        return 10;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.SACRED_OATH;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.WISDOM, AttributeType.CHARISMA);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.STRENGTH, AttributeType.CHARISMA).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        final String casting = "Paladin";
        gen.level(1).addAttributes(ALL_ARMOUR, ALL_WEAPONS);
        gen.level(1).addChoice(2, new AttributeChoice("Skill", ATHLETICS, INSIGHT,
            INTIMIDATION, MEDICINE, PERSUASION, RELIGION));
        gen.level(1).addChoice(new EquipmentChoice("Weapon")
            .with(MARTIAL_MELEE).with(MARTIAL_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Weapon or Shield", Armour.SHIELD)
            .with(MARTIAL_MELEE).with(MARTIAL_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Secondary Weapon")
            .with(JAVELIN, 5).with(SIMPLE_MELEE));
        gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
            PRIEST_PACK, EXPLORER_PACK));
        gen.level(1).addEquipment(CHAIN_MAIL_ARMOUR);
        gen.level(1).addChoice(new EquipmentChoice(HOLY_SYMBOL));
        gen.level(1).addAttributes(DIVINE_SENSE, LAY_ON_HANDS);
        gen.level(2).addChoice(new AttributeChoice("Fighting Style", FightingStyle.values()));
        gen.level(2)
            .addSpellCasting(casting, CHARISMA, CharacterClass.PALADIN,
                "[max(1, $chr_mod + $level/2)]")
            .learnAllSpells(casting).addSpellSlots(casting, 1, 2)
            .addAttributes(DIVINE_SMITE);
        gen.level(3).addAttributes(DIVINE_HEALTH);
        gen.level(3, 5).addSpellSlots(casting, 1, 1);
        gen.level(5).addSpellSlots(casting, 2, 2);
        gen.level(7).addSpellSlots(casting, 2, 1);
        gen.level(9).addSpellSlots(casting, 3, 2);
        gen.level(13).addSpellSlots(casting, 3, 1);
        gen.level(13, 15, 17).addSpellSlots(casting, 4, 1);
        gen.level(17, 19).addSpellSlots(casting, 5, 1);
        gen.level(3).addChoice(new AttributeChoice("Sacred Oath", SacredOath.values()));
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(5).addAttributes(EXTRA_ATTACK);
        gen.level(6).addAttributes(AURA_OF_PROTECTION);
        gen.level(10).addAttributes(AURA_OF_COURAGE);
        gen.level(11).addAttributes(IMPROVED_DIVINE_SMITE);
        gen.level(14).addAttributes(CLEANSING_TOUCH);
    }
}
