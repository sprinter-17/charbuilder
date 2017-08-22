package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;
import org.w3c.dom.Node;

public enum FavouredEnemy implements Attribute {
    ABERRATIONS,
    BEASTS,
    CELESTIALS,
    CONSTRUCTS,
    DRAGONS,
    ELEMENTALS,
    FEY,
    FIENDS,
    GIANTS,
    MONSTROSITIES,
    OOZES,
    PLANTS,
    UNDEAD,
    GNOLLS,
    ORCS,
    GOBLINS;

    private final static FavouredEnemy[] HUMANOIDS = {GNOLLS, ORCS, GOBLINS};

    @Override
    public AttributeType getType() {
        return AttributeType.FAVOURED_ENEMY;
    }

    @Override
    public void generateInitialChoices(Character character) {
        if (Arrays.stream(HUMANOIDS).anyMatch(this::equals))
            character.addChoice(new OptionChoice("Second Humanoid Enemy") {
                @Override
                public void select(Character character, ChoiceSelector selector) {
                    selector.chooseOption(Arrays.stream(HUMANOIDS), character::addAttribute);
                }
            });
        // add language choice
    }

    public static FavouredEnemy load(Node node) {
        return valueOf(node.getTextContent());
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.of("Advantage on Survival checks to track; "
            + "Int. checks to recall information.");
    }
}
