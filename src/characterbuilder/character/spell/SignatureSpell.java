package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.saveload.Savable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SignatureSpell implements Attribute {

    private final List<Spell> spells = new ArrayList<>();

    @Override
    public AttributeType getType() {
        return AttributeType.SIGNATURE_SPELL;
    }

    @Override
    public void generateInitialChoices(Character character) {
        assert spells.isEmpty();
        SpellCasting casting = character
            .getAttributes(AttributeType.SPELLCASTING, SpellCasting.class)
            .filter(sc -> sc.hasName("Wizard"))
            .findAny().orElseThrow(() -> new IllegalStateException("No wizard spellcasting"));
        character.addChoice(new OptionChoice("Signature Spell", 2) {
            @Override
            public void select(Character character, ChoiceSelector selector) {
                selector.chooseOption(casting.getLearntSpells().filter(sp -> sp.isLevel(3)),
                    spells::add);
            }
        });
    }

    public boolean hasSpell(Spell spell) {
        return spells.contains(spell);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.of(
            spells.get(0).toString() + " and " + spells.get(1).toString() + " are always prepared.",
            "Can be cast without spending a spell slot once betwen each long rest."
        );
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        spells.forEach(sp -> element
            .appendChild(doc.createElement("spell"))
            .setTextContent(sp.name()));
        return element;
    }

    public static SignatureSpell load(Element element) {
        SignatureSpell signatureSpell = new SignatureSpell();
        Savable.children(element)
            .map(Element::getTextContent).map(Spell::valueOf)
            .forEach(signatureSpell.spells::add);
        return signatureSpell;
    }

    @Override
    public int hashCode() {
        return spells.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final SignatureSpell other = (SignatureSpell) obj;
        return other.spells.equals(spells);
    }
}
