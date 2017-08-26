package characterbuilder.character.characterclass.sorcerer;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Skill.*;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.sorcerer.Sorcerer.Ability.FONT_OF_MAGIC;
import static characterbuilder.character.characterclass.sorcerer.Sorcerer.Ability.SORCEROUS_RESTORATIONS;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.EquipmentChoice;
import static characterbuilder.character.equipment.AdventureGear.COMPONENT_POUCH;
import static characterbuilder.character.equipment.AdventureGear.CROSSBOW_BOLT;
import static characterbuilder.character.equipment.EquipmentCategory.ARCANE_FOCUS;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_MELEE;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_RANGED;
import static characterbuilder.character.equipment.EquipmentPack.DUNGEONEER_PACK;
import static characterbuilder.character.equipment.EquipmentPack.EXPLORER_PACK;
import characterbuilder.character.equipment.EquipmentSet;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import static characterbuilder.character.equipment.Weapon.DART;
import static characterbuilder.character.equipment.Weapon.LIGHT_CROSSBOW;
import static characterbuilder.character.equipment.Weapon.QUARTERSTAFF;
import static characterbuilder.character.equipment.Weapon.SLING;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public class Sorcerer extends AbstractCharacterClass {

    public enum Ability implements Attribute {
        FONT_OF_MAGIC(delegate()
            .withDescription("[$level] sorcery points. ")
            .withDescription("As a bonus action, convert sorcery points to spell slots. "
                + "2 1st, 3 2nd, 5 3rd, 6 4th 7 5th. ")
            .withDescription("Or convert spell slots to sorcery points. "
                + "1 sorcery point for each level. ")),
        SORCEROUS_RESTORATIONS(delegate()
            .withDescription("Regain 4 sorcery points after short rest.")),
        DRACONIC_RESILIENCE(delegate()
            .withDescription("Increase in HP and unarmoured AC [13+$dex_mod].")
            .withAction("Increase HP", ch -> {
                ch.getAttribute(AttributeType.HIT_POINTS, IntAttribute.class)
                    .addValue(ch.getLevel());
            })),
        ELEMENTAL_AFFINITY(delegate()
            .withDescription("+[$chr_mod] to one damage roll of spells dealing "
                + "[$draconic_damage] damage.")
            .withDescription("Spend 1 sorcery point to gain resistance "
                + "to [$draconic_damage] for 1 hour.")),
        DRAGON_WINGS(delegate()
            .withDescription("As a bonus action, sprout or dismiss dragon wings "
                + "if not wearing armour.")
            .withDescription("Gain flying speed equal to current speed.")),
        DRACONIC_PRESENCE(delegate()
            .withDescription("As an action, spend 5 sorcery points to exude aura of awe or fear "
                + "to 60 feet for 1 minute. Creatures in aura are charmed or frightened. Wis save.")),
        WILD_MAGIC_SURGE(delegate()
            .withDescription("Each spell can cause a <em>Wild Magic</em> surge on a roll of 1 on d20.")),
        TIDES_OF_CHAOS(delegate()
            .withDescription("Gain advantage on one attack, ability check or save.")
            .withDescription("Use once between each long rest.")),
        BEND_LUCK(delegate()
            .withDescription("As a reaction to an attack, ability check or save, "
                + "spend 2 sorcery points to apply a d4 penalty to roll.")),
        CONTROLLED_CHAOS(delegate()
            .withDescription("On each <em>Wild Magic</em> surge, roll twice and use either number.")),
        SPELL_BOMBARDMENT(delegate()
            .withDescription("On rolling maximum damage on at least one die for a spell, "
                + "roll the same die again and add to the damage."));

        private final AttributeDelegate delegate;

        private Ability(AttributeDelegate delegate) {
            this.delegate = delegate;
        }

        @Override
        public AttributeType getType() {
            return AttributeType.SORCERER_ABILITY;
        }

        @Override
        public void generateInitialChoices(Character character) {
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
        return 6;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.SORCEROUS_ORIGIN;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.CONSTITUTION, AttributeType.CHARISMA);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        String casting = "Sorcerer";
        gen.level(1)
            .addWeaponProficiencies(DAGGER, DART, SLING, QUARTERSTAFF, LIGHT_CROSSBOW);
        gen.level(1).addChoice(new AttributeChoice("Skill", ARCANA, DECEPTION, INSIGHT,
            INTIMIDATION, PERSUASION, RELIGION));
        gen.level(1).addChoice(new EquipmentChoice("Weapon")
            .with(LIGHT_CROSSBOW, new EquipmentSet(CROSSBOW_BOLT, 20))
            .with(SIMPLE_MELEE).with(SIMPLE_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Spell Equipment")
            .with(COMPONENT_POUCH).with(ARCANE_FOCUS));
        gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
            DUNGEONEER_PACK, EXPLORER_PACK));
        gen.level(2).addAttributes(FONT_OF_MAGIC);
        gen.level(3).addChoice(2, new AttributeChoice("Metamagic", MetaMagic.values()));
        gen.level(10, 17).addChoice(new AttributeChoice("Metamagic", MetaMagic.values()));
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(20).addAttributes(SORCEROUS_RESTORATIONS);

        gen.level(1)
            .addSpellCasting(casting, CHARISMA, CharacterClass.SORCERER, "All")
            .addSpellSlots(casting, 1, 2).addKnownSpells(casting, 2);
        gen.level(2, 3).addSpellSlots(casting, 1, 1);
        gen.level(3).addSpellSlots(casting, 2, 2);
        gen.level(4).addSpellSlots(casting, 2, 1);
        gen.level(5).addSpellSlots(casting, 3, 2);
        gen.level(6).addSpellSlots(casting, 3, 1);
        gen.level(7, 8, 9).addSpellSlots(casting, 4, 1);
        gen.level(9, 10, 18).addSpellSlots(casting, 5, 1);
        gen.level(11, 19).addSpellSlots(casting, 6, 1);
        gen.level(13, 20).addSpellSlots(casting, 7, 1);
        gen.level(15).addSpellSlots(casting, 8, 1);
        gen.level(17).addSpellSlots(casting, 9, 1);
        gen.cond(ch -> ch.getLevel() > 1).replaceSpell(casting);
        gen.level(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17).addKnownSpells(casting, 1);
    }
}
