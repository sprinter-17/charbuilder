package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Alignment;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Background;
import characterbuilder.character.attribute.CharacterClass;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.attribute.Sex;

public class InitialChoiceGenerator extends ChoiceGenerator {

    public InitialChoiceGenerator() {
        addChoice(new AttributeChoice("Race", Race.values()));
        addChoice(new AttributeChoice("Sex", Sex.values()));
        addChoice(new AttributeChoice("Class", CharacterClass.values()));
        addChoice(new AttributeChoice("Background", Background.values()));
        addChoice(new AttributeChoice("Alignment", Alignment.values()));
        addChoice(generateAbilityScores());
    }

    private OptionChoice generateAbilityScores() {
        return new OptionChoice("Generate ability scores") {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.generateAbilityScores(scores -> {
                    scores.forEach(character::addAttribute);
                    character.generateHitPoints();
                });
            }

            @Override
            public boolean isAllowed(Character character) {
                return character.hasAttribute(AttributeType.RACE)
                    && character.hasAttribute(AttributeType.CHARACTER_CLASS);
            }
        };
    }

}
