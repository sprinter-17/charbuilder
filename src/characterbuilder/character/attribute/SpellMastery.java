package characterbuilder.character.attribute;

import characterbuilder.character.ability.Spell;

public class SpellMastery implements Attribute {

    private final String name;
    private final Spell spell;

    public SpellMastery(String name, Spell spell) {
        this.name = name;
        this.spell = spell;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SPELL_MASTERY;
    }

    @Override
    public String toString() {
        return name + " " + spell.toString();
    }

    @Override
    public String encode() {
        return name + ":" + spell.name();
    }

    public static SpellMastery decode(AttributeType type, String code) {
        String[] components = code.split(":");
        return new SpellMastery(components[0], Spell.valueOf(components[1]));
    }
}
