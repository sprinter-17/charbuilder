package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.equipment.Attack;
import static characterbuilder.character.saveload.Savable.text;
import characterbuilder.utils.EvaluationContext;
import characterbuilder.utils.StringUtils;
import java.util.Optional;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class LearntSpell implements Attribute {

    private final Spell spell;
    private final AttributeType ability;
    private final boolean prepared;

    public static boolean hasLearntSpell(Character character, LearntSpell spell) {
        return character.getAttributes(AttributeType.SPELL_ABILITY, LearntSpell.class)
            .anyMatch(ls -> ls.isSpell(spell.spell));
    }

    public LearntSpell(Spell spell, AttributeType ability, boolean prepared) {
        this.spell = spell;
        this.ability = ability;
        this.prepared = prepared;
    }

    public LearntSpell(Spell spell, AttributeType ability) {
        this(spell, ability, true);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SPELL_ABILITY;
    }

    public Spell getSpell() {
        return spell;
    }

    public int getModifier(Character character) {
        return character.getProficiencyBonus() + character.getModifier(ability);
    }

    public int getLevel() {
        return spell.getLevel();
    }

    public boolean isSpell(Spell spell) {
        return this.spell.equals(spell);
    }

    public boolean isPrepared() {
        return prepared;
    }

    public Stream<String> getEffects(Character character) {
        EvaluationContext context = new EvaluationContext();
        context.setCharacter(character);
        context.setSpell(this);
        return spell.getEffects().map(desc -> StringUtils.expand(desc, context));
    }

    public Optional<Attack> getAttack(Character character) {
        return spell.getAttack(character, ability);
    }

    @Override
    public String getOptionName() {
        return spell.getOptionName();
    }

    @Override
    public Stream<String> getDescription(Character character) {
        EvaluationContext context = new EvaluationContext();
        context.setCharacter(character);
        context.setSpell(this);
        return spell.getDescription().map(desc -> StringUtils.expand(desc, context));
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setTextContent(spell.name());
        element.setAttribute("ability", ability.name());
        element.setAttribute("prepared", Boolean.toString(prepared));
        return element;
    }

    public static LearntSpell load(Element element) {
        Spell spell = Spell.valueOf(text(element));
        AttributeType ability = AttributeType.valueOf(element.getAttribute("ability"));
        boolean prepared = Boolean.valueOf(element.getAttribute("prepared"));
        return new LearntSpell(spell, ability, prepared);
    }

    @Override
    public String toString() {
        return spell.toString();
    }

    @Override
    public int hashCode() {
        return spell.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass()
            && equalsLearntSpell((LearntSpell) obj);
    }

    protected boolean equalsLearntSpell(LearntSpell other) {
        return this.spell == other.spell
            && this.ability == other.ability
            && this.prepared == other.prepared;
    }

}
