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
                Stream.concat(Stream.of(NoOption.NONE),
                    getLearntSpells().map(LearntSpell::getSpell)), opt -> {
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
            selector.chooseOption(getAvailableSpells()
                .filter(sp -> !sp.isCantrip())
                .filter(sp -> sp.getLevel() <= getMaxSlot())
                .filter(sp -> !hasLearntSpell(sp)),
                SpellCasting.this::addPreparedSpell);
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
    private final List<LearntSpell> learntSpells = new ArrayList<>();
    private final List<Spell> expandedSpells = new ArrayList<>();
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

    public void addKnownSpells(int count) {
        knownSpells += count;
        choice.useAndCheck();
    }

    public void addPreparedSpell(Spell spell) {
        addLearntSpell(spell, true);
    }

    public void addLearntSpell(Spell spell, boolean prepared) {
        if (spell.isCantrip())
            throw new IllegalArgumentException("Cantrips cannot be learnt as spellcasting");
        if (learntSpells.stream().anyMatch(ls -> ls.isSpell(spell)))
            throw new IllegalArgumentException("Have already learnt spell " + spell);
        learntSpells.add(new LearntSpell(spell, prepared));
        choice.useAndCheck();
    }

    public void removeLearntSpell(Spell spell) {
        if (!hasLearntSpell(spell))
            throw new IllegalArgumentException("Attempt to remove non-existent spell " + spell);
        learntSpells.removeIf(ls -> ls.isSpell(spell));
    }

    public boolean hasLearntSpell(Spell spell) {
        return getLearntSpells().anyMatch(lp -> lp.isSpell(spell));
    }

    public Stream<LearntSpell> getLearntSpells() {
        return Stream.concat(getLearnAllSpells(), learntSpells.stream());
    }

    private Stream<LearntSpell> getLearnAllSpells() {
        if (learnAll)
            return getAvailableSpells()
                .filter(sp -> sp.getLevel() <= getMaxSlot() && !sp.isCantrip())
                .filter(sp -> learntSpells.stream().noneMatch(ls -> ls.isSpell(sp)))
                .map(sp -> new LearntSpell(sp, false));
        else
            return Stream.empty();
    }

    public void addExpandedSpell(Spell spell) {
        expandedSpells.add(spell);
    }

    public Stream<Spell> getAvailableSpells() {
        return Stream.concat(spellClass.getSpells(), expandedSpells.stream()).distinct();
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
        learntSpells.forEach(spell -> {
            Element spellElement = doc.createElement("learnt_spell");
            spellElement.setTextContent(spell.getSpell().name());
            spellElement.setAttribute("prepared", Boolean.toString(spell.isPrepared()));
            element.appendChild(spellElement);
        });
        expandedSpells.forEach(spell -> element.appendChild(doc.createElement("expanded_spell"))
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
        Savable.children(element, "spell_slot")
            .forEach(el -> loadSpellSlot(casting, el));
        Savable.children(element, "learnt_spell")
            .forEach(el -> {
                Spell spell = Spell.valueOf(el.getTextContent());
                boolean prepared = Boolean.valueOf(el.getAttribute("prepared"));
                casting.addLearntSpell(spell, prepared);
            });
        Savable.children(element, "expanded_spell")
            .map(Element::getTextContent).map(Spell::valueOf)
            .forEach(casting::addExpandedSpell);
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
            && other.learntSpells.equals(learntSpells)
            && other.expandedSpells.equals(expandedSpells);
    }

}
