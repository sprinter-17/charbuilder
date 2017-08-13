package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.choice.ChoiceGenerator.spellChoice;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellCasting;
import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class ReplaceSpell extends OptionChoice {

    private final String spellCasting;
    private final List<Spell> allowedSpells;

    public ReplaceSpell(String spellCasting, Stream<Spell> allowedSpells) {
        super("Remove " + spellCasting + " Spell");
        this.spellCasting = spellCasting;
        this.allowedSpells = allowedSpells.collect(toList());
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        SpellCasting casting = getSpellCasting(character);
        if (allowedSpells.stream().noneMatch(casting::hasLearntSpell))
            throw new IllegalStateException("Attempt to replace spell while none available");
        selector.chooseOption(
            Stream.concat(Stream.of(NoOption.NONE), casting.getLearntSpells()), opt -> {
            if (opt instanceof Spell) {
                Spell spell = (Spell) opt;
                casting.removeLearntSpell(spell);
                character.pushChoice(spellChoice(spellCasting, 1,
                    "Replace " + spellCasting + " Spell",
                    allowedSpells.stream().filter(sp -> sp != spell)));
            }
        });
    }

    private SpellCasting getSpellCasting(Character character) {
        return character.getAttributes(AttributeType.SPELLCASTING, SpellCasting.class)
            .filter(sc -> sc.hasName(spellCasting))
            .findAny()
            .orElseThrow(() -> new IllegalStateException("No spellcasting " + spellCasting));
    }

}
