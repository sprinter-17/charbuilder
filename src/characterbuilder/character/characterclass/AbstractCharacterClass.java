package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.choice.ChoiceGenerator;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class AbstractCharacterClass {

    private Optional<ChoiceGenerator> generator = Optional.empty();

    public abstract int getHitDie();

    public abstract AttributeType getClassAttribute();

    public abstract Stream<AttributeType> getPrimaryAttributes();

    public boolean hasSavingsThrow(AttributeType type) {
        return getPrimaryAttributes().anyMatch(type::equals);
    }

    public void generateInitialChoices(Character character) {
        character.addAttributes(
            new IntAttribute(AttributeType.LEVEL, 1),
            new IntAttribute(AttributeType.EXPERIENCE_POINTS, 0));
        generateLevelChoices(character);
    }

    public void generateLevelChoices(Character character) {
        getGenerator().generateChoices(character);
    }

    public ChoiceGenerator getGenerator() {
        if (!generator.isPresent()) {
            generator = Optional.of(new ChoiceGenerator());
            makeGenerator(generator.get());
        }
        return generator.get();
    }

    protected abstract void makeGenerator(ChoiceGenerator gen);

    protected static AttributeDelegate ability() {
        return new AttributeDelegate();
    }

}
