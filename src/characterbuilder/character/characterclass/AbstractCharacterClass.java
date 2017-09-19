package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeDelegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.ClassSpecificChoiceGenerator;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class AbstractCharacterClass {

    private Optional<ChoiceGenerator> generator = Optional.empty();

    public abstract int getHitDie();

    public abstract Predicate<Character> getMulticlassPrerequisites();

    public abstract AttributeType getClassAttribute();

    public abstract Stream<AttributeType> getPrimaryAttributes();

    public boolean hasSavingsThrow(AttributeType type) {
        return getPrimaryAttributes().anyMatch(type::equals);
    }

    public void addInitialAttributes(Character character) {
    }

    public final ChoiceGenerator getGenerator(CharacterClass characterClass) {
        if (!generator.isPresent()) {
            generator = Optional.of(new ClassSpecificChoiceGenerator(characterClass));
            makeGenerator(generator.get());
        }
        return generator.get();
    }

    protected abstract void makeGenerator(ChoiceGenerator gen);

    protected static AttributeDelegate ability() {
        return new AttributeDelegate();
    }
}
