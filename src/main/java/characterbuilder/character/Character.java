package characterbuilder.character;

import characterbuilder.character.ability.Feat;
import characterbuilder.character.attribute.AbilityScore;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeSet;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.attribute.DamageType;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.attribute.StringAttribute;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.characterclass.CharacterClass;
import characterbuilder.character.characterclass.CharacterClassLevel;
import characterbuilder.character.characterclass.barbarian.BarbarianAbility;
import characterbuilder.character.characterclass.monk.MonkAbility;
import characterbuilder.character.characterclass.sorcerer.SorcererAbility;
import characterbuilder.character.choice.ChoiceList;
import characterbuilder.character.choice.ChoiceSelector;
import characterbuilder.character.choice.OptionChoice;
import characterbuilder.character.choice.UndoQueue;
import characterbuilder.character.equipment.Armour;
import characterbuilder.character.equipment.Attack;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.Inventory;
import characterbuilder.character.spell.LearntSpell;
import characterbuilder.character.spell.SpellCasting;
import characterbuilder.utils.StringUtils;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

public class Character {

    protected static final int[] XP_LEVELS = {0, 300, 900, 2700, 6500, 14000, 23000, 34000, 48000,
        64000, 85000, 100000, 120000, 140000, 165000, 195000, 225000, 265000, 305000, 355000};

    private final AttributeSet attributes = new AttributeSet();
    private final Inventory inventory = new Inventory();
    private ChoiceList choices;
    private boolean dirty = true;
    private Optional<CharacterClass> currentClass = Optional.empty();

    protected void setCurrentClass(CharacterClass characterClass) {
        this.currentClass = Optional.of(characterClass);
    }

    public boolean isIncreasingClass(CharacterClass characterClass) {
        return currentClass.isPresent() && currentClass.get() == characterClass;
    }

    public CharacterClass getCurrentClass() {
        return currentClass.orElseThrow(IllegalStateException::new);
    }

    public void addChoiceList(ChoiceSelector selector) {
        this.choices = new ChoiceList(selector);
    }

    public void addChoice(OptionChoice choice) {
        assert choices != null;
        choices.add(choice);
    }

    public void addChoice(int index, OptionChoice choice) {
        choices.add(index, choice);
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

    public boolean canUndo() {
        return choices.canUndo();
    }

    public UndoQueue.Element undoChoice() {
        return choices.undo();
    }

    public boolean hasChoice(OptionChoice choice) {
        return choices.has(choice);
    }

    public boolean hasChoice(String name) {
        assert choices != null;
        return getAllChoices().map(OptionChoice::getName).anyMatch(name::equals);
    }

    public boolean hasChoice(String name, int count) {
        assert choices != null;
        return getAllChoices().map(OptionChoice::toString)
            .anyMatch(String.format("%s (x%d)", name, count)::equals);
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

    public void addAttributeIfNotPresent(Attribute attribute) {
        if (!hasAttribute(attribute))
            addAttribute(attribute);
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
        if (!AbilityScore.SCORES.contains(type))
            throw new IllegalArgumentException("Getting ability score for other attribute");
        return getAttribute(type);
    }

    public <T extends Attribute> T getAttribute(AttributeType type) {
        if (!type.isUnique())
            throw new IllegalArgumentException("Getting value for non-unique attribute " + type);
        return attributes.getAttribute(type);
    }

    public <T extends Attribute> T getAttribute(AttributeType type, Class<T> attrClass) {
        if (!type.isUnique())
            throw new IllegalArgumentException("Getting value for non-unique attribute " + type);
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
        return getAttributes(CHARACTER_CLASS, CharacterClassLevel.class)
            .mapToInt(CharacterClassLevel::getLevel).sum();
    }

    public int getLevel(CharacterClass characterClass) {
        return getAttributes(CHARACTER_CLASS, CharacterClassLevel.class)
            .filter(ccl -> ccl.hasCharacterClass(characterClass))
            .mapToInt(CharacterClassLevel::getLevel).sum();
    }

    public void generateHitPoints() {
        if (getLevel() == 1) {
            CharacterClass characterClass = getCharacterClassLevels()
                .findFirst().get().getCharacterClass();
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
        if (hasAttribute(SorcererAbility.DRACONIC_RESILIENCE))
            hitPoints++;
        return Math.max(1, hitPoints);
    }

    public Stream<CharacterClassLevel> getCharacterClassLevels() {
        return getAttributes(CHARACTER_CLASS, CharacterClassLevel.class);
    }

    public String getClassLevelDescription() {
        return getCharacterClassLevels()
            .sorted(Comparator
                .comparingInt(CharacterClassLevel::getLevel).reversed()
                .thenComparing(CharacterClassLevel::toString))
            .map(CharacterClassLevel::toString)
            .collect(joining(" / "));
    }

    public Stream<CharacterClass> getCharacterClasses() {
        return getCharacterClassLevels().map(CharacterClassLevel::getCharacterClass);
    }

    public Stream<CharacterClass> allowedMultiClasses() {
        return Arrays.stream(CharacterClass.values())
            .filter(cc -> cc.hasMulticlassPrerequisites(this))
            .filter(cc -> getCharacterClasses().noneMatch(cc::equals));
    }

    public final void increaseLevel(CharacterClass characterClass, CharacterRandom random) {
        if (getLevel() == 20)
            throw new IllegalStateException("Attempt to increase level beyond 20");
        setCurrentClass(characterClass);
        increaseExperiencePoints();
        increaseHitPoints(characterClass, random);
        increaseClassLevel(characterClass);
        generateChoices(characterClass);
        setDirty();
    }

    private void increaseExperiencePoints() {
        int minXP = XP_LEVELS[getLevel()];
        if (!hasAttribute(EXPERIENCE_POINTS))
            addAttribute(new IntAttribute(EXPERIENCE_POINTS, 0));
        else if (getIntAttribute(EXPERIENCE_POINTS) < minXP)
            getAttribute(EXPERIENCE_POINTS, IntAttribute.class).setValue(minXP);
    }

    private void increaseHitPoints(CharacterClass characterClass, CharacterRandom random) {
        int hpIncrease = characterClass.getHitDie();
        if (hasAttribute(HIT_POINTS))
            getAttribute(HIT_POINTS, IntAttribute.class)
                .addValue(adjustHitPointIncrease(random.nextHitPoints(hpIncrease)));
    }

    private void increaseClassLevel(CharacterClass characterClass) {
        if (getCharacterClassLevel(characterClass).isPresent())
            getCharacterClassLevel(characterClass).get().increaseLevel();
        else
            addAttributes(new CharacterClassLevel(characterClass));
    }

    private void generateChoices(CharacterClass characterClass) {
        getCharacterClassLevel(characterClass)
            .orElseThrow(() -> new IllegalStateException("Generating choices for "
            + "non-existent class " + characterClass.toString()))
            .generateChoices(this);
        attributes.getAllAttributes()
            .collect(toList())
            .forEach(attr -> attr.generateLevelChoices(this));
    }

    private Optional<CharacterClassLevel> getCharacterClassLevel(CharacterClass characterClass) {
        return getCharacterClassLevels()
            .filter(ccl -> ccl.hasCharacterClass(characterClass))
            .findAny();
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
        return getCharacterClassLevels()
            .collect(Collectors.groupingBy(ccl -> ccl.getCharacterClass().getHitDie(),
                Collectors.summingInt(ccl -> ccl.getLevel())))
            .entrySet().stream()
            .sorted(Comparator.comparing(Map.Entry::getKey, Comparator.reverseOrder()))
            .map(e -> e.getValue() + "d" + e.getKey())
            .collect(joining("/"));
    }

    public int getHitPoints() {
        return getIntAttribute(HIT_POINTS);
    }

    public void addEquipment(Equipment equipment) {
        inventory.addItem(equipment);
        setDirty();
    }

    public void removeEquipment(Equipment equipment) {
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

    public Stream<Attack> getAttacks() {
        return Stream.of(getUnarmouredAttacks(), getWeaponAttacks(), getSpellAttacks())
            .flatMap(Function.identity());
    }

    private Stream<Attack> getUnarmouredAttacks() {
        String name = "Unarmoured";
        int abilityBonus = getModifier(STRENGTH);
        String damage = String.valueOf(1 + abilityBonus);
        if (hasAttribute(MonkAbility.MARTIAL_ARTS)) {
            abilityBonus = Math.max(abilityBonus, getModifier(DEXTERITY));
            damage = StringUtils.expand("[max($monk_level 1:1d4,5:1d6,11:1d8,17:1d10)"
                + "bonus(max($str_mod,$dex_mod))]", this);
        }
        return Stream.of(
            new Attack(name, "5", getProficiencyBonus() + abilityBonus,
                damage, DamageType.BLUDGEONING));
    }

    private Stream<Attack> getWeaponAttacks() {
        return inventory.getWeapons().flatMap(weapon -> weapon.getAttacks(this));
    }

    private Stream<Attack> getSpellAttacks() {
        return getAttributes(SPELL_ABILITY, LearntSpell.class)
            .map(sa -> sa.getAttack(this))
            .filter(Optional::isPresent).map(Optional::get);
    }

    public Weight getInventoryWeight() {
        return inventory.getWeight();
    }

    public Weight getCarryingCapacity() {
        Weight capacity = Weight.lb(getIntAttribute(STRENGTH) * 15);
        if (hasAttribute(BarbarianAbility.ASPECT_OF_BEAST_BEAR))
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
        if (hasAttribute(BarbarianAbility.FAST_MOVEMENT) && !Armour.bestArmour(this).isPresent())
            speed += 10;
        if (hasAttribute(Feat.MOBILE))
            speed += 10;
        return speed;
    }

    public String getErrors() {
        return Stream.of(NAME, TRAIT, IDEAL, BOND, FLAW, PHYSICAL_DESCRIPTION, PERSONAL_HISTORY)
            .filter(this::isEmpty)
            .map(AttributeType::toString)
            .collect(joining(", "));
    }

    private boolean isEmpty(AttributeType at) {
        return getAttributes(at, StringAttribute.class)
            .map(StringAttribute::toString)
            .allMatch(String::isEmpty);
    }

    public Character copy() {
        Character copy = new Character();
        copy.attributes.copyFrom(attributes);
        copy.inventory.copyFrom(inventory);
        copy.choices = choices.copy();
        copy.dirty = dirty;
        return copy;
    }
}
