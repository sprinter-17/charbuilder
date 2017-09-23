package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.CharacterClass.*;
import characterbuilder.character.characterclass.CharacterClassLevel;
import characterbuilder.character.characterclass.fighter.MartialArchetype;
import characterbuilder.character.characterclass.rogue.RoguishArchetype;
import java.util.EnumMap;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public abstract class SpellSlots {

    protected abstract boolean characterIsSpellCaster(Character character);

    protected abstract int getMultiClassSpellCastingLevelContribution(int classLevel);

    protected abstract int getSlots(int classLevel, int spellLevel);

    protected abstract int getMaxSpellLevel(int classLevel);

    private static class StandardSlots extends SpellSlots {

        private static final int[][] SLOTS = {
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

        @Override
        protected int getMultiClassSpellCastingLevelContribution(int classLevel) {
            return classLevel;
        }

        @Override
        protected boolean characterIsSpellCaster(Character character) {
            return true;
        }

        @Override
        protected int getSlots(int classLevel, int spellLevel) {
            return getSlotsFromSpellLevel(SLOTS[classLevel - 1], spellLevel);
        }

        @Override
        protected int getMaxSpellLevel(int classLevel) {
            return SLOTS[classLevel - 1].length;
        }
    }

    private static class WarlockSlots extends SpellSlots {

        private static final int[][] SLOTS = {
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

        @Override
        protected int getMultiClassSpellCastingLevelContribution(int classLevel) {
            return 0;
        }

        @Override
        protected boolean characterIsSpellCaster(Character character) {
            return true;
        }

        @Override
        protected int getSlots(int classLevel, int spellLevel) {
            return getSlotsFromSpellLevel(SLOTS[classLevel - 1], spellLevel);
        }

        @Override
        protected int getMaxSpellLevel(int classLevel) {
            return SLOTS[classLevel - 1].length;
        }
    }

    private static class PaladinRangerSlots extends SpellSlots {

        private static final int[][] SLOTS = {
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

        @Override
        protected int getMultiClassSpellCastingLevelContribution(int classLevel) {
            return classLevel / 2;
        }

        @Override
        protected boolean characterIsSpellCaster(Character character) {
            return true;
        }

        @Override
        protected int getSlots(int classLevel, int spellLevel) {
            return getSlotsFromSpellLevel(SLOTS[classLevel - 1], spellLevel);
        }

        @Override
        protected int getMaxSpellLevel(int classLevel) {
            return SLOTS[classLevel - 1].length;
        }
    }

    private abstract static class FighterRogueSlots extends SpellSlots {

        private static final int[][] SLOTS = {
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

        @Override
        protected int getMultiClassSpellCastingLevelContribution(int classLevel) {
            return classLevel / 3;
        }

        @Override
        protected int getSlots(int classLevel, int spellLevel) {
            return getSlotsFromSpellLevel(SLOTS[classLevel - 1], spellLevel);
        }

        @Override
        protected int getMaxSpellLevel(int classLevel) {
            return SLOTS[classLevel - 1].length;
        }
    }

    private static class FighterSlots extends FighterRogueSlots {

        @Override
        protected boolean characterIsSpellCaster(Character character) {
            return character.hasAttribute(MartialArchetype.ELDRITCH_KNIGHT);
        }
    }

    private static class RogueSlots extends FighterRogueSlots {

        @Override
        protected boolean characterIsSpellCaster(Character character) {
            return character.hasAttribute(RoguishArchetype.ARCANE_TRICKSTER);
        }
    }

    private final static EnumMap<CharacterClass, SpellSlots> MAP
        = new EnumMap<>(CharacterClass.class);

    private final static SpellSlots MULTICLASS_SLOTS = new StandardSlots();

    static {
        MAP.put(BARD, new StandardSlots());
        MAP.put(CLERIC, new StandardSlots());
        MAP.put(WIZARD, new StandardSlots());
        MAP.put(SORCERER, new StandardSlots());
        MAP.put(DRUID, new StandardSlots());
        MAP.put(WARLOCK, new WarlockSlots());
        MAP.put(PALADIN, new PaladinRangerSlots());
        MAP.put(RANGER, new PaladinRangerSlots());
        MAP.put(FIGHTER, new FighterSlots());
        MAP.put(ROGUE, new RogueSlots());
    }

    public static boolean isSpellCaster(Character character) {
        return character.getAttributes(AttributeType.CHARACTER_CLASS, CharacterClassLevel.class)
            .anyMatch(ccl -> isSpellCastingClassForCharacter(ccl, character));
    }

    private static boolean isSpellCastingClassForCharacter(CharacterClassLevel classLevel,
        Character character) {
        return MAP.containsKey(classLevel.getCharacterClass())
            && MAP.get(classLevel.getCharacterClass()).characterIsSpellCaster(character);
    }

    public static int getHighestSpellLevelForClass(Character character, CharacterClass spellClass) {
        CharacterClassLevel characterClassLevel = character.getCharacterClassLevels()
            .filter(ccl -> ccl.hasCharacterClass(spellClass))
            .findAny().orElseThrow(IllegalStateException::new);
        return getSpellSlots(characterClassLevel)
            .getMaxSpellLevel(characterClassLevel.getLevel());
    }

    public static int getSlotsForSpellLevel(Character character, int spellLevel) {
        if (spellLevel < 1 || spellLevel > 9)
            throw new IllegalArgumentException("Illegal spell level " + spellLevel);
        List<CharacterClassLevel> castingClasses = getCastingClasses(character).collect(toList());
        switch (castingClasses.size()) {
            case 0:
                return 0;
            case 1:
                return getSlotsForSingleClass(castingClasses.get(0), spellLevel);
            default:
                return getSlotsForMultiClass(castingClasses, spellLevel);
        }
    }

    private static Stream<CharacterClassLevel> getCastingClasses(Character character) {
        return character.getCharacterClassLevels()
            .filter(ccl -> isSpellCastingClassForCharacter(ccl, character));
    }

    private static int getSlotsForSingleClass(CharacterClassLevel classLevel, int spellLevel) {
        return getSpellSlots(classLevel).getSlots(classLevel.getLevel(), spellLevel);
    }

    private static int getSlotsForMultiClass(List<CharacterClassLevel> castingClasses,
        int spellLevel) {
        int castingLevel = castingClasses.stream()
            .mapToInt(SpellSlots::getMultiClassSpellCastingContribution).sum();
        return MULTICLASS_SLOTS.getSlots(castingLevel, spellLevel)
            + pactMagicSlots(castingClasses, spellLevel);
    }

    private static int getMultiClassSpellCastingContribution(CharacterClassLevel classLevel) {
        return getSpellSlots(classLevel)
            .getMultiClassSpellCastingLevelContribution(classLevel.getLevel());
    }

    private static int pactMagicSlots(List<CharacterClassLevel> castingClasses, int spellLevel) {
        return castingClasses.stream()
            .mapToInt(ccl -> pactMagicSlotsForClassLevel(ccl, spellLevel))
            .sum();
    }

    private static int pactMagicSlotsForClassLevel(CharacterClassLevel ccl, int spellLevel) {
        SpellSlots slots = getSpellSlots(ccl);
        if (slots.getMultiClassSpellCastingLevelContribution(ccl.getLevel()) == 0)
            return getSpellSlots(ccl).getSlots(ccl.getLevel(), spellLevel);
        else
            return 0;
    }

    private static SpellSlots getSpellSlots(CharacterClassLevel classLevel) {
        if (MAP.containsKey(classLevel.getCharacterClass()))
            return MAP.get(classLevel.getCharacterClass());
        else
            throw new IllegalArgumentException("Non-casting class");
    }

    protected int getSlotsFromSpellLevel(int[] slots, int spellLevel) {
        if (spellLevel > slots.length)
            return 0;
        else
            return slots[spellLevel - 1];
    }

}
