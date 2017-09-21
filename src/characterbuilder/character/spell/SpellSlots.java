package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.CharacterClass.*;
import java.util.EnumSet;

public class SpellSlots {

    private static final EnumSet<CharacterClass> SPELL_CASTING_CLASSES = EnumSet.of(
        BARD, WIZARD, CLERIC, WARLOCK, DRUID);

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

    public static int getSlotsAtLevel(Character character, int level) {
        if (character.getCharacterClasses().noneMatch(SPELL_CASTING_CLASSES::contains))
            throw new IllegalArgumentException("No spellcasting classes");
        if (level < 1 || level > 9)
            throw new IllegalArgumentException("Illegal spell slot level " + level);
        int[] slots = STANDARD_SLOTS[character.getLevel() - 1];
        return level <= slots.length ? slots[level - 1] : 0;
    }
}
