package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.CharacterRandom;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.DIVINE_DOMAIN;
import static characterbuilder.character.characterclass.CharacterClass.CLERIC;
import static characterbuilder.character.characterclass.CharacterClass.FIGHTER;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.InitialChoiceGenerator;
import characterbuilder.character.choice.Option;
import java.util.List;
import java.util.function.Consumer;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class CharacterClassTest {

    private int choiceCount = 0;

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
            T option = optionList.get(count++ % optionList.size());
            followUp.accept(option);
        }

        @Override
        public void generateAbilityScores(Consumer<Stream<AbilityScore>> consumer) {
            consumer.accept(AbilityScore.SCORES.stream()
                .map(score -> new AbilityScore(score, count++ % 16 + 3)));
        }
    };

    @Before
    public void setup() {
    }

    @Test
    public void testClassAttribute() {
        assertThat(CLERIC.getClassAttribute().get(), is(DIVINE_DOMAIN));
        assertThat(FIGHTER.getClassAttribute().get(), is(AttributeType.MARTIAL_ARCHETYPE));
    }

    @Test
    public void testMultipleCharacterGeneration() {
        InitialChoiceGenerator init = new InitialChoiceGenerator();
        for (int i = 1; i < 500; i++) {
            Character character = new Character();
            character.addChoiceList(new IterativeSelector(i));
            init.generateChoices(character);
            exhaustChoices(character);
            while (character.getLevel() < 20) {
                character.increaseLevel(new CharacterRandom());
                exhaustChoices(character);
            }
        }
    }

    private void exhaustChoices(Character character) {
        while (character.getChoiceCount() > 0) {
            character.selectChoice(character.getChoice(choiceCount++ % character.getChoiceCount()));
        }
    }
}
