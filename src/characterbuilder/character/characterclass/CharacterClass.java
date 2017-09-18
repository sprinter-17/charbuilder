package characterbuilder.character.characterclass;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.barbarian.Barbarian;
import characterbuilder.character.characterclass.bard.Bard;
import characterbuilder.character.characterclass.cleric.Cleric;
import characterbuilder.character.characterclass.druid.Druid;
import characterbuilder.character.characterclass.fighter.Fighter;
import characterbuilder.character.characterclass.monk.Monk;
import characterbuilder.character.characterclass.paladin.Paladin;
import characterbuilder.character.characterclass.ranger.Ranger;
import characterbuilder.character.characterclass.rogue.Rogue;
import characterbuilder.character.characterclass.sorcerer.Sorcerer;
import characterbuilder.character.characterclass.warlock.Warlock;
import characterbuilder.character.characterclass.wizard.Wizard;
import characterbuilder.character.choice.ChoiceGenerator;
import characterbuilder.character.choice.Option;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellClassMap;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public enum CharacterClass implements Option {
    BARBARIAN(new Barbarian()),
    BARD(new Bard()),
    CLERIC(new Cleric()),
    DRUID(new Druid()),
    FIGHTER(new Fighter()),
    MONK(new Monk()),
    PALADIN(new Paladin()),
    RANGER(new Ranger()),
    ROGUE(new Rogue()),
    SORCERER(new Sorcerer()),
    WARLOCK(new Warlock()),
    WIZARD(new Wizard());

    private final AbstractCharacterClass delegate;

    private CharacterClass(AbstractCharacterClass delegate) {
        this.delegate = delegate;
    }

    public AttributeType getClassAttribute() {
        return delegate.getClassAttribute();
    }

    public int getHitDie() {
        return delegate.getHitDie();
    }

    public Stream<AttributeType> getPrimaryAttributes() {
        return delegate.getPrimaryAttributes();
    }

    public boolean hasSavingsThrow(AttributeType type) {
        return delegate.hasSavingsThrow(type);
    }

    public ChoiceGenerator getGenerator() {
        return delegate.getGenerator();
    }

    public Stream<Spell> getSpells() {
        final SpellClassMap map = new SpellClassMap();
        return map.spellsForClass(this);
    }

    @Override
    public String toString() {
        return StringUtils.capitaliseEnumName(name());
    }

    public static CharacterClass load(Node node) {
        return CharacterClass.valueOf(node.getTextContent());
    }

    public boolean hasMulticlassPrerequisites(Character character) {
        return delegate.getMulticlassPrerequisites().test(character);
    }

    @Override
    public void choose(Character character) {
        new CharacterClassLevel(this).choose(character);
    }

    @Override
    public Element save(Document doc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
