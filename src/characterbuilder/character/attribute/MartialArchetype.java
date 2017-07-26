package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.choice.AttributeChoice;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;

public enum MartialArchetype implements Attribute {

    CHAMPION(gen -> {
        gen.level(3).addAttributes(IMPROVED_CRITICAL);
        gen.level(7).addAttributes(REMARKABLE_ATHLETE);
        gen.level(7).addChoice(new AttributeChoice(AttributeType.FIGHTING_STYLE));
        gen.level(15).replaceAttribute(IMPROVED_CRITICAL, SUPERIOR_CRITICAL);
        gen.level(18).addAttributes(SURVIVOR);
    });

    private final ChoiceGenerator generator = new ChoiceGenerator();

    private MartialArchetype(Consumer<ChoiceGenerator> generator) {
        generator.accept(this.generator);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.MARTIAL_ARCHETYPE;
    }

    @Override
    public void generateLevelChoices(Character character) {
        generator.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

}
