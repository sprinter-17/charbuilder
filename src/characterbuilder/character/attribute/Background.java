package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Proficiency;
import static characterbuilder.character.ability.Proficiency.*;
import static characterbuilder.character.ability.Skill.*;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.EquipmentChoice;
import characterbuilder.character.equipment.AdventureGear;
import static characterbuilder.character.equipment.AdventureGear.*;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.MusicalInstrument;
import characterbuilder.character.equipment.Token;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum Background implements Attribute {
    ACOLYTE(gen
        -> gen.addAttributes(INSIGHT, RELIGION)
            .addEquipment(GOLD_PIECE, 15)
            .addEquipment(INCENSE_BLOCK, 5)
            .addChoice(new EquipmentChoice(EquipmentCategory.HOLY_SYMBOL))
            .addChoice(2, Proficiency.choose(LANGUAGE))),
    CHARLATAN(gen
        -> gen.addAttributes(DECEPTION, SLEIGHT_OF_HAND)
            .addAttributes(Proficiency.DISGUISE_KIT, Proficiency.FORGERY_KIT)
            .addEquipment(FINE_CLOTHES, AdventureGear.DISGUISE_KIT)
            .addChoice(new EquipmentChoice("Tools of the Con",
                new Token("Bottles of coloured liquid"),
                new Token("Set of Weighted Dice"),
                new Token("Deck of Marked Cards"),
                new Token("Signet Ring of Imaginary Duke")))
            .addEquipment(GOLD_PIECE, 15)
            .addAttributes(BackgroundAbility.FALSE_IDENTITY)),
    CRIMINAL(gen
        -> gen.addAttributes(DECEPTION, STEALTH, Proficiency.THIEVES_TOOLS)
            .addEquipment(AdventureGear.GOLD_PIECE, 15)
            .addEquipment(CROWBAR, COMMON_CLOTHES)
            .addChoice(Proficiency.choose(LANGUAGE))),
    ENTERTAINER(gen
        -> gen.addAttributes(ACROBATICS, PERFORMANCE, BackgroundAbility.BY_POPULAR_DEMAND)
            .addAttributes(Proficiency.DISGUISE_KIT)
            .addChoice(new AttributeChoice("Musical Instrument Proficiency",
                MusicalInstrument.getAllProficiencies()))
            .addChoice(new EquipmentChoice(EquipmentCategory.MUSICAL_INSTRUMENT))
            .addEquipment(GOLD_PIECE, 15)
            .addTokens("the favour of an admirer.")
            .addEquipment(COSTUME)
            .addChoice(3, Proficiency.choose(ENTERTAINER_ROUTINE))),
    FOLK_HERO(gen
        -> gen.addAttributes(ANIMAL_HANDLING, SURVIVAL, LAND_VEHICLES)
            .addEquipment(AdventureGear.GOLD_PIECE, 10)
            .addChoice(Proficiency.choose(ARTISAN))),
    GUILD_ARTISAN(gen -> {
        gen.addAttributes(INSIGHT, PERSUASION);
        gen.addChoice(Proficiency.choose(ARTISAN));
        gen.addChoice(Proficiency.choose(LANGUAGE));
        gen.addChoice(new EquipmentChoice(EquipmentCategory.TOOL));
        gen.addTokens("Letter of introduction from guild");
        gen.addEquipment(TRAVELERS_CLOTHES);
        gen.addEquipment(GOLD_PIECE, 15);
        gen.addChoice(Proficiency.choose(GUILD_BUSINESS));
        gen.addAttributes(BackgroundAbility.GUILD_MEMBERSHIP);
    }),
    HERMIT(gen -> {
        gen.addAttributes(MEDICINE, RELIGION, Proficiency.HERBALISM_KIT);
        gen.addChoice(Proficiency.choose(LANGUAGE));
        gen.addEquipment(GOLD_PIECE, 5);
        gen.addEquipment(BLANKET, COMMON_CLOTHES, AdventureGear.HERBALISM_KIT);
        gen.addTokens("Notes of prayers");
        gen.addAttributes(BackgroundAbility.DISCOVERY);
    }),
    NOBLE(gen
        -> gen
            .addAttributes(PERSUASION, HISTORY)
            .addEquipment(new EquipmentSet(AdventureGear.GOLD_PIECE, 25))
            .addChoice(Proficiency.choose(LANGUAGE))
            .addChoice(new AttributeChoice("Gaming skill", CARDS, DICE))),
    OUTLANDER(gen -> {
        gen.addAttributes(ATHLETICS, SURVIVAL);
        gen.addChoice(new AttributeChoice("Musical Instrument Proficiency",
            MusicalInstrument.getAllProficiencies()));
        gen.addChoice(Proficiency.choose(LANGUAGE));
        gen.addEquipment(AdventureGear.STAFF, AdventureGear.TRAVELERS_CLOTHES);
        gen.addTokens("Hunting trap", "Trophy from animal");
        gen.addEquipment(AdventureGear.GOLD_PIECE, 10);
        gen.addAttributes(BackgroundAbility.WANDERER);
    }),
    SAGE(gen -> {
        gen.addAttributes(ARCANA, HISTORY)
            .addEquipment(AdventureGear.GOLD_PIECE, 10)
            .addChoice(2, Proficiency.choose(LANGUAGE));
        gen.addTokens("Bottle of black ink", "Quill", "Letter with unanswered question");
        gen.addEquipment(AdventureGear.COMMON_CLOTHES);
        gen.addAttributes(BackgroundAbility.RESEARCHER);
    }),
    SAILOR(gen -> {
        gen.addAttributes(ATHLETICS, PERCEPTION, Proficiency.NAVIGATORS_TOOLS, WATER_VEHICLES);
        gen.addEquipment(AdventureGear.GOLD_PIECE, 10);
        gen.addEquipment(new Token("Belaying pin"), AdventureGear.ROPE_SILK,
            new Token("Luck charm"), AdventureGear.COMMON_CLOTHES);
        gen.addAttributes(BackgroundAbility.SHIPS_PASSAGE);
    }),
    SOLDIER(gen -> {
        gen.addAttributes(ATHLETICS, INTIMIDATION, LAND_VEHICLES);
        gen.addEquipment(AdventureGear.GOLD_PIECE, 10);
        gen.addEquipment(new Token("Insignia of rank"), new Token("Trophy from fallen enemy"));
        gen.addEquipment(AdventureGear.COMMON_CLOTHES);
        gen.addChoice(new EquipmentChoice(
            "Game", AdventureGear.BONE_DICE, AdventureGear.DECK_PLAYING_CARDS));
        gen.addChoice(new AttributeChoice("Gaming skill", CARDS, DICE));
    }),
    URCHIN(gen -> {
        gen.addAttributes(SLEIGHT_OF_HAND, STEALTH,
            Proficiency.THIEVES_TOOLS, Proficiency.DISGUISE_KIT);
        gen.addEquipment(AdventureGear.KNIFE_SMALL,
            new Token("Map of city"), new Token("Pet mouse"), new Token("Momento of parents"));
        gen.addEquipment(COMMON_CLOTHES);
        gen.addEquipment(AdventureGear.GOLD_PIECE, 10);
        gen.addAttributes(BackgroundAbility.CITY_SECRETS);
    });

    public enum BackgroundAbility implements Attribute {
        CITY_SECRETS(ability()
            .withDescription("When not in combat, travel at double speed between locations in city.")),
        SHIPS_PASSAGE(ability()
            .withName("Ship's Passage")
            .withDescription("Can secure free passage on sailing ships.")),
        RESEARCHER(ability()
            .withDescription("Can attempt to obtain a piece of lore.")),
        WANDERER(ability()
            .withDescription("Excellent memory for maps and geography. ")
            .withDescription("Can find food if available.")),
        DISCOVERY(ability()
            .withDescription("Have made a unique and powerful discovery.")),
        BY_POPULAR_DEMAND(ability()
            .withDescription("Find a place to perform is each town, receiving free lodging.")),
        FALSE_IDENTITY(ability()
            .withDescription("Can assume persona of second identity. ")
            .withDescription("Can forge official papers and letters if samples are available. ")),
        GUILD_MEMBERSHIP(ability()
            .withDescription("Guild will provide lodging and food. ")
            .withDescription("Must pay dues of 5GP each month.")),;

        private final AttributeDelegate delegate;

        private static AttributeDelegate ability() {
            return new AttributeDelegate();
        }

        private BackgroundAbility(AttributeDelegate delegate) {
            this.delegate = delegate;
        }

        @Override
        public AttributeType getType() {
            return BACKGROUND_FEATURE;
        }

        @Override
        public String toString() {
            return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
        }

    }

    private final ChoiceGenerator generator = new ChoiceGenerator();

    private Background(Consumer<ChoiceGenerator> generatorConsumer) {
        generatorConsumer.accept(generator);
    }

    @Override
    public void generateInitialChoices(Character character) {
        generator.generateChoices(character);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.BACKGROUND;
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return generator.getDescription(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static Background load(Node node) {
        return Background.valueOf(node.getTextContent());
    }
}
