package characterbuilder.character.characterclass.bard;

import static characterbuilder.character.ability.Ability.BARDIC_INSPIRATION;
import static characterbuilder.character.ability.Ability.COUNTERCHARM;
import static characterbuilder.character.ability.Ability.FONT_OF_INSPIRATION;
import static characterbuilder.character.ability.Ability.JACK_OF_ALL_TRADES;
import static characterbuilder.character.ability.Ability.SONG_OF_REST;
import static characterbuilder.character.ability.Ability.SUPERIOR_INSPIRATION;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.ALL_SIMPLE_WEAPONS;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.CHARISMA;
import static characterbuilder.character.characterclass.CharacterClass.BARD;
import characterbuilder.character.characterclass.AbstractCharacterClass;
import characterbuilder.character.choice.AbilityScoreOrFeatIncrease;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import static characterbuilder.character.choice.ChoiceGenerator.cantripChoice;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.EquipmentChoice;
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
import java.util.stream.Stream;

public class Bard extends AbstractCharacterClass {

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
    protected void makeGenerator(ChoiceGenerator gen) {
        gen.level(1).addSpellCasting("Bard", CHARISMA, BARD, "All");
        gen.level(1).addChoice(cantripChoice(2, CHARISMA));
        gen.level(4, 10).addChoice(cantripChoice(1, CHARISMA));
        gen.level(1).addAttributes(Proficiency.LIGHT_ARMOUR, ALL_SIMPLE_WEAPONS);
        gen.level(1).addWeaponProficiencies(HAND_CROSSBOW, LONGSWORD, RAPIER, SHORTSWORD);
        gen.level(1).addChoice(3, new AttributeChoice("Skill", Skill.values()));
        gen.level(1).addChoice(new EquipmentChoice("Weapon", RAPIER, LONGSWORD)
            .with(EquipmentCategory.SIMPLE_MELEE).with(EquipmentCategory.SIMPLE_RANGED));
        gen.level(1).addChoice(new EquipmentChoice("Adventure Pack",
            EquipmentPack.DIPLOMAT_PACK, EquipmentPack.ENTERTAINER_PACK));
        gen.level(1).addEquipment(LEATHER_ARMOUR, DAGGER);
        gen.level(1).addChoice(3, new AttributeChoice("Musical Instrument Proficiency",
            MusicalInstrument.getAllProficiencies()));
        gen.level(1).addChoice(new EquipmentChoice("Musical Instrument")
            .with(EquipmentCategory.MUSICAL_INSTRUMENT));
        gen.level(1).addAttributes(BARDIC_INSPIRATION);
        gen.level(2).addAttributes(JACK_OF_ALL_TRADES);
        gen.level(2).addAttributes(SONG_OF_REST);
        gen.level(3).addChoice(new AttributeChoice("Bard College", BardicCollege.values()));
        gen.level(3, 10).addChoice(2, new ExpertiseChoice());
        gen.level(4, 8, 12, 16, 19).addChoice(2, new AbilityScoreOrFeatIncrease());
        gen.level(5).addAttributes(FONT_OF_INSPIRATION);
        gen.level(6).addAttributes(COUNTERCHARM);
        gen.level(20).addAttributes(SUPERIOR_INSPIRATION);
        gen.level(1).addSpellSlots("Bard", 1, 2);
        gen.level(2, 3).addSpellSlots("Bard", 1, 1);
        gen.level(3).addSpellSlots("Bard", 2, 2);
        gen.level(4).addSpellSlots("Bard", 2, 1);
        gen.level(5).addSpellSlots("Bard", 3, 2);
        gen.level(6).addSpellSlots("Bard", 3, 1);
        gen.level(7, 8, 9).addSpellSlots("Bard", 4, 1);
        gen.level(9, 10).addSpellSlots("Bard", 5, 1);
        gen.level(11, 19).addSpellSlots("Bard", 6, 1);
        gen.level(13, 12).addSpellSlots("Bard", 7, 1);
        gen.level(15).addSpellSlots("Bard", 8, 1);
        gen.level(17).addSpellSlots("Bard", 9, 1);

        gen.cond(ch -> ch.getLevel() > 1).replaceSpell("Bard");
        gen.level(1).addKnownSpells("Bard", 4);
        gen.level(2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 15, 17).addKnownSpells("Bard", 1);
        gen.level(10, 14, 18)
            .addChoice(2, new OptionChoice("Magical Secrets") {
                @Override
                public void select(characterbuilder.character.Character character,
                    ChoiceSelector selector) {
                    SpellCasting casting = character.getSpellCasting("Bard");
                    selector.chooseOption(Arrays.stream(Spell.values())
                        .filter(spell -> !casting.hasLearntSpell(spell))
                        .filter(spell -> spell.getLevel() <= casting.getMaxSlot()), spell -> {
                        if (spell.isCantrip()) {
                            character.addAttribute(new SpellAbility(spell, CHARISMA));
                        } else {
                            casting.addKnownSpells(1);
                            casting.addLearntSpell(spell);
                        }
                    });
                }
            });
    }
}
