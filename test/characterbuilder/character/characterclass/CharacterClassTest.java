package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.CharacterRandom;
import characterbuilder.character.ability.Ability;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.DIVINE_DOMAIN;
import static characterbuilder.character.characterclass.CharacterClass.BARBARIAN;
import static characterbuilder.character.characterclass.CharacterClass.CLERIC;
import static characterbuilder.character.characterclass.CharacterClass.FIGHTER;
import static characterbuilder.character.characterclass.CharacterClass.PALADIN;
import characterbuilder.character.characterclass.wizard.MagicSchool;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.InitialChoiceGenerator;
import characterbuilder.character.choice.Option;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.utils.TestCharacter;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CharacterClassTest {

    private static final Logger LOG = Logger.getLogger(CharacterClassTest.class.getName());

    private TestCharacter character;
    private IterativeSelector selector;

    @Before
    public void setup() {
        MagicSchool.values(); // avoid initialisation error
        Ability.values(); // avoid initialisation error
        character = new TestCharacter().withScores(10);
    }

    @Test
    public void testMulticlassPrerequisites() {
        assertFalse(CLERIC.hasMulticlassPrerequisites(character));
        character.setScore(AttributeType.WISDOM, 12);
        assertFalse(CLERIC.hasMulticlassPrerequisites(character));
        character.setScore(AttributeType.WISDOM, 13);
        assertTrue(CLERIC.hasMulticlassPrerequisites(character));
        assertFalse(FIGHTER.hasMulticlassPrerequisites(character));
        character.setScore(AttributeType.STRENGTH, 13);
        assertTrue(FIGHTER.hasMulticlassPrerequisites(character));
        assertFalse(PALADIN.hasMulticlassPrerequisites(character));
        character.setScore(AttributeType.CHARISMA, 13);
        assertTrue(PALADIN.hasMulticlassPrerequisites(character));
    }

    private class IterativeSelector implements ChoiceSelector {

        private int count;

        public IterativeSelector(int count) {
            this.count = count;
        }

        @Override
        public <T extends Option> void chooseOption(Stream<T> options, Consumer<T> followUp) {
            List<T> optionList = options.collect(toList());
            if (optionList.isEmpty())
                throw new IllegalStateException("Empty option list");
            T option = optionList.get(count(optionList.size()));
            if (optionList.size() > 3)
                LOG.fine("        Chosen " + option.toString() + " from "
                    + optionList.subList(0, 3) + "...");
            else
                LOG.fine("        Chosen " + option.toString() + " from " + optionList);
            followUp.accept(option);
        }

        @Override
        public void generateAbilityScores(Consumer<Stream<AbilityScore>> consumer) {
            consumer.accept(AbilityScore.SCORES.stream()
                .map(score -> new AbilityScore(score, count(16) + 3)));
        }

        private int count(int mod) {
            int result = count % mod;
            count = (241 * count + 2287) % 305017;
            return result;
        }
    };

    @Test
    public void testClassAttribute() {
        assertThat(CLERIC.getClassAttribute(), is(DIVINE_DOMAIN));
        assertThat(FIGHTER.getClassAttribute(), is(AttributeType.MARTIAL_ARCHETYPE));
    }

    @Test
    public void testBarbarianPrimalChampion() {
        character.setScore(AttributeType.STRENGTH, 18);
        BARBARIAN.choose(character);
        character.setLevel(BARBARIAN, 19);
        character.getCharacterClassLevels().findAny().get().increaseLevel(character);
        AbilityScore strength = character.getAttribute(AttributeType.STRENGTH);
        assertThat(strength.getValue(), is(22));
    }

    @Test
    public void testMultipleCharacterGeneration() {
        setLogging(Level.OFF);
        InitialChoiceGenerator init = new InitialChoiceGenerator();
        for (int i = 1; i <= 1000; i++) {
            selector = new IterativeSelector(i);
            LOG.info("Character #" + i);
            character = new TestCharacter();
            character.addChoiceList(selector);
            init.generateChoices(character);
            exhaustChoices(i, character);
            while (character.getLevel() < 20) {
                CharacterClass characterClass = character
                    .getAttributes(AttributeType.CHARACTER_CLASS, CharacterClassLevel.class)
                    .map(cc -> cc.getCharacterClass()).findAny().get();
                character.increaseLevel(characterClass, new CharacterRandom());
                exhaustChoices(i, character);
            }
        }
    }

    private void setLogging(Level level) {
        try {
            FileHandler fh = new FileHandler("trace.log");
            fh.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return record.getMessage() + "\n";
                }
            });
            LOG.setUseParentHandlers(false);
            LOG.addHandler(fh);
            LOG.setLevel(level);
        } catch (SecurityException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    private void exhaustChoices(int count, Character character) {
        LOG.info("  Level " + character.getLevel());
        while (character.getChoiceCount() > 0) {
            OptionChoice choice = character.getChoice(selector.count(character.getChoiceCount()));
            LOG.fine("    Choosing " + choice.toString());
            try {
                character.selectChoice(choice);
                LOG.fine("      Choices: " + character.getAllChoices()
                    .limit(3).map(Object::toString).collect(joining(","))
                    + (character.getChoiceCount() > 3 ? "..." : ""));
            } catch (IllegalStateException ex) {
                String message = count + ":"
                    + character.getAttribute(AttributeType.RACE).toString() + " "
                    + character.getAttribute(AttributeType.CHARACTER_CLASS).toString()
                    + "/" + character.getLevel()
                    + " Choosing " + choice.toString() + ": " + ex.getMessage();
                throw new AssertionError(message);
            }
        }
    }
}
