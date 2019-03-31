package characterbuilder.character.spell;

import characterbuilder.character.characterclass.CharacterClass;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

public class SpellClassMapTest {

    @Test
    public void testAllSpellsHaveClasses() {
        SpellClassMap map = new SpellClassMap();
        List<Spell> unmappedSpells = Arrays.stream(Spell.values())
            .filter(sp -> map.allSpells().noneMatch(sp::equals))
            .collect(toList());
        assertTrue(unmappedSpells.toString(), unmappedSpells.isEmpty());
    }

    @Test
    public void testRogueAndFighterSpellsAreWizardSpells() {
        SpellClassMap map = new SpellClassMap();
        assertTrue(map.spellsForClass(CharacterClass.ROGUE).anyMatch(Spell.FIREBALL::equals));
        assertTrue(map.spellsForClass(CharacterClass.FIGHTER).anyMatch(Spell.FIREBALL::equals));
    }

}
