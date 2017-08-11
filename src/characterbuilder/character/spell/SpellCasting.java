package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.saveload.Savable;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SpellCasting implements Attribute {

    private final String name;
    private final AttributeType spellAbilityScore;
    private final String preparedSpellText;
    private final List<Spell> learntSpells = new ArrayList<>();
    private int[] spellSlots = {};

    public SpellCasting(String name, AttributeType spellAbilityScore) {
        this(name, spellAbilityScore, "All");
    }

    public SpellCasting(String name, AttributeType spellAbilityScore, String preparedSpellText) {
        this.name = name;
        this.spellAbilityScore = spellAbilityScore;
        this.preparedSpellText = preparedSpellText;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SPELLCASTING;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    public void addSlots(int level, int slots) {
        if (level > spellSlots.length)
            this.spellSlots = Arrays.copyOf(spellSlots, level);
        spellSlots[level - 1] += slots;
    }

    public int getSlotsAtLevel(int level) {
        if (level < 1 || level > spellSlots.length)
            throw new IllegalArgumentException("Attempt to get unavailable spell slot count");
        return spellSlots[level - 1];
    }

    public int getMaxSlot() {
        return spellSlots.length;
    }

    public AttributeType getAbilityScore() {
        return spellAbilityScore;
    }

    public void addLearntSpell(Spell spell) {
        if (spell.isCantrip())
            throw new IllegalArgumentException("Cantrips cannot be learnt as spellcasting");
        learntSpells.add(spell);
    }

    public boolean hasLearntSpell(Spell spell) {
        return learntSpells.contains(spell);
    }

    public Stream<Spell> getLearntSpells() {
        return learntSpells.stream();
    }

    @Override
    public String toString() {
        return name + " Spellcasting";
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.of(
            "<b>Slots</b<: " + Arrays.toString(spellSlots),
            "<b>Spell ability modifier</b>: " + getModifier(character),
            "<b>Spell difficulty check</b>: " + getSaveDC(character));
    }

    public int getSaveDC(Character character) {
        return 8 + getModifier(character);
    }

    public int getModifier(Character character) {
        if (character.hasAttribute(spellAbilityScore))
            return character.getProficiencyBonus() + character.getModifier(spellAbilityScore);
        else
            return 0;
    }

    public String getPreparedSpells(Character character) {
        return StringUtils.expand(preparedSpellText, character);
    }

    @Override
    public Node save(Document doc) {
        Node node = getType().save(doc);
        node.appendChild(doc.createElement("name"))
            .setTextContent(name);
        node.appendChild(doc.createElement("ability_score"))
            .setTextContent(spellAbilityScore.name());
        node.appendChild(doc.createElement("prepared_spells"))
            .setTextContent(preparedSpellText);
        for (int i = 0; i < spellSlots.length; i++) {
            Element slot = doc.createElement("spell_slot");
            slot.setAttribute("level", String.valueOf(i + 1));
            slot.setTextContent(String.valueOf(spellSlots[i]));
            node.appendChild(slot);
        }
        learntSpells.forEach(spell -> node.appendChild(doc.createElement("learnt_spell"))
            .setTextContent(spell.name()));
        return node;
    }

    public static SpellCasting load(Node node) {
        String name = Savable.child(node, "name").getTextContent();
        AttributeType abilityScore
            = AttributeType.valueOf(Savable.child(node, "ability_score").getTextContent());
        String preparedSpellText = Savable.child(node, "prepared_spells").getTextContent();
        SpellCasting casting = new SpellCasting(name, abilityScore, preparedSpellText);
        Savable.children(node)
            .filter(el -> el.getTagName().equals("spell_slot"))
            .forEach(el -> loadSpellSlot(casting, el));
        Savable.children(node)
            .filter(el -> el.getTagName().equals("learnt_spell"))
            .forEach(el -> casting.addLearntSpell(Spell.valueOf(el.getTextContent())));
        return casting;
    }

    private static void loadSpellSlot(SpellCasting casting, Element element) {
        casting.addSlots(
            Integer.valueOf(element.getAttribute("level")),
            Integer.valueOf(element.getTextContent()));
    }

    @Override
    public int hashCode() {
        return spellAbilityScore.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        SpellCasting other = (SpellCasting) obj;
        return other.name.equals(name)
            && other.spellAbilityScore.equals(spellAbilityScore)
            && other.preparedSpellText.equals(preparedSpellText)
            && Arrays.equals(other.spellSlots, spellSlots)
            && other.learntSpells.equals(learntSpells);
    }

}
