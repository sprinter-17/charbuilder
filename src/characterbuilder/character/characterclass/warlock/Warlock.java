package characterbuilder.character.characterclass.warlock;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.warlock.MysticArcanum.chooseArcanum;
import static characterbuilder.character.characterclass.warlock.Warlock.Ability.ELDRITCH_MASTER;
import static characterbuilder.character.characterclass.warlock.Warlock.Ability.PACT_OF_THE_CHAIN;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.equipment.AdventureGear;
import static characterbuilder.character.equipment.AdventureGear.ARROW;
import static characterbuilder.character.equipment.AdventureGear.COMPONENT_POUCH;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import static characterbuilder.character.equipment.EquipmentCategory.ARCANE_FOCUS;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_MELEE;
import static characterbuilder.character.equipment.EquipmentCategory.SIMPLE_RANGED;
import characterbuilder.character.equipment.EquipmentPack;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.Weapon;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import characterbuilder.character.spell.Spell;
import static characterbuilder.character.spell.Spell.getSpellsAtLevel;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public class Warlock extends AbstractCharacterClass {

    public enum Ability implements Attribute {

        PACT_OF_THE_CHAIN(ability()
            .withDescription("Familiar can be imp, pseudodragon, quasit or sprite.")
            .withDescription("Forgo an attack to allow familiar to make attack as reaction.")
            .withSpellAbility(Spell.FIND_FAMILIAR, CHARISMA)),
        PACT_OF_THE_BLADE(ability()
            .withDescription("As an action, create a pact weapon in any form.")
            .withDescription("Gain proficiency with pact weapon.")),
        PACT_OF_THE_TOME(ability()
            .withDescription("Cast cantrips from Book of Shadows.")
            .withEquipment(AdventureGear.BOOK_OF_SHADOWS)
            .withChoice(cantripChoice(3, "Book of Shadow Cantrips", CHARISMA, getSpellsAtLevel(0)))),
        ELDRITCH_MASTER(ability()
            .withDescription("Spend 1 minute to regain all spell slots once between each long rest.")),
        FEY_PRESENCE(ability()
            .withDescription("As an action, charm or frighten creatures within a 10-foot cube "
                + "around Warlock for 1 turn. Wis. save.")),
        MISTY_ESCAPE(ability()
            .withDescription("As a reaction to taking damage, turn invisible and teleport up to 60 feet.")),
        BEGUILING_DEFENSES(ability()
            .withDescription("Immune to charm. ")
            .withDescription("As a reaction to a charm attempt, Wis. save or target charmed for 1 minute.")),
        DARK_DELERIUM(ability()
            .withDescription("As a reaction, charm or frighten one creature within 60 feet for 1 minute. "
                + "Wis. save.")),
        DARK_ONES_BLESSING(ability()
            .withDescription("On reducing enemy to 0 HP, gain [$chr_mod + $level] temporary HP.")),
        DARK_ONES_OWN_LUCK(ability()
            .withDescription("Add 1d10 to 1 ability check or save.")
            .withDescription("Use once between each long rest.")),
        FIENDISH_RESILIENCE(ability()
            .withDescription("Resistance to a chosen damage type.")),
        HURL_THROUGH_HELL(ability()
            .withDescription("On hitting an enemy, send away for 1 turn and cause 10d10 phychic damage.")),
        AWAKENED_MIND(ability()
            .withDescription("Communicate telepathically with any creature within 30 feet.")),
        ENTROPIC_WARD(ability()
            .withDescription("As a reaction, impose disadvantage on an attack roll.")
            .withDescription("If the attack misses, next attack against target has advantage.")
            .withDescription("Use once between each rest.")),
        THOUGHT_SHIELD(ability()
            .withDescription("Immune to telepathy.")
            .withDescription("Resistance to psychic damage.")
            .withDescription("Creature dealing psychic attack takes equal damage.")),
        CREATE_THRALL(ability()
            .withDescription("As an action, charm an incapacitated humanoid with touch."));

        private final AttributeDelegate delegate;

        private Ability(AttributeDelegate delegate) {
            this.delegate = delegate;
        }

        @Override
        public AttributeType getType() {
            return AttributeType.WARLOCK_ABILITY;
        }

        @Override
        public void generateInitialChoices(Character character) {
            delegate.generateChoices(character);
        }

        @Override
        public Stream<String> getDescription(Character character) {
            return delegate.getDescription(character);
        }
    }

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.OTHERWORLDLY_PATRON;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.WISDOM, AttributeType.CHARISMA);
    }

    @Override
    public boolean hasSavingsThrow(AttributeType type) {
        return Stream.of(AttributeType.CONSTITUTION, AttributeType.CHARISMA).anyMatch(type::equals);
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        final String casting = "Warlock";
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, Proficiency.ALL_SIMPLE_WEAPONS);
        gen.level(1).addChoice(2, new AttributeChoice("Skill", Skill.ARCANA, Skill.DECEPTION,
            Skill.HISTORY, Skill.INTIMIDATION, Skill.INVESTIGATION, Skill.NATURE, Skill.RELIGION));
        gen.level(1).addChoice(new EquipmentChoice("Primary Weapon")
            .with(Weapon.LIGHT_CROSSBOW, new EquipmentSet(ARROW, 20))
            .with(SIMPLE_MELEE).with(SIMPLE_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Secondary Weapon")
            .with(SIMPLE_MELEE).with(SIMPLE_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Magical Equipment")
            .with(COMPONENT_POUCH).with(ARCANE_FOCUS));
        gen.level(1).addChoice(new EquipmentChoice("Adventurer's Pack",
            EquipmentPack.SCHOLAR_PACK, EquipmentPack.DUNGEONEER_PACK));
        gen.level(1).addEquipment(LEATHER_ARMOUR, new EquipmentSet(DAGGER, 2));
        gen.level(1)
            .addChoice(new AttributeChoice("Otherworldy Patron", OtherwordlyPatron.values()));
        gen.level(2).addChoice(EldritchInvocation.getChoice(2));
        gen.cond(ch -> ch.getLevel() > 2).addChoice(EldritchInvocation.getReplacement());
        gen.level(5, 7, 9, 12, 15, 18).addChoice(EldritchInvocation.getChoice(1));

        gen.level(3).addChoice(new AttributeChoice("Pact Boon",
            PACT_OF_THE_CHAIN, Ability.PACT_OF_THE_BLADE, Ability.PACT_OF_THE_TOME));
        gen.level(4, 8, 12, 16, 19).addChoice(new AbilityScoreOrFeatIncrease());

        gen.level(11).addChoice(chooseArcanum(CharacterClass.WARLOCK, 6));
        gen.level(13).addChoice(chooseArcanum(CharacterClass.WARLOCK, 7));
        gen.level(15).addChoice(chooseArcanum(CharacterClass.WARLOCK, 8));
        gen.level(17).addChoice(chooseArcanum(CharacterClass.WARLOCK, 9));

        gen.level(20).addAttributes(ELDRITCH_MASTER);

        gen.level(1).addChoice(cantripChoice(2, CHARISMA));
        gen.level(4, 10).addChoice(cantripChoice(1, CHARISMA));
        gen.level(1).addSpellCasting(casting, CHARISMA, CharacterClass.WARLOCK, "All")
            .setSpellSlots(casting, 1, 1).addKnownSpells(casting, 2);
        gen.level(2).setSpellSlots(casting, 1, 2);
        gen.level(3).setSpellSlots(casting, 2, 2);
        gen.level(5).setSpellSlots(casting, 3, 2);
        gen.level(7).setSpellSlots(casting, 4, 2);
        gen.level(9).setSpellSlots(casting, 5, 2);
        gen.level(11).setSpellSlots(casting, 5, 3);
        gen.level(17).setSpellSlots(casting, 5, 4);
        gen.cond(ch -> ch.getLevel() > 1).replaceSpell(casting);
        gen.level(2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 15, 17, 19).addKnownSpells(casting, 1);
    }

    public static Ability loadAbility(Element element) {
        return Ability.valueOf(element.getTextContent());
    }
}
