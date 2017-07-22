package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.CharacterRandom;
import characterbuilder.character.attribute.Alignment;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Background;
import characterbuilder.character.attribute.CharacterClass;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.attribute.Sex;
import java.util.Arrays;
import java.util.function.Consumer;

public class InitialChoiceGenerator extends ChoiceGenerator {

    protected static final Choice GENERATE_ABILITY_SCORES
        = generateAbilityScores();
    protected static final Choice CHOOSE_RACE
        = attributeEnumChoice("Choose race", Race.values(),
            ch -> {
                if (ch.hasAttribute(AttributeType.CHARACTER_CLASS))
                    ch.getChoices().addChoice(GENERATE_ABILITY_SCORES);
            },
            ch -> ch.getChoices().addChoice(attributeEnumChoice("Sex", Sex.values())));
    protected static final Choice CHOOSE_CLASS
        = attributeEnumChoice("Choose class", CharacterClass.values(),
            ch -> {
                if (ch.hasAttribute(AttributeType.RACE))
                    ch.getChoices().addChoice(GENERATE_ABILITY_SCORES);
            });
    protected static final Choice CHOOSE_BACKGROUND
        = attributeEnumChoice("Choose background", Background.values(),
            ch -> ch.getAttribute(AttributeType.BACKGROUND, Background.class)
                .addChoices(ch));
    protected static final Choice CHOOSE_ALIGNMENT
        = attributeEnumChoice("Choose alignment", Alignment.values());

    private static final CharacterRandom RANDOM = new CharacterRandom();

    private static Choice baseChoice(String name, Consumer<Character> action) {
        return new ChoiceGenerator.AbstractChoice(name) {
            @Override
            public void makeChoice(Character character, ChoiceSelector selector) {
                action.accept(character);
                character.getChoices().removeChoice(this);
                selector.choiceMade();
            }
        };
    }

    private static Choice attributeEnumChoice(String name, Attribute[] values,
        Consumer<Character>... andThen) {
        return new ChoiceGenerator.AbstractChoice(name) {
            @Override
            public void makeChoice(Character character, ChoiceSelector selector) {
                selector.getAttribute(Arrays.stream(values), attr -> {
                    character.addAttribute(attr);
                    attr.generateInitialChoices(character);
                    character.getChoices().removeChoice(this);
                    Arrays.stream(andThen).forEach(at -> at.accept(character));
                });
            }
        };
    }

    private static Choice generateAbilityScores() {
        return new ChoiceGenerator.AbstractChoice("Generate ability scores") {
            @Override
            public void makeChoice(Character character, ChoiceSelector selector) {
                character.getChoices().removeChoice(this);
                character.generateAbilityScores(new CharacterRandom());
                character.getChoices().addChoice(
                    baseChoice("Generate hit points", Character::generateHitPoints));
                selector.choiceMade();
            }
        };
    }

    public InitialChoiceGenerator() {
        addChoice(() -> CHOOSE_RACE);
        addChoice(() -> CHOOSE_CLASS);
        addChoice(() -> CHOOSE_BACKGROUND);
        addChoice(() -> CHOOSE_ALIGNMENT);
    }

}
