package characterbuilder.character.characterclass;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;

public class CharacterClassLevel implements Attribute {

    private final CharacterClass characterClass;
    private int level = 1;

    public CharacterClassLevel(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.CHARACTER_CLASS;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void increaseLevel() {
        level++;
    }

    public int getLevel() {
        return level;
    }

}
