package characterbuilder.character.ability;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class AttributeChoice {

    private final String name;
    private int count;
    private final List<Attribute> attributes = new ArrayList<>();

    public AttributeChoice(Attribute attribute) {
        this(attribute.toString(), 1, Collections.singletonList(attribute));
    }

    public AttributeChoice(String name, int count, List<Attribute> attributes) {
        this.name = name;
        this.count = count;
        this.attributes.addAll(attributes);
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public Stream<Attribute> getAttributes() {
        return attributes.stream();
    }

    private boolean matches(AttributeChoice other) {
        return this.name.equals(other.name)
                && this.attributes.containsAll(other.attributes)
                && other.attributes.containsAll(this.attributes);
    }

    public void add(AttributeChoice other) {
        if (matches(other)) {
            count += other.count;
            other.count = 0;
        }
    }

    public void removeExisting(Character character, List<Attribute> used) {
        count -= character.getAllAttributes()
                .filter(attributes::contains)
                .filter(ab -> checkUsed(ab, used))
                .count();
        count = Math.min(count, attributes.size());
    }

    private boolean checkUsed(Attribute attribute, List<Attribute> used) {
        attributes.remove(attribute);
        if (used.contains(attribute))
            return false;
        used.add(attribute);
        return true;
    }

    public String toString() {
        return count + " x " + name;
    }
}
