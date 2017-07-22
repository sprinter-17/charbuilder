package characterbuilder.character.attribute;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;

public enum RoguishArchetype implements Attribute {
    THIEF(gen -> {
        gen.level(3).addAttributes(FAST_HANDS, SECOND_STORY_WORK);
        gen.level(9).addAttributes(SUPREME_SNEAK);
        gen.level(13).addAttributes(USE_MAGIC_DEVICES);
        gen.level(17).addAttributes(THIEFS_REFLEXES);
    });

    private final ChoiceGenerator generator = new ChoiceGenerator();

    private RoguishArchetype(Consumer<ChoiceGenerator> generator) {
        generator.accept(this.generator);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.ROGUISH_ARCHETYPE;
    }

    @Override
    public void generateLevelChoices(Character character) {
        Attribute.super.generateLevelChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

}
