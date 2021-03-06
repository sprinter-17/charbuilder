package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.choice.Option;
import java.util.Optional;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface Equipment extends Option {

	default Stream<Equipment> getComponents() {
		return Stream.of(this);
	}

	EquipmentCategory getCategory();

	default Equipment getBaseEquipment() {
		return this;
	}

	default EquipmentSet makeSet(int count) {
		return new EquipmentSet(this, count);
	}

	default int getCount() {
		return 1;
	}

	default int getBonus() {
		return 0;
	}

	default Optional<Weapon> asWeapon() {
		return Optional.empty();
	}

	default Optional<MagicItem> asMagicItem() {
		return Optional.empty();
	}

	@Override
	public default void choose(Character character) {
		character.addEquipment(this);
	}

	Weight getWeight();

	Value getValue();

	@Override
	public default Stream<String> getDescription(Character character) {
		return Stream.of("Cost " + getValue().toString() + " Weight " + getWeight().toString());
	}

	@Override
	public default Element save(Document doc) {
		if (this instanceof Enum) {
			Element element = doc.createElement(getCategory().name().toLowerCase());
			element.setTextContent(((Enum<?>) this).name());
			return element;
		} else {
			throw new AbstractMethodError("object " + getClass().getName() + " does not have save");
		}
	}
}
