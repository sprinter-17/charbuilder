package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Proficiency.*;
import static characterbuilder.character.ability.Skill.*;
import static characterbuilder.character.attribute.AttributeType.LANGUAGE;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.EquipmentType;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;

public enum Background implements Attribute {
    ACOLYTE(gen
        -> gen.addAttributes(INSIGHT, RELIGION)
            .addEquipment(EquipmentType.GOLD_PIECE, 15)
            .addEquipment(EquipmentType.INCENSE_BLOCK, 5)
            .addChoice(new EquipmentChoice(EquipmentCategory.HOLY_SYMBOL))
            .addChoice(new AttributeChoice(LANGUAGE).withCount(2))),
    CRIMINAL(gen
        -> gen.addAttributes(DECEPTION, STEALTH, THIEVES_TOOLS)
            .addEquipment(EquipmentType.GOLD_PIECE, 15)
            .addEquipment(EquipmentType.CROWBAR)
            .addEquipment(EquipmentType.COMMON_CLOTHES)
            .addChoice(new AttributeChoice(LANGUAGE))),
    FOLK_HERO(gen
        -> gen.addAttributes(ANIMAL_HANDLING, SURVIVAL, LAND_VEHICLES)
            .addEquipment(EquipmentType.GOLD_PIECE, 10)
            .addChoice(new AttributeChoice(AttributeType.ARTISAN))),
    NOBLE(gen
        -> gen
            .addAttributes(PERSUASION, HISTORY)
            .addEquipment(new EquipmentSet(EquipmentType.GOLD_PIECE, 25))
            .addChoice(new AttributeChoice(LANGUAGE))
            .addChoice(new AttributeChoice("Gaming skill", CARDS, DICE))),
    SAGE(gen
        -> gen.addAttributes(ARCANA, HISTORY)
            .addEquipment(EquipmentType.GOLD_PIECE, 10)
            .addChoice(new AttributeChoice(LANGUAGE).withCount(2))),
    SOLDIER(gen
        -> gen.addAttributes(ATHLETICS, INTIMIDATION, LAND_VEHICLES)
            .addEquipment(EquipmentType.GOLD_PIECE, 10)
            .addEquipment(EquipmentType.RANK_INSIGNIA)
            .addEquipment(EquipmentType.COMMON_CLOTHES)
            .addChoice(new EquipmentChoice(
                "Game", EquipmentType.BONE_DICE, EquipmentType.DECK_PLAYING_CARDS))
            .addChoice(new AttributeChoice("Gaming skill", CARDS, DICE)));

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
