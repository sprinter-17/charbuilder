package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.choice.ChoiceGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class CharacterClassDelegate {

    private final int hitDie;
    private final AttributeType classAttribute;
    private final List<AttributeType> savingThrows = new ArrayList<>();
    private final List<AttributeType> primaryAttributes;
    private final BiConsumer<CharacterClass, ChoiceGenerator> generatorMaker;
    private Optional<ChoiceGenerator> generator = Optional.empty();

    public CharacterClassDelegate(int hitDie, AttributeType classAttribute,
        AttributeType savingThrow1, AttributeType savingThrow2,
        List<AttributeType> primaryAttributes, BiConsumer<CharacterClass, ChoiceGenerator> generator) {
        this.hitDie = hitDie;
        this.classAttribute = classAttribute;
        savingThrows.add(savingThrow1);
        savingThrows.add(savingThrow2);
        this.primaryAttributes = primaryAttributes;
        this.generatorMaker = generator;
    }

    public Optional<AttributeType> getClassAttribute() {
        return Optional.ofNullable(classAttribute);
    }

    public int getHitDie() {
        return hitDie;
    }

    public Stream<AttributeType> getPrimaryAttributes() {
        return primaryAttributes.stream();
    }

    public boolean hasSavingsThrow(AttributeType type) {
        return savingThrows.contains(type);
    }

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
            generatorMaker.accept(characterClass, generator.get());
        }
        return generator.get();
    }

}
