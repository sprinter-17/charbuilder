package characterbuilder.character.characterclass.bard;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Feat;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_SIMPLE_WEAPONS;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import static characterbuilder.character.characterclass.CharacterClass.BARD;
import static characterbuilder.character.characterclass.bard.BardAbility.*;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.ExpertiseChoice;
import characterbuilder.character.choice.OptionChoice;
import static characterbuilder.character.equipment.Armour.LEATHER_ARMOUR;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.equipment.EquipmentPack;
import characterbuilder.character.equipment.MusicalInstrument;
import static characterbuilder.character.equipment.Weapon.DAGGER;
import static characterbuilder.character.equipment.Weapon.HAND_CROSSBOW;
import static characterbuilder.character.equipment.Weapon.LONGSWORD;
import static characterbuilder.character.equipment.Weapon.RAPIER;
import static characterbuilder.character.equipment.Weapon.SHORTSWORD;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import characterbuilder.character.spell.SpellCasting;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Bard extends AbstractCharacterClass {

    private class MagicalSecret extends OptionChoice {

        public MagicalSecret() {
            super("Magical Secret");
        }

        @Override
        public void select(Character character, ChoiceSelector selector) {
            SpellCasting casting = character.getSpellCasting(CASTING_NAME);
            selector.chooseOption(Arrays.stream(Spell.values())
                .filter(spell -> !casting.hasLearntSpell(spell))
                .filter(spell -> spell.getLevel() <= casting.getMaxSpellLevel()), spell -> {
                if (spell.isCantrip()) {
                    character.addAttribute(new SpellAbility(spell, CHARISMA));
                } else {
                    casting.addKnownSpells(1);
                    casting.addPreparedSpell(spell);
                }
            });
        }
    }

    private static final String CASTING_NAME = "Bard";

    @Override
    public int getHitDie() {
        return 8;
    }

    @Override
    public AttributeType getClassAttribute() {
        return AttributeType.BARDIC_COLLEGE;
    }

    @Override
    public Stream<AttributeType> getPrimaryAttributes() {
        return Stream.of(AttributeType.DEXTERITY, AttributeType.CHARISMA);
    }

    @Override
    public Predicate<Character> getMulticlassPrerequisites() {
        return ch -> ch.getScore(CHARISMA).getValue() >= 13;
    }

    @Override
    protected void makeGenerator(ChoiceGenerator gen) {
        addAbilities(gen);
        addEquipment(gen.initialClass());
        addCantrips(gen);
        addSpellCasting(gen);
    }

    private void addAbilities(ChoiceGenerator gen) {
        gen.initialClass().addAttributes(ALL_SIMPLE_WEAPONS)
            .addAttributeChoice(2, "Skill", Skill.values())
            .addAttributeChoice(2, "Musical Instrument Proficiency",
                MusicalInstrument.getAllProficiencies())
            .initialClass().addWeaponProficiencies(HAND_CROSSBOW, LONGSWORD, RAPIER, SHORTSWORD);
        gen.level(1)
            .addAttributes(Proficiency.LIGHT_ARMOUR)
            .addAttributeChoice(1, "Skill", Skill.values())
            .addAttributeChoice(1, "Musical Instrument Proficiency",
                MusicalInstrument.getAllProficiencies());
        gen.level(1).addAttributes(Feat.RITUAL_CASTER);
        gen.level(1).addAttributes(BARDIC_INSPIRATION);
        gen.level(2).addAttributes(JACK_OF_ALL_TRADES);
        gen.level(2).addAttributes(SONG_OF_REST);
        gen.level(3).addAttributeChoice("Bard College", BardicCollege.values());
        gen.level(3, 10).addChoice(2, new ExpertiseChoice());
        gen.level(4, 8, 12, 16, 19).addAbilityScoreOrFeatChoice();
        gen.level(5).addAttributes(FONT_OF_INSPIRATION);
        gen.level(6).addAttributes(COUNTERCHARM);
        gen.level(10, 14, 18).addChoice(2, new MagicalSecret());
        gen.level(20).addAttributes(SUPERIOR_INSPIRATION);
    }

    private void addEquipment(ChoiceGenerator gen) {
        gen.addEquipmentChoice("Weapon", RAPIER, LONGSWORD)
            .with(EquipmentCategory.SIMPLE_MELEE).with(EquipmentCategory.SIMPLE_RANGED);
        gen.addEquipmentChoice("Adventure Pack",
            EquipmentPack.DIPLOMAT_PACK, EquipmentPack.ENTERTAINER_PACK);
        gen.addEquipment(LEATHER_ARMOUR, DAGGER);
        gen.addEquipmentChoice("Musical Instrument")
            .with(EquipmentCategory.MUSICAL_INSTRUMENT);
    }

    private void addCantrips(ChoiceGenerator gen) {
        gen.level(1).addChoice(cantripChoice(2, CHARISMA));
        gen.level(4, 10).addChoice(cantripChoice(1, CHARISMA));
    }

    private void addSpellCasting(ChoiceGenerator gen) {
        gen.level(1).addSpellCasting(CASTING_NAME, CHARISMA, BARD, "All");
        gen.cond(ch -> ch.getLevel() > 1).replaceSpell(CASTING_NAME);
        gen.level(1).addKnownSpells(CASTING_NAME, 4);
        gen.level(2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 15, 17).addKnownSpells(CASTING_NAME, 1);
    }
}
