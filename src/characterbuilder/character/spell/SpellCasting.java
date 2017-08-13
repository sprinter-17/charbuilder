package characterbuilder.character.spell;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.NoOption;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.saveload.Savable;
import characterbuilder.utils.StringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SpellCasting implements Attribute {

    private class ReplaceSpell extends OptionChoice {

        public ReplaceSpell() {
            super("Replace " + name + " Spell");
        }

        @Override
        public void select(Character character, ChoiceSelector selector) {
            selector.chooseOption(
                Stream.concat(Stream.of(NoOption.NONE), getLearntSpells()), opt -> {
                if (opt instanceof Spell) {
                    Spell spell = (Spell) opt;
                    removeLearntSpell(spell);
                    choice.useAndCheck();
                }
            });
        }

        @Override
        public boolean isAllowed(Character character) {
            return !learntSpells.isEmpty();
        }
    }

    private class ChooseSpell extends OptionChoice {

        public ChooseSpell() {
            super(name + " Spell", Integer.MAX_VALUE);
        }

        @Override
        public void select(Character character, ChoiceSelector selector) {
            selector.chooseOption(spellClass.getSpells()
                .filter(sp -> !sp.isCantrip())
                .filter(sp -> sp.getLevel() <= getMaxSlot())
                .filter(sp -> !hasLearntSpell(sp)),
                SpellCasting.this::addLearntSpell);
        }

        @Override
        public boolean useAndCheck() {
            withCount(knownSpells - learntSpells.size());
            return false;
        }

        @Override
        public boolean isAllowed(Character character) {
            return character.hasAttribute(spellAbilityScore) && knownSpells > learntSpells.size();
        }
    }

    private final String name;
    private final AttributeType spellAbilityScore;
    private final String preparedSpellText;
    private final CharacterClass spellClass;
    private final List<Spell> learntSpells = new ArrayList<>();
    private final ChooseSpell choice;
    private boolean learnAll = false;
    private int knownSpells = 0;
    private int[] spellSlots = {};

    public SpellCasting(String name, AttributeType spellAbilityScore,
        CharacterClass spellClass, String preparedSpellText) {
        this.name = name;
        this.spellAbilityScore = spellAbilityScore;
        this.spellClass = spellClass;
        this.preparedSpellText = preparedSpellText;
        this.choice = new ChooseSpell();
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

    public void setSlots(int level, int slots) {
        this.spellSlots = new int[level];
        spellSlots[level - 1] = slots;
    }

    public void learnAllSpells() {
        this.learnAll = true;
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

    public void replaceSpell(Character character) {
        character.pushChoice(new ReplaceSpell());
    }

    public void addKnownSpells(Character character, int count) {
        knownSpells += count;
        choice.useAndCheck();
    }

    public void addLearntSpell(Spell spell) {
        if (spell.isCantrip())
            throw new IllegalArgumentException("Cantrips cannot be learnt as spellcasting");
        learntSpells.add(spell);
        choice.useAndCheck();
    }

    public void removeLearntSpell(Spell spell) {
        if (!hasLearntSpell(spell))
            throw new IllegalArgumentException("Attempt to remove non-existent spell " + spell);
        learntSpells.remove(spell);
    }

    public boolean hasLearntSpell(Spell spell) {
        return getLearntSpells().anyMatch(spell::equals);
    }

    public Stream<Spell> getLearntSpells() {
        return Stream.concat(getLearnAllSpells(), learntSpells.stream());
    }

    private Stream<Spell> getLearnAllSpells() {
        if (learnAll)
            return spellClass.getSpells()
                .filter(sp -> sp.getLevel() <= getMaxSlot() && !sp.isCantrip());
        else
            return Stream.empty();
    }

    @Override
    public void generateLevelChoices(Character character) {
        character.addChoice(choice);
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

    public String getPreparedSpellText(Character character) {
        return StringUtils.expand(preparedSpellText, character);
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.appendChild(doc.createElement("name"))
            .setTextContent(name);
        element.appendChild(doc.createElement("ability_score"))
            .setTextContent(spellAbilityScore.name());
        element.appendChild(doc.createElement("spell_class"))
            .setTextContent(spellClass.name());
        element.appendChild(doc.createElement("prepared_spells"))
            .setTextContent(preparedSpellText);
        element.setAttribute("learn_all", Boolean.toString(learnAll));
        for (int i = 0; i < spellSlots.length; i++) {
            Element slot = doc.createElement("spell_slot");
            slot.setAttribute("level", String.valueOf(i + 1));
            slot.setTextContent(String.valueOf(spellSlots[i]));
            element.appendChild(slot);
        }
        learntSpells.forEach(spell -> element.appendChild(doc.createElement("learnt_spell"))
            .setTextContent(spell.name()));
        return element;
    }

    public static SpellCasting load(Element element) {
        String name = Savable.child(element, "name").getTextContent();
        AttributeType abilityScore
            = AttributeType.valueOf(Savable.child(element, "ability_score").getTextContent());
        String preparedSpellText = Savable.child(element, "prepared_spells").getTextContent();
        CharacterClass spellClass
            = CharacterClass.valueOf(Savable.child(element, "spell_class").getTextContent());
        SpellCasting casting = new SpellCasting(name, abilityScore, spellClass, preparedSpellText);
        if (Boolean.valueOf(element.getAttribute("learn_all")))
            casting.learnAllSpells();
        if (Boolean.valueOf(element.getAttribute("learn_all")))
            casting.learnAllSpells();
        Savable.children(element)
            .filter(el -> el.getTagName().equals("spell_slot"))
            .forEach(el -> loadSpellSlot(casting, el));
        Savable.children(element)
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
            && other.spellClass.equals(spellClass)
            && other.learnAll == learnAll
            && other.learntSpells.equals(learntSpells);
    }

}
