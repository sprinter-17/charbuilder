package characterbuilder.character.spell;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SpellClassMapTest {

    @Test
    public void testAllSpellsHaveClasses() {
        SpellClassMap map = new SpellClassMap();
        List<Spell> unmappedSpells = Arrays.stream(Spell.values())
            .filter(sp -> map.allSpells().noneMatch(sp::equals))
            .collect(toList());
        assertTrue(unmappedSpells.toString(), unmappedSpells.isEmpty());
    }

}
