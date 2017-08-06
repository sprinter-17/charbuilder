package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.saveload.Savable;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SpellCasting implements Attribute {

    private final AttributeType spellAbilityScore;
    private final String preparedSpellText;
    private int[] spellSlots = {};

    public SpellCasting(AttributeType spellAbilityScore) {
        this(spellAbilityScore, "All");
    }

    public SpellCasting(AttributeType spellAbilityScore, String preparedSpellText) {
        this.spellAbilityScore = spellAbilityScore;
        this.preparedSpellText = preparedSpellText;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.SPELLCASTING;
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

    @Override
    public String toString() {
        return "Spell casting using " + spellAbilityScore.toString();
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
        return node;
    }

    public static SpellCasting load(Node node) {
        AttributeType abilityScore
            = AttributeType.valueOf(Savable.child(node, "ability_score").getTextContent());
        String preparedSpellText = Savable.child(node, "prepared_spells").getTextContent();
        SpellCasting casting = new SpellCasting(abilityScore, preparedSpellText);
        Savable.children(node)
            .filter(el -> el.getTagName().equals("spell_slot"))
            .forEach(el -> loadSpellSlot(casting, el));
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
        return other.spellAbilityScore.equals(spellAbilityScore)
            && other.preparedSpellText.equals(preparedSpellText)
            && Arrays.equals(other.spellSlots, spellSlots);
    }

}
