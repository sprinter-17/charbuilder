package characterbuilder.utils;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.Choice;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellCasting;
import java.util.List;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class TestMatchers {

    public static Matcher<Character> hasChoice(String choice) {
        return new TypeSafeDiagnosingMatcher<Character>() {
            @Override
            protected boolean matchesSafely(Character ch, Description desc) {
                if (ch.getChoiceCount() == 0) {
                    desc.appendText("has no choices");
                } else {
                    desc.appendText("has choices ")
                        .appendText(ch.getAllChoices().map(Choice::toString)
                            .collect(joining(",", "[", "]")));
                }
                return ch.hasChoice(choice);
            }

            @Override
            public void describeTo(Description desc) {
                desc.appendText("has choice ").appendValue(choice);
            }

        };
    }

    public static Matcher<Character> hasSpellAbility(Spell spell) {
        return new TypeSafeDiagnosingMatcher<Character>() {
            @Override
            protected boolean matchesSafely(Character ch, Description desc) {
                List<LearntSpell> spellAbilities
                    = ch.getAttributes(AttributeType.SPELL_ABILITY, LearntSpell.class)
                        .collect(toList());
                if (spellAbilities.isEmpty())
                    desc.appendText("has no spell abilities");
                else
                    desc.appendText("has spell abilities").appendValue(spellAbilities);
                return spellAbilities.stream().anyMatch(sa -> sa.isSpell(spell));
            }

            @Override
            public void describeTo(Description desc) {
                desc.appendText("has spell ability ").appendValue(spell.toString());
            }

        };
    }

    public static Matcher<Character> hasAttribute(String attributeName) {
        return new TypeSafeDiagnosingMatcher<Character>() {
            @Override
            protected boolean matchesSafely(Character ch, Description desc) {
                desc.appendText("has attributes ")
                    .appendValue(ch.getAllAttributes().map(Attribute::toString).collect(toList()));
                return ch.getAllAttributes().anyMatch(attr -> attr.toString().equals(attributeName));
            }

            @Override
            public void describeTo(Description desc) {
                desc.appendText("has attribute ").appendValue(attributeName);
            }
        };
    }

    public static Matcher<SpellCasting> hasPreparedSpell(Spell spell) {
        return new TypeSafeDiagnosingMatcher<SpellCasting>() {
            @Override
            protected boolean matchesSafely(SpellCasting t, Description d) {
                List<Spell> preparedSpells = t.getLearntSpells()
                    .filter(LearntSpell::isPrepared).map(LearntSpell::getSpell)
                    .collect(toList());
                d.appendText("has prepared spells ").appendValue(preparedSpells);
                return preparedSpells.contains(spell);
            }

            @Override
            public void describeTo(Description d) {
                d.appendText("has prepared spell ").appendValue(spell);
            }

        };
    }
}
