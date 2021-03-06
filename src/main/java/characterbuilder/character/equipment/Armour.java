package characterbuilder.character.equipment;

import static characterbuilder.character.attribute.Value.gp;
import static characterbuilder.character.attribute.Weight.lb;
import static characterbuilder.character.equipment.EquipmentCategory.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

import org.w3c.dom.Node;

import characterbuilder.character.Character;
import characterbuilder.character.ability.Feat;
import characterbuilder.character.ability.FightingStyle;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.characterclass.barbarian.BarbarianAbility;
import characterbuilder.character.characterclass.monk.MonkAbility;
import characterbuilder.character.characterclass.sorcerer.SorcererAbility;
import characterbuilder.utils.StringUtils;

public enum Armour implements Equipment {
	//
	PADDED_ARMOUR(LIGHT_ARMOUR, gp(5), lb(8), 1),
	LEATHER_ARMOUR(LIGHT_ARMOUR, gp(10), lb(10), 1),
	STUDDED_LEATHER_ARMOUR(LIGHT_ARMOUR, gp(45), lb(13), 2),
	//
	HIDE_ARMOUR(MEDIUM_ARMOUR, gp(10), lb(12), 2),
	CHAIN_SHIRT_ARMOUR(MEDIUM_ARMOUR, gp(50), lb(20), 3),
	SCALE_MAIL_ARMOUR(MEDIUM_ARMOUR, gp(50), lb(45), 4),
	BREASTPLATE_ARMOUR(MEDIUM_ARMOUR, gp(400), lb(20), 4),
	HALF_PLATE_ARMOUR(MEDIUM_ARMOUR, gp(750), lb(40), 5),
	//
	RING_MAIL_ARMOUR(HEAVY_ARMOUR, gp(30), lb(40), 4),
	CHAIN_MAIL_ARMOUR(HEAVY_ARMOUR, gp(75), lb(55), 6),
	SPLINT_ARMOUR(HEAVY_ARMOUR, gp(200), lb(60), 7),
	PLATE_ARMOUR(HEAVY_ARMOUR, gp(1500), lb(65), 8),
	//
	SHIELD(EquipmentCategory.SHIELD, gp(10), lb(6), 2), //
	;

	private final EquipmentCategory category;
	private final Value cost;
	private final Weight weight;
	private final int armourClass;

	private Armour(EquipmentCategory category, Value cost, Weight weight, int armourClass) {
		this.category = category;
		this.cost = cost;
		this.weight = weight;
		this.armourClass = armourClass;
	}

	@Override
	public EquipmentCategory getCategory() {
		return category;
	}

	@Override
	public Weight getWeight() {
		return weight;
	}

	@Override
	public Value getValue() {
		return cost;
	}

	@Override
	public String toString() {
		return StringUtils.capitaliseEnumName(name());
	}

	public static int getArmourClass(Character character) {
		int ac = 10;
		Optional<Armour> bestArmour = bestArmour(character);
		if (!bestArmour.isPresent() && character.hasAttribute(SorcererAbility.DRACONIC_RESILIENCE))
			ac = 13;
		ac += bestArmour.map(arm -> arm.armourClass + getBonus(character, arm)).orElse(0);
		if (character.hasEquipment(SHIELD))
			ac += 2 + getBonus(character, SHIELD);
		else if (!bestArmour.isPresent() && character.hasAttribute(MonkAbility.UNARMORED_DEFENCE))
			ac += character.getModifier(AttributeType.WISDOM);

		ac += bestArmour.map(armour -> armour.getDexBonus(character))
				.orElse(character.getModifier(AttributeType.DEXTERITY));

		if (bestArmour.isPresent() && character.hasAttribute(FightingStyle.DEFENSE))
			ac += 1;

		if (!bestArmour.isPresent() && character.hasAttribute(BarbarianAbility.UNARMORED_DEFENCE))
			ac += character.getModifier(AttributeType.CONSTITUTION);
		return ac;
	}

	private static int getBonus(Character character, Armour armour) {
		return character.getInventory()
				.filter(eq -> eq.getBaseEquipment().equals(armour))
				.mapToInt(Equipment::getBonus)
				.max()
				.orElse(0);
	}

	private int getDexBonus(Character character) {
		final int dexMod = character.getModifier(AttributeType.DEXTERITY);
		switch (getCategory()) {
		case HEAVY_ARMOUR:
			return 0;
		case MEDIUM_ARMOUR:
			return Math.min(dexMod, character.hasAttribute(Feat.MEDIUM_ARMOUR_MASTER) ? 3 : 2);
		case LIGHT_ARMOUR:
			return dexMod;
		default:
			throw new IllegalStateException("Getting dex mod for shield");
		}
	}

	public static Optional<Armour> bestArmour(Character character) {
		return Arrays.stream(values())
				.filter(arm -> !arm.equals(SHIELD))
				.filter(character::hasEquipment)
				.max(Comparator.comparing(arm -> arm.armourClass));
	}

	@Override
	public Stream<String> getDescription(Character character) {
		return Stream.concat(Equipment.super.getDescription(character),
				Stream.of(getCategory().toString(), "AC +" + armourClass));
	}

	public static Armour load(Node node) {
		return Armour.valueOf(node.getTextContent());
	}
}
