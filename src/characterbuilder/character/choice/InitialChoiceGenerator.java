package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.Alignment;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Background;
import characterbuilder.character.attribute.CharacterClass;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.attribute.Sex;

public class InitialChoiceGenerator extends ChoiceGenerator {

    public InitialChoiceGenerator() {
        addChoice(new OptionChoice("Race") {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(Race.initialRaceValues(), o -> o.choose(character));
            }
        });
        addChoice(new AttributeChoice("Sex", Sex.values()));
        addChoice(new AttributeChoice("Class", CharacterClass.values()));
        addChoice(generateAbilityScores());
        addChoice(new AttributeChoice("Background", Background.values()));
        addChoice(new AttributeChoice("Alignment", Alignment.values()));
    }

    private OptionChoice generateAbilityScores() {
        return new OptionChoice("Generate ability scores") {
            @Override
            public boolean isAllowed(Character character) {
                return character.hasAttribute(AttributeType.RACE)
                    && character.hasAttribute(AttributeType.CHARACTER_CLASS);
            }

            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.generateAbilityScores(scores -> {
                    scores.forEach(as -> addAbilityScore(character, as));
                    character.generateHitPoints();
                });
            }

            private void addAbilityScore(Character character, AbilityScore score) {
                if (character.getAttribute(AttributeType.CHARACTER_CLASS, CharacterClass.class)
                    .hasSavingsThrow(score.getType()))
                    score.setProficientSaves();
                character.addAttribute(score);
            }
        };
    }

}
