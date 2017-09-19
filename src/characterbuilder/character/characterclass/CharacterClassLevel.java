package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.saveload.Savable;
import java.util.Objects;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CharacterClassLevel implements Attribute {

    private final CharacterClass characterClass;
    private int level = 1;

    public CharacterClassLevel(CharacterClass characterClass) {
        this(characterClass, 1);
    }

    public CharacterClassLevel(CharacterClass characterClass, int level) {
        this.characterClass = characterClass;
        this.level = level;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.CHARACTER_CLASS;
    }

    public boolean hasCharacterClass(CharacterClass characterClass) {
        return this.characterClass == characterClass;
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void increaseLevel(Character character) {
        level++;
        characterClass.getGenerator().generateChoices(character);
    }

    public int getLevel() {
        return level;
    }

    @Override
    public void generateInitialChoices(Character character) {
        if (!character.hasAttribute(AttributeType.EXPERIENCE_POINTS))
            character.addAttribute(new IntAttribute(AttributeType.EXPERIENCE_POINTS, 0));
        characterClass.getGenerator().generateChoices(character);
    }

    public boolean hasSavingsThrow(AttributeType abilityScore) {
        return characterClass.hasSavingsThrow(abilityScore);
    }

    @Override
    public String toString() {
        return characterClass.toString() + " " + level;
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setAttribute("level", Integer.toString(level));
        element.setTextContent(characterClass.name());
        return element;
    }

    public static CharacterClassLevel load(Element element) {
        CharacterClass characterClass = CharacterClass.valueOf(Savable.text(element));
        int level = Integer.valueOf(element.getAttribute("level"));
        return new CharacterClassLevel(characterClass, level);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.characterClass);
        hash = 97 * hash + this.level;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final CharacterClassLevel other = (CharacterClassLevel) obj;
        return this.characterClass == other.characterClass
            && this.level == other.level;
    }
}
