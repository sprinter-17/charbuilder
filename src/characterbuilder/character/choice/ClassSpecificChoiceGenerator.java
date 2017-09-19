package characterbuilder.character.choice;

import characterbuilder.character.characterclass.CharacterClass;
import java.util.Arrays;

public class ClassSpecificChoiceGenerator extends ChoiceGenerator {

    private final CharacterClass characterClass;

    public ClassSpecificChoiceGenerator(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    @Override
    public ChoiceGenerator level(int... levels) {
        return cond(ch -> ch.getCharacterClassLevels()
            .filter(ccl -> ccl.hasCharacterClass(characterClass))
            .findAny()
            .map(ccl -> Arrays.stream(levels).anyMatch(l -> ccl.getLevel() == l))
            .orElse(false));
    }
}
