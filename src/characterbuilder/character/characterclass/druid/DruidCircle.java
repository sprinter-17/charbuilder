package characterbuilder.character.characterclass.druid;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Terrain;
import static characterbuilder.character.attribute.Terrain.*;
import static characterbuilder.character.characterclass.druid.DruidCircle.Ability.*;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public enum DruidCircle implements Attribute {
    CIRCLE_OF_THE_LAND(gen -> {
        gen.level(2).addChoice(ChoiceGenerator.cantripChoice(1, AttributeType.WISDOM));
        gen.level(2).addAttributes(NATURAL_RECOVERY);
        gen.level(3).addAttributes(CIRCLE_SPELLS);
        gen.level(6).addAttributes(LANDS_STRIDE);
        gen.level(10).addAttributes(NATURES_WARD);
        gen.level(14).addAttributes(NATURES_SANCTUARY);
    }),
    CIRCLE_OF_THE_MOON(gen -> {
        gen.level(2).addAttributes(COMBAT_WILD_SHAPE);
        gen.level(2).addAttributes(CIRCLE_FORMS);
        gen.level(6).addAttributes(PRIMAL_STRIKE);
        gen.level(10).addAttributes(ELEMENTAL_WILD_SHAPE);
        gen.level(14).addAttributes(THOUSAND_FORMS);
    });

    public enum Ability implements Attribute {
        NATURAL_RECOVERY(delegate()
            .withDescription("During a short rest, regain [$level /^ 2] total [plural(level,levels)] "
                + "of spell slots under 6th level.")
            .withDescription("Use once between each long rest.")),
        CIRCLE_SPELLS(delegate()) {
            @Override
            public void generateInitialChoices(Character character) {
                character.addChoice(0, new OptionChoice("Circle Spells Terrain") {
                    @Override
                    public void select(Character character, ChoiceSelector selector) {
                        selector.chooseOption(Arrays.stream(Terrain.values()), terrain -> {
                            terrain.choose(character);
                            generateCircleSpells(character);
                        });
                    }
                });
            }

            @Override
            public void generateLevelChoices(Character character) {
                generateCircleSpells(character);
            }
        },
        LANDS_STRIDE(delegate()
            .withName("Land's Stride")
            .withDescription("Move through nonmagical difficult terrain without penalty.")
            .withDescription("Move through nonmagical plants without taking damage.")
            .withDescription("Advantage on save vs magical plants impeding movement.")),
        NATURES_WARD(delegate()
            .withName("Nature's Ward")
            .withDescription("Cannot be charmed or frightened by elementals or fey.")
            .withDescription("Immune to poison and disease")),
        NATURES_SANCTUARY(delegate()
            .withName("Nature's Sanctuary")
            .withDescription("Beasts and plant creatures must make Wis. save DC[$spell_dc] to "
                + "attack Druid. On save, immune for 24 hours.")),
        COMBAT_WILD_SHAPE(delegate()
            .withDescription("Assume <em>Wild Shape</em> as bonus action rather than action.")
            .withDescription("As a bonus action in <em>Wild Shape</em>, expend a spell slot to "
                + "regain 1d8 HP per spell level.")),
        CIRCLE_FORMS(delegate()
            .withDescription("Assume <em>Wild Shape</em> of beasts with CR up to "
                + "[max($level 2:1,6:$level/3)].")),
        PRIMAL_STRIKE(delegate()
            .withDescription("Beast form attacks count as magical.")),
        ELEMENTAL_WILD_SHAPE(delegate()
            .withDescription("Expend two uses of <em>Wild Shape</em> to transform into air, earth, "
                + "fire or water elemental.")),
        THOUSAND_FORMS(delegate()
            .withDescription("Cast <em>Alter Self</em> at will.")
            .withSpellAbility(Spell.ALTER_SELF, AttributeType.WISDOM)),;

        private final AttributeDelegate delegate;

        private Ability(AttributeDelegate delegate) {
            this.delegate = delegate;
        }

        @Override
        public AttributeType getType() {
            return AttributeType.DRUID_CIRCLE_ABILITY;
        }

        @Override
        public void generateLevelChoices(Character character) {
            delegate.generateChoices(character);
        }

        @Override
        public Stream<String> getDescription(Character character) {
            return delegate.getDescription(character);
        }

        @Override
        public String toString() {
            return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
        }

        public static Ability load(Element element) {
            return valueOf(element.getTextContent());
        }
    }

    private static final EnumMap<Terrain, Map<Integer, List<Spell>>> CIRCLE_SPELL_MAP
        = new EnumMap<>(Terrain.class);

    static {
        addCircleSpells(ARCTIC, 3, Spell.HOLD_PERSON, Spell.SPIKE_GROWTH);
        addCircleSpells(ARCTIC, 5, Spell.SLEET_STORM, Spell.SLOW);
        addCircleSpells(ARCTIC, 7, Spell.FREEDOM_OF_MOVEMENT, Spell.ICE_STORM);
        addCircleSpells(ARCTIC, 9, Spell.COMMUNE_WITH_NATURE, Spell.CONE_OF_COLD);
        addCircleSpells(COAST, 3, Spell.MIRROR_IMAGE, Spell.MISTY_STEP);
        addCircleSpells(COAST, 5, Spell.WATER_BREATHING, Spell.WATER_WALK);
        addCircleSpells(COAST, 7, Spell.CONTROL_WATER, Spell.FREEDOM_OF_MOVEMENT);
        addCircleSpells(COAST, 9, Spell.CONJURE_ELEMENTAL, Spell.SCRYING);
        addCircleSpells(DESERT, 3, Spell.BLUR, Spell.SILENCE);
        addCircleSpells(DESERT, 5, Spell.CREATE_FOOD_AND_WATER, Spell.PROTECTION_FROM_ENERGY);
        addCircleSpells(DESERT, 7, Spell.BLIGHT, Spell.HALLUCINATORY_TERRAIN);
        addCircleSpells(DESERT, 9, Spell.INSECT_PLAGUE, Spell.WALL_OF_STONE);
        addCircleSpells(FOREST, 3, Spell.BARKSKIN, Spell.SPIDER_CLIMB);
        addCircleSpells(FOREST, 5, Spell.CALL_LIGHTNING, Spell.PLANT_GROWTH);
        addCircleSpells(FOREST, 7, Spell.DIVINATION, Spell.FREEDOM_OF_MOVEMENT);
        addCircleSpells(FOREST, 9, Spell.COMMUNE_WITH_NATURE, Spell.TREE_STRIDE);
        addCircleSpells(GRASSLAND, 3, Spell.INVISIBILITY, Spell.PASS_WITHOUT_TRACE);
        addCircleSpells(GRASSLAND, 5, Spell.DAYLIGHT, Spell.HASTE);
        addCircleSpells(GRASSLAND, 7, Spell.DIVINATION, Spell.FREEDOM_OF_MOVEMENT);
        addCircleSpells(GRASSLAND, 9, Spell.DREAM, Spell.INSECT_PLAGUE);
        addCircleSpells(MOUNTAIN, 3, Spell.SPIDER_CLIMB, Spell.SPIKE_GROWTH);
        addCircleSpells(MOUNTAIN, 5, Spell.LIGHTNING_BOLT, Spell.MELD_INTO_STONE);
        addCircleSpells(MOUNTAIN, 7, Spell.STONE_SHAPE, Spell.STONESKIN);
        addCircleSpells(MOUNTAIN, 9, Spell.PASSWALL, Spell.WALL_OF_STONE);
        addCircleSpells(SWAMP, 3, Spell.DARKNESS, Spell.MELFS_ACID_ARROW);
        addCircleSpells(SWAMP, 5, Spell.WATER_WALK, Spell.STINKING_CLOUD);
        addCircleSpells(SWAMP, 7, Spell.FREEDOM_OF_MOVEMENT, Spell.LOCATE_CREATURE);
        addCircleSpells(SWAMP, 9, Spell.INSECT_PLAGUE, Spell.SCRYING);
        addCircleSpells(UNDERDARK, 3, Spell.SPIDER_CLIMB, Spell.WEB);
        addCircleSpells(UNDERDARK, 5, Spell.GASEOUS_FORM, Spell.STINKING_CLOUD);
        addCircleSpells(UNDERDARK, 7, Spell.GREATER_INVISIBILITY, Spell.STONE_SHAPE);
        addCircleSpells(UNDERDARK, 9, Spell.CLOUDKILL, Spell.INSECT_PLAGUE);
    }

    private static void addCircleSpells(Terrain terrain, int level, Spell... spells) {
        CIRCLE_SPELL_MAP.putIfAbsent(terrain, new HashMap<>());
        CIRCLE_SPELL_MAP.get(terrain).put(level, Arrays.asList(spells));
    }

    private static void generateCircleSpells(Character character) {
        if (character.hasAttribute(AttributeType.TERRAIN)) {
            Terrain terrain = character.getAttribute(AttributeType.TERRAIN);
            Map<Integer, List<Spell>> spells = CIRCLE_SPELL_MAP.get(terrain);
            int level = character.getLevel();
            if (spells.containsKey(level)) {
                SpellCasting casting = character.getSpellCasting("Druid");
                spells.get(level).forEach(casting::addPreparedSpell);
            }
        }
    }

    private final ChoiceGenerator generator = new ChoiceGenerator();

    private DruidCircle(Consumer<ChoiceGenerator> generatorMaker) {
        generatorMaker.accept(generator);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.DRUID_CIRCLE;
    }

    @Override
    public void generateLevelChoices(Character character) {
        generator.generateChoices(character);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return generator.getDescription(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static DruidCircle load(Node node) {
        return valueOf(node.getTextContent());
    }
}
