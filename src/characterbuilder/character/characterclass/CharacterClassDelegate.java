package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.choice.ChoiceGenerator;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class CharacterClassDelegate {

    private Optional<ChoiceGenerator> generator = Optional.empty();

    public abstract AttributeType getClassAttribute();

    public abstract int getHitDie();

    public abstract Stream<AttributeType> getPrimaryAttributes();

    public abstract boolean hasSavingsThrow(AttributeType type);

    public void generateInitialChoices(Character character) {
        character.addAttributes(
            new IntAttribute(AttributeType.LEVEL, 1),
            new IntAttribute(AttributeType.EXPERIENCE_POINTS, 0));
        generateLevelChoices(character);
    }

    public void generateLevelChoices(Character character) {
        getGenerator(character.getAttribute(AttributeType.CHARACTER_CLASS))
            .generateChoices(character);
    }

    public Stream<String> getDescription(Character character) {
        return getGenerator(character.getAttribute(AttributeType.CHARACTER_CLASS))
            .getDescription(character);
    }

    public ChoiceGenerator getGenerator(CharacterClass characterClass) {
        if (!generator.isPresent()) {
            generator = Optional.of(new ChoiceGenerator());
            makeGenerator(generator.get());
//            generatorMaker.accept(characterClass, generator.get());
        }
        return generator.get();
    }

    protected abstract void makeGenerator(ChoiceGenerator gen);

}
