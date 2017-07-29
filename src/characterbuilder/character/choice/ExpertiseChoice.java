package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Expertise;
import characterbuilder.character.ability.Proficiency;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import java.util.Arrays;
import java.util.stream.Stream;

public class ExpertiseChoice extends AttributeChoice {

    public ExpertiseChoice() {
        super(AttributeType.EXPERTISE);
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        selector.chooseOption(Stream.concat(Arrays.stream((Attribute[]) Skill.values()),
            Stream.of(Proficiency.THIEVES_TOOLS))
            .filter(attr -> character.hasAttribute(attr))
            .map(Expertise::new)
            .filter(ex -> !character.hasAttribute(ex)),
            ex -> ex.choose(character));
    }
}