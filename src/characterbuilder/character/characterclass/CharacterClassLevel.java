package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    @Override
    public void generateInitialChoices(Character character) {
        generateLevelChoices(character);
    }

    public boolean hasSavingsThrow(AttributeType abilityScore) {
        return characterClass.hasSavingsThrow(abilityScore);
    }

    @Override
    public void generateLevelChoices(Character character) {
        characterClass.getGenerator().generateChoices(character);
    }

    @Override
    public Element save(Document doc) {
        return Attribute.super.save(doc);
    }

    public static CharacterClassLevel load(Element element) {
        return null;
    }
}
