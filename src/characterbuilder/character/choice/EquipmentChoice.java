package characterbuilder.character.choice;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.equipment.Armour;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;
import characterbuilder.character.equipment.EquipmentType;
import characterbuilder.character.equipment.MusicalInstrument;
import characterbuilder.character.equipment.Weapon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

public class EquipmentChoice extends OptionChoice {

    private final List<Equipment> equipmentList = new ArrayList<>();

    private class MultiEquipment implements Equipment {

        private final List<Equipment> components = new ArrayList<>();

        public MultiEquipment(Equipment... components) {
            Arrays.stream(components).forEach(this.components::add);
        }

        @Override
        public Stream<Equipment> getComponents() {
            return components.stream();
        }

        @Override
        public EquipmentCategory getCategory() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Weight getWeight() {
            throw new UnsupportedOperationException("Multi equipment for selection only");
        }

        @Override
        public Value getValue() {
            throw new UnsupportedOperationException("Multi equipment for selection only");
        }

        @Override
        public String encode() {
            throw new UnsupportedOperationException("Multi equipment for selection only");
        }

        @Override
        public String toString() {
            return components.stream().map(Equipment::toString).collect(joining(", "));
        }
    }

    public EquipmentChoice(EquipmentCategory category) {
        super(category.toString());
        with(category);
    }

    public EquipmentChoice(String name, Equipment... equipment) {
        super(name);
        Arrays.stream(equipment).forEach(equipmentList::add);
    }

    public EquipmentChoice with(Equipment equipment) {
        equipmentList.add(equipment);
        return this;
    }

    public EquipmentChoice with(EquipmentCategory category) {
        Stream.of(EquipmentType.values(), Weapon.values(), Armour.values(),
            MusicalInstrument.values())
            .flatMap(Arrays::stream)
            .filter(eq -> eq.getCategory().equals(category))
            .forEach(equipmentList::add);
        return this;
    }

    public EquipmentChoice with(Equipment... equipment) {
        equipmentList.add(new MultiEquipment(equipment));
        return this;
    }

    @Override
    public void select(Character character, ChoiceSelector selector) {
        selector.chooseOption(equipmentList.stream(), eq -> {
            eq.choose(character);
        });
    }

}
