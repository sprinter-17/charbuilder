package characterbuilder.character.equipment;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Value;
import characterbuilder.character.attribute.Weight;
import characterbuilder.utils.StringUtils;
import java.util.Optional;
import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MagicItem implements Equipment {

    private final Equipment baseItem;
    private final int bonus;
    private Optional<String> ability = Optional.empty();

    public MagicItem(Equipment baseItem, int bonus) {
        this.baseItem = baseItem;
        this.bonus = bonus;
    }

    public void setAbility(String ability) {
        this.ability = Optional.of(ability);
    }

    @Override
    public Equipment getBaseEquipment() {
        return baseItem;
    }

    @Override
    public int getBonus() {
        return bonus;
    }

    @Override
    public EquipmentCategory getCategory() {
        return baseItem.getCategory();
    }

    @Override
    public Weight getWeight() {
        return baseItem.getWeight();
    }

    @Override
    public Value getValue() {
        return baseItem.getValue();
    }

    @Override
    public Optional<Weapon> asWeapon() {
        return baseItem.asWeapon();
    }

    public Optional<String> getAbility(Character character) {
        return ability.map(ab -> StringUtils.expand(ab, character));
    }

    @Override
    public String toString() {
        if (bonus != 0)
            return String.format("%+d ", bonus) + baseItem.toString();
        else
            return baseItem.toString();
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return Stream.concat(Equipment.super.getDescription(character),
            ability.isPresent()
            ? Stream.of(StringUtils.expand(ability.get(), character))
            : Stream.empty());
    }

    @Override
    public Element save(Document doc) {
        Element element = baseItem.save(doc);
        element.setAttribute("bonus", Integer.toString(bonus));
        if (ability.isPresent())
            element.setAttribute("ability", ability.get());
        return element;
    }

    public static MagicItem load(Equipment baseItem, Element element) {
        int bonus = Integer.valueOf(element.getAttribute("bonus"));
        MagicItem item = new MagicItem(baseItem, bonus);
        if (element.hasAttribute("ability"))
            item.setAbility(element.getAttribute("ability"));
        return item;
    }

    @Override
    public int hashCode() {
        return baseItem.hashCode() + bonus * 97;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        final MagicItem other = (MagicItem) obj;
        return this.baseItem.equals(other.baseItem)
            && this.bonus == other.bonus
            && this.ability.equals(other.ability);
    }

}
