package characterbuilder.character.characterclass.rogue;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.ability.Ability.*;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.utils.StringUtils;
import java.util.function.Consumer;
import org.w3c.dom.Node;

public enum RoguishArchetype implements Attribute {
    THIEF(gen -> {
        gen.level(3).addAttributes(FAST_HANDS, SECOND_STORY_WORK);
        gen.level(9).addAttributes(SUPREME_SNEAK);
        gen.level(13).addAttributes(USE_MAGIC_DEVICES);
        gen.level(17).addAttributes(THIEFS_REFLEXES);
    }),
    ASSASSIN(gen -> {

    }),
    ARCANE_TRICKSTER(gen -> {

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
        generator.generateChoices(character);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static RoguishArchetype load(Node node) {
        return RoguishArchetype.valueOf(node.getTextContent());
    }

}
