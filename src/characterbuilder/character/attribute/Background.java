package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.*;
import static characterbuilder.character.ability.Skill.*;
import static characterbuilder.character.attribute.AttributeType.ARTISAN;
import static characterbuilder.character.attribute.AttributeType.LANGUAGE;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.EquipmentType;
import static characterbuilder.character.equipment.EquipmentType.*;
import characterbuilder.character.equipment.MusicalInstrument;
import characterbuilder.character.equipment.Token;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;

public enum Background implements Attribute {
    ACOLYTE(gen
        -> gen.addAttributes(INSIGHT, RELIGION)
            .addEquipment(GOLD_PIECE, 15)
            .addEquipment(INCENSE_BLOCK, 5)
            .addChoice(new EquipmentChoice(EquipmentCategory.HOLY_SYMBOL))
            .addChoice(new AttributeChoice(LANGUAGE).withCount(2))),
    CHARLATAN(gen -> {

    }),
    CRIMINAL(gen
        -> gen.addAttributes(DECEPTION, STEALTH, Proficiency.THIEVES_TOOLS)
            .addEquipment(EquipmentType.GOLD_PIECE, 15)
            .addEquipment(CROWBAR, COMMON_CLOTHES)
            .addChoice(new AttributeChoice(LANGUAGE))),
    ENTERTAINER(gen -> {

    }),
    FOLK_HERO(gen
        -> gen.addAttributes(ANIMAL_HANDLING, SURVIVAL, LAND_VEHICLES)
            .addEquipment(EquipmentType.GOLD_PIECE, 10)
            .addChoice(new AttributeChoice(AttributeType.ARTISAN))),
    GUILD_ARTISAN(gen -> {
        gen.addAttributes(INSIGHT, PERSUASION);
        gen.addChoice(new AttributeChoice(ARTISAN));
        gen.addChoice(new AttributeChoice(LANGUAGE));
        gen.addChoice(new EquipmentChoice(EquipmentCategory.TOOLS));
        gen.addTokens("Letter of introduction from guild");
        gen.addEquipment(TRAVELERS_CLOTHES);
        gen.addEquipment(GOLD_PIECE, 15);
    }),
    HERMIT(gen -> {
        gen.addAttributes(MEDICINE, RELIGION, Proficiency.HERBALISM_KIT);
        gen.addChoice(new AttributeChoice(LANGUAGE));
        gen.addEquipment(GOLD_PIECE, 5);
        gen.addEquipment(BLANKET, COMMON_CLOTHES, EquipmentType.HERBALISM_KIT);
        gen.addTokens("Notes of prayers");
        gen.addAttributes(Ability.DISCOVERY);
    }),
    NOBLE(gen
        -> gen
            .addAttributes(PERSUASION, HISTORY)
            .addEquipment(new EquipmentSet(EquipmentType.GOLD_PIECE, 25))
            .addChoice(new AttributeChoice(LANGUAGE))
            .addChoice(new AttributeChoice("Gaming skill", CARDS, DICE))),
    OUTLANDER(gen -> {
        gen.addAttributes(ATHLETICS, SURVIVAL);
        gen.addChoice(new AttributeChoice("Musical Instrument Proficiency",
            MusicalInstrument.getAllProficiencies()));
        gen.addChoice(new AttributeChoice(LANGUAGE));
        gen.addEquipment(EquipmentType.STAFF, EquipmentType.TRAVELERS_CLOTHES);
        gen.addTokens("Hunting trap", "Trophy from animal");
        gen.addEquipment(EquipmentType.GOLD_PIECE, 10);
        gen.addAttributes(Ability.WANDERER);
    }),
    SAGE(gen -> {
        gen.addAttributes(ARCANA, HISTORY)
            .addEquipment(EquipmentType.GOLD_PIECE, 10)
            .addChoice(new AttributeChoice(LANGUAGE).withCount(2));
        gen.addTokens("Bottle of black ink", "Quill", "Letter with unanswered question");
        gen.addEquipment(EquipmentType.COMMON_CLOTHES);
        gen.addAttributes(Ability.RESEARCHER);
    }),
    SAILOR(gen -> {
        gen.addAttributes(ATHLETICS, PERCEPTION, Proficiency.NAVIGATORS_TOOLS, WATER_VEHICLES);
        gen.addEquipment(EquipmentType.GOLD_PIECE, 10);
        gen.addEquipment(new Token("Belaying pin"), EquipmentType.ROPE_SILK,
            new Token("Luck charm"), EquipmentType.COMMON_CLOTHES);
        gen.addAttributes(Ability.SHIPS_PASSAGE);
    }),
    SOLDIER(gen -> {
        gen.addAttributes(ATHLETICS, INTIMIDATION, LAND_VEHICLES);
        gen.addEquipment(EquipmentType.GOLD_PIECE, 10);
        gen.addEquipment(new Token("Insignia of rank"), new Token("Trophy from fallen enemy"));
        gen.addEquipment(EquipmentType.COMMON_CLOTHES);
        gen.addChoice(new EquipmentChoice(
            "Game", EquipmentType.BONE_DICE, EquipmentType.DECK_PLAYING_CARDS));
        gen.addChoice(new AttributeChoice("Gaming skill", CARDS, DICE));
    }),
    URCHIN(gen -> {
        gen.addAttributes(SLEIGHT_OF_HAND, STEALTH,
            Proficiency.THIEVES_TOOLS, Proficiency.DISGUISE_KIT);
        gen.addEquipment(EquipmentType.KNIFE_SMALL,
            new Token("Map of city"), new Token("Pet mouse"), new Token("Momento of parents"));
        gen.addEquipment(EquipmentType.GOLD_PIECE, 10);
        gen.addAttributes(Ability.CITY_SECRETS);
    });

    private final ChoiceGenerator generator = new ChoiceGenerator();

    private Background(Consumer<ChoiceGenerator> generatorConsumer) {
        generatorConsumer.accept(generator);
    }

    public void addChoices(Character character) {
        generator.generateChoices(character);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.BACKGROUND;
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }
}
