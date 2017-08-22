package characterbuilder.character.characterclass.warlock;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellAbility;
import java.util.Objects;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MysticArcanum implements Attribute {

    private final Spell spell;

    public static OptionChoice chooseArcanum(CharacterClass characterClass, int level) {
        return new OptionChoice("Mystic Arcanum") {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(characterClass.getSpells()
                    .filter(sp -> sp.isLevel(level)), sp -> {
                    new MysticArcanum(sp).choose(character);
                });
            }
        };
    }

    protected MysticArcanum(Spell spell) {
        this.spell = spell;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.MYSTIC_ARCANUM;
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.of("Cast <em>" + spell.toString() + "</em> without expending a spell slot.",
            "Use once between each long rest.");
    }

    @Override
    public String toString() {
        return "Mystic Arcanum: " + spell.toString();
    }

    @Override
    public void choose(Character character) {
        Attribute.super.choose(character);
        character.addAttribute(new SpellAbility(spell, AttributeType.CHARISMA));
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setTextContent(spell.name());
        return element;
    }

    public static MysticArcanum load(Element element) {
        return new MysticArcanum(Spell.valueOf(element.getTextContent()));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.spell);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final MysticArcanum other = (MysticArcanum) obj;
        return this.spell == other.spell;
    }

}
