package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.CharacterClass.*;
import characterbuilder.character.characterclass.CharacterClassLevel;
import characterbuilder.character.characterclass.fighter.MartialArchetype;
import characterbuilder.character.characterclass.rogue.RoguishArchetype;
import java.util.EnumMap;
import java.util.List;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class SpellSlots {

    private final int levelDivisor;
    private final int[][] spellSlots;
    private final Optional<Attribute> prerequisite;

    private static final int[][] STANDARD_SLOTS = {
        {2},
        {3},
        {4, 2},
        {4, 3},
        {4, 3, 2},
        {4, 3, 3},
        {4, 3, 3, 1},
        {4, 3, 3, 2},
        {4, 3, 3, 3, 1},
        {4, 3, 3, 3, 2},
        {4, 3, 3, 3, 2, 1},
        {4, 3, 3, 3, 2, 1},
        {4, 3, 3, 3, 2, 1, 1},
        {4, 3, 3, 3, 2, 1, 1},
        {4, 3, 3, 3, 2, 1, 1, 1},
        {4, 3, 3, 3, 2, 1, 1, 1},
        {4, 3, 3, 3, 2, 1, 1, 1, 1},
        {4, 3, 3, 3, 3, 1, 1, 1, 1},
        {4, 3, 3, 3, 3, 2, 1, 1, 1},
        {4, 3, 3, 3, 3, 2, 2, 1, 1}
    };

    private static final int[][] FIGHTER_ROGUE_SLOTS = {
        {},
        {},
        {2},
        {3},
        {3},
        {3},
        {4, 2},
        {4, 2},
        {4, 2},
        {4, 3},
        {4, 3},
        {4, 3},
        {4, 3, 2},
        {4, 3, 2},
        {4, 3, 2},
        {4, 3, 3},
        {4, 3, 3},
        {4, 3, 3},
        {4, 3, 3, 1},
        {4, 3, 3, 1}
    };

    private static final int[][] PALADIN_RANGER_SPELLS = {
        {},
        {2},
        {3},
        {3},
        {4, 2},
        {4, 2},
        {4, 3},
        {4, 3},
        {4, 3, 2},
        {4, 3, 2},
        {4, 3, 3},
        {4, 3, 3},
        {4, 3, 3, 1},
        {4, 3, 3, 1},
        {4, 3, 3, 2},
        {4, 3, 3, 2},
        {4, 3, 3, 3, 1},
        {4, 3, 3, 3, 1},
        {4, 3, 3, 3, 2},
        {4, 3, 3, 3, 2}
    };

    private static final int[][] WARLOCK_SPELLS = {
        {1},
        {2},
        {0, 2},
        {0, 2},
        {0, 0, 2},
        {0, 0, 2},
        {0, 0, 0, 2},
        {0, 0, 0, 2},
        {0, 0, 0, 0, 2},
        {0, 0, 0, 0, 2},
        {0, 0, 0, 0, 3},
        {0, 0, 0, 0, 3},
        {0, 0, 0, 0, 3},
        {0, 0, 0, 0, 3},
        {0, 0, 0, 0, 3},
        {0, 0, 0, 0, 3},
        {0, 0, 0, 0, 4},
        {0, 0, 0, 0, 4},
        {0, 0, 0, 0, 4},
        {0, 0, 0, 0, 4}
    };

    private final static EnumMap<CharacterClass, SpellSlots> MAP
        = new EnumMap<>(CharacterClass.class);

    static {
        add(BARD, 1, STANDARD_SLOTS);
        add(CLERIC, 1, STANDARD_SLOTS);
        add(WIZARD, 1, STANDARD_SLOTS);
        add(SORCERER, 1, STANDARD_SLOTS);
        add(DRUID, 1, STANDARD_SLOTS);
        add(PALADIN, 2, PALADIN_RANGER_SPELLS);
        add(RANGER, 2, PALADIN_RANGER_SPELLS);
        add(WARLOCK, 0, WARLOCK_SPELLS);
        add(FIGHTER, 3, FIGHTER_ROGUE_SLOTS, MartialArchetype.ELDRITCH_KNIGHT);
        add(ROGUE, 3, FIGHTER_ROGUE_SLOTS, RoguishArchetype.ARCANE_TRICKSTER);
    }

    private static void add(CharacterClass characterClass, int levelDivisor, int[][] spellSlots) {
        MAP.put(characterClass, new SpellSlots(levelDivisor, spellSlots,
            Optional.empty()));
    }

    private static void add(CharacterClass characterClass, int levelDivisor, int[][] spellSlots,
        Attribute prerequisite) {
        MAP.put(characterClass, new SpellSlots(levelDivisor, spellSlots,
            Optional.of(prerequisite)));
    }

    public static boolean isSpellCaster(Character character) {
        return character.getAttributes(AttributeType.CHARACTER_CLASS, CharacterClassLevel.class)
            .anyMatch(ccl -> isSpellCastingClass(ccl, character));
    }

    private static boolean isSpellCastingClass(CharacterClassLevel classLevel,
        Character character) {
        return MAP.containsKey(classLevel.getCharacterClass())
            && MAP.get(classLevel.getCharacterClass()).prerequisite
                .map(character::hasAttribute).orElse(true);
    }

    public static int getSlotsAtLevel(Character character, int spellLevel) {
        if (spellLevel < 1 || spellLevel > 9)
            throw new IllegalArgumentException("Illegal spell level " + spellLevel);
        List<CharacterClassLevel> castingClasses = getCastingClasses(character).collect(toList());
        if (castingClasses.isEmpty())
            throw new IllegalArgumentException("Attempt to get spell slots for non-spellcaster");
        int[] slots = getSlots(castingClasses);
        return spellLevel <= slots.length ? slots[spellLevel - 1] : 0;
    }

    public static int getMaxSlot(Character character, CharacterClass spellClass) {
        return getSingleClassSlots(character.getCharacterClassLevels()
            .filter(ccl -> ccl.hasCharacterClass(spellClass)).findAny()
            .orElseThrow(IllegalStateException::new)).length;
    }

    private static Stream<CharacterClassLevel> getCastingClasses(Character character) {
        return character.getCharacterClassLevels()
            .filter(ccl -> isSpellCastingClass(ccl, character));
    }

    private static int[] getSlots(List<CharacterClassLevel> castingClasses) {
        if (castingClasses.size() == 1) {
            return getSingleClassSlots(castingClasses.get(0));
        } else {
            return getMultiClassSlots(castingClasses);
        }
    }

    private static int[] getSingleClassSlots(CharacterClassLevel classLevel) {
        int castingLevel = classLevel.getLevel();
        return getSpellSlots(classLevel).spellSlots[castingLevel - 1];
    }

    private static int[] getMultiClassSlots(List<CharacterClassLevel> castingClasses) {
        int castingLevel = castingClasses.stream()
            .mapToInt(SpellSlots::getMultiClassSpellCastingContribution).sum();
        return STANDARD_SLOTS[castingLevel - 1];
    }

    private static int getMultiClassSpellCastingContribution(CharacterClassLevel classLevel) {
        return getSpellSlots(classLevel)
            .getMultiClassSpellCastingLevelContribution(classLevel.getLevel());
    }

    private static SpellSlots getSpellSlots(CharacterClassLevel classLevel) {
        return MAP.get(classLevel.getCharacterClass());
    }

    private SpellSlots(int levelDivisor, int[][] spellSlots,
        Optional<Attribute> prerequisite) {
        this.levelDivisor = levelDivisor;
        this.spellSlots = spellSlots;
        this.prerequisite = prerequisite;
    }

    private int getMultiClassSpellCastingLevelContribution(int classLevel) {
        if (levelDivisor == 0)
            return 0;
        else
            return classLevel / levelDivisor;
    }
}
