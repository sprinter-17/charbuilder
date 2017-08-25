package characterbuilder.character;

import characterbuilder.character.ability.Feat;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeSet;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.characterclass.barbarian.Barbarian;
import static characterbuilder.character.characterclass.barbarian.Barbarian.Ability.FAST_MOVEMENT;
import characterbuilder.character.choice.ChoiceList;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.equipment.Armour;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentSet;
import characterbuilder.character.equipment.Inventory;
import characterbuilder.character.equipment.Weapon;
import characterbuilder.character.spell.SpellCasting;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Character {

    protected static final int[] XP_LEVELS = {0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000,
        64000, 85000, 100000, 120000, 140000, 165000, 195000, 225000, 265000, 305000, 355000};

    private final AttributeSet attributes = new AttributeSet();
    private final Inventory inventory = new Inventory();
    private ChoiceList choices;

    private boolean dirty = true;

    public void addChoiceList(ChoiceSelector selector) {
        this.choices = new ChoiceList(selector);
    }

    public void addChoice(OptionChoice choice) {
        assert choices != null;
        choices.add(choice);
    }

    public void pushChoice(OptionChoice choice) {
        choices.push(choice);
    }

    public OptionChoice getChoice(int index) {
        assert choices != null;
        return getAllChoices().skip(index).findFirst()
            .orElseThrow(() -> new IndexOutOfBoundsException("Choice index out of bounds"));
    }

    public Stream<OptionChoice> getAllChoices() {
        return choices.stream().filter(oc -> oc.isAllowed(this));
    }

    public int getChoiceCount() {
        assert choices != null;
        return (int) getAllChoices().count();
    }

    public void selectChoice(OptionChoice choice) {
        assert choices != null;
        choices.select(this, choice);
    }

    public boolean hasChoice(OptionChoice choice) {
        return choices.has(choice);
    }

    public boolean hasChoice(String name) {
        assert choices != null;
        return getAllChoices().map(Object::toString).anyMatch(name::equals);
    }

    public void generateAbilityScores(CharacterRandom random) {
        if (!hasAttribute(CHARACTER_CLASS) || !hasAttribute(RACE))
            throw new IllegalStateException("Generate ability score without class and race");
        CharacterClass characterClass = getAttribute(CHARACTER_CLASS);
        List<Integer> abilityScores = IntStream
            .generate(random::nextAbilityScore).limit(6L).boxed()
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.toList());
        characterClass.getPrimaryAttributes()
            .forEach(attr -> addAttribute(new AbilityScore(attr, abilityScores.remove(0))));
        Collections.shuffle(abilityScores);
        AbilityScore.SCORES.stream()
            .filter(as -> !hasAttribute(as))
            .forEach(as -> addAttribute(new AbilityScore(as, abilityScores.remove(0))));
        Race race = getAttribute(RACE);
        AbilityScore.SCORES
            .forEach(attr -> getScore(attr).addValue(race.getAttributeAdjustment(attr)));
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty() {
        dirty = true;
    }

    public void clearDirty() {
        dirty = false;
    }

    public SpellCasting getSpellCasting(String name) {
        return getAttributes(AttributeType.SPELLCASTING, SpellCasting.class)
            .filter(sc -> sc.hasName(name))
            .findAny().orElseThrow(() -> new IllegalArgumentException("No spellcasting " + name));
    }

    public void addAttributes(Attribute... attributes) {
        Arrays.stream(attributes).forEach(this::addAttribute);
    }

    public void addAttribute(Attribute attribute) {
        attributes.addAttribute(attribute);
        setDirty();
    }

    public void removeAttribute(Attribute attribute) {
        dirty = attributes.removeAttribute(attribute) || isDirty();
    }

    public boolean hasAttribute(AttributeType type) {
        return attributes.hasAttribute(type);
    }

    public boolean hasAttribute(Attribute attribute) {
        return attributes.hasAttribute(attribute);
    }

    public void removeAttributesOfType(AttributeType type) {
        dirty = attributes.removeAttributesOfType(type) || isDirty();
    }

    public int getAttributeCount(AttributeType type) {
        return (int) attributes.getAttributes(type).count();
    }

    public AbilityScore getScore(AttributeType type) {
        return getAttribute(type);
    }

    public <T extends Attribute> T getAttribute(AttributeType type) {
        return attributes.getAttribute(type);
    }

    public <T extends Attribute> T getAttribute(AttributeType type, Class<T> attrClass) {
        if (!hasAttribute(type))
            throw new IllegalArgumentException("Attempt to retrieve non-existent attribute " + type);
        return attributes.getAttribute(type);
    }

    public <T extends Attribute> Stream<T> getAttributes(AttributeType type, Class<T> attrClass) {
        return attributes.getAttributes(type, attrClass);
    }

    public int getIntAttribute(AttributeType type) {
        if (!hasAttribute(type))
            throw new IllegalArgumentException("Attempt to get non-existent int attribute " + type);
        IntAttribute attribute = getAttribute(type);
        return attribute.getValue();
    }

    public String getStringAttribute(AttributeType type) {
        return getAttribute(type).toString();
    }

    public Stream<Attribute> getAllAttributes() {
        return attributes.getAllAttributes();
    }

    public int getModifier(AttributeType abilityScore) {
        if (hasAttribute(abilityScore))
            return getIntAttribute(abilityScore) / 2 - 5;
        else
            return 0;
    }

    public int getSavingsThrowBonus(AttributeType type) {
        int modifier = getModifier(type);
        if (hasAttribute(type) && getScore(type).hasProficientSaves())
            modifier += getProficiencyBonus();
        return modifier;
    }

    public void swapAttributes(AttributeType attribute1, AttributeType attribute2) {
        if (!hasAttribute(attribute1) || !hasAttribute(attribute2))
            throw new IllegalArgumentException("Attempt to swap non-existent attributes");
        if (!attribute1.equals(attribute2)) {
            Race race = getAttribute(RACE);
            AbilityScore score1 = getAttribute(attribute1);
            AbilityScore score2 = getAttribute(attribute2);
            int mod1 = race.getAttributeAdjustment(attribute1);
            int mod2 = race.getAttributeAdjustment(attribute2);
            int unadjusted1 = score1.getValue() - mod1;
            int unadjusted2 = score2.getValue() - mod2;
            score1.setValue(unadjusted2 + mod1);
            score2.setValue(unadjusted1 + mod2);
            setDirty();
        }
    }

    public int getLevel() {
        return hasAttribute(LEVEL) ? getIntAttribute(LEVEL) : 0;
    }

    public void generateHitPoints() {
        if (getLevel() == 1) {
            CharacterClass characterClass = getAttribute(CHARACTER_CLASS);
            int hp = adjustHitPointIncrease(characterClass.getHitDie());
            addAttribute(new IntAttribute(HIT_POINTS, hp));
            setDirty();
        }
    }

    private int adjustHitPointIncrease(int hitPoints) {
        hitPoints += getModifier(CONSTITUTION);
        if (getAttribute(RACE).equals(Race.HILL_DWARF))
            hitPoints++;
        if (hasAttribute(Feat.TOUGH))
            hitPoints += 2;
        return Math.max(1, hitPoints);
    }

    public final void increaseLevel(CharacterRandom random) {
        checkAttributes(CHARACTER_CLASS, LEVEL, EXPERIENCE_POINTS, CONSTITUTION, HIT_POINTS);
        IntAttribute level = getAttribute(LEVEL);
        IntAttribute xp = getAttribute(EXPERIENCE_POINTS);
        CharacterClass characterClass = getAttribute(CHARACTER_CLASS);
        IntAttribute hp = getAttribute(HIT_POINTS);
        if (level.getValue() < 20) {
            xp.setValue(XP_LEVELS[level.getValue()]);
            level.addValue(1);
            int hpIncrease = random.nextHitPoints(characterClass.getHitDie());
            hp.addValue(adjustHitPointIncrease(hpIncrease));
            attributes.getAllAttributes()
                .collect(toList())
                .forEach(attr -> attr.generateLevelChoices(this));
            setDirty();
        }
    }

    private void checkAttributes(AttributeType... attributes) {
        for (AttributeType attr : attributes) {
            if (!hasAttribute(attr))
                throw new AssertionError("Missing mandatory attribute " + attr);
        }
    }

    public int getProficiencyBonus() {
        return (getLevel() - 1) / 4 + 2;
    }

    public String getHitDice() {
        checkAttributes(CHARACTER_CLASS);
        CharacterClass characterClass = getAttribute(CHARACTER_CLASS);
        return getLevel() + "d" + characterClass.getHitDie();
    }

    public int getHitPoints() {
        return getIntAttribute(HIT_POINTS);
    }

    public void addEquipment(Equipment equipment) {
        inventory.addItem(equipment);
        setDirty();
    }

    public void removeEquipment(EquipmentSet equipment) {
        inventory.removeItem(equipment);
        setDirty();
    }

    public boolean hasEquipment(Equipment equipment) {
        return inventory.containsItem(equipment);
    }

    public Stream<Equipment> getItemisedEquipment() {
        return inventory.getEquipment().flatMap(Equipment::getComponents);
    }

    public Stream<Equipment> getInventory() {
        return inventory.getEquipment();
    }

    public Stream<Weapon> getWeapons() {
        return inventory.getWeapons();
    }

    public Weight getInventoryWeight() {
        return inventory.getWeight();
    }

    public Weight getCarryingCapacity() {
        Weight capacity = Weight.lb(getIntAttribute(STRENGTH) * 15);
        if (hasAttribute(Barbarian.Ability.ASPECT_OF_BEAST_BEAR))
            capacity = capacity.times(2);
        return capacity;
    }

    public Value getTreasureValue() {
        return inventory.getTreasureValue();
    }

    public void spendTreasure(Value value) {
        inventory.spendTreasure(value);
    }

    public int getSpeed() {
        Race race = getAttribute(RACE);
        int speed = race.getSpeed();
        if (hasAttribute(FAST_MOVEMENT) && !Armour.bestArmour(this).isPresent())
            speed += 10;
        if (hasAttribute(Feat.MOBILE))
            speed += 10;
        return speed;
    }

    public String getErrors() {
        return Stream.of(NAME, TRAIT, IDEAL, BOND, FLAW)
            .filter(at -> !hasAttribute(at) || getStringAttribute(at).isEmpty())
            .map(AttributeType::toString)
            .collect(joining(", "));
    }
}
