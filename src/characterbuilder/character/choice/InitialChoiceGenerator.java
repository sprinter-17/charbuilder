package characterbuilder.character.choice;

import characterbuilder.character.Character;
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

    protected static final OptionChoice GENERATE_ABILITY_SCORES
        = generateAbilityScores();
    protected static final OptionChoice CHOOSE_RACE
        = attributeEnumChoice("Choose race", Race.values(),
            ch -> {
            if (ch.hasAttribute(AttributeType.CHARACTER_CLASS))
                ch.addChoice(GENERATE_ABILITY_SCORES);
        },
            ch -> ch.addChoice(attributeEnumChoice("Sex", Sex.values())));
    protected static final OptionChoice CHOOSE_CLASS
        = attributeEnumChoice("Choose class", CharacterClass.values(),
            ch -> {
            if (ch.hasAttribute(AttributeType.RACE))
                ch.addChoice(GENERATE_ABILITY_SCORES);
        });
    protected static final OptionChoice CHOOSE_BACKGROUND
        = attributeEnumChoice("Choose background", Background.values(),
            ch -> ch.getAttribute(AttributeType.BACKGROUND, Background.class)
                .addChoices(ch));
    protected static final Choice CHOOSE_ALIGNMENT
        = attributeEnumChoice("Choose alignment", Alignment.values());

    private static OptionChoice attributeEnumChoice(String name, Attribute[] values,
        Consumer<Character>... andThen) {
        return new ChoiceGenerator.NamedChoice(name) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(Arrays.stream(values), attr -> {
                    attr.choose(character);
                    Arrays.stream(andThen).forEach(at -> at.accept(character));
                });
            }
        };
    }

    private static OptionChoice generateAbilityScores() {
        return new ChoiceGenerator.NamedChoice("Generate ability scores") {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.generateAbilityScores(scores -> {
                    scores.forEach(character::addAttribute);
                    character.generateHitPoints();
                });
            }
        };
    }

    public InitialChoiceGenerator() {
        addChoice(CHOOSE_RACE);
        addChoice(CHOOSE_CLASS);
        addChoice(CHOOSE_BACKGROUND);
        addChoice(CHOOSE_ALIGNMENT);
    }

}
