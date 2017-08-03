package characterbuilder.character.attribute;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class AttributeSet {

    private final Set<Attribute> attributes = new HashSet<>();

    public void addAttribute(Attribute attribute) {
        if (!attribute.getSuperSet().isPresent() || !hasAttribute(attribute.getSuperSet().get())) {
            if (attribute.getType().isUnique() && hasAttribute(attribute.getType()))
                throw new IllegalStateException("Attempt to add unique attribute " + attribute.
                    getType() + " twice");
            attributes.removeIf(att -> att.getSuperSet().isPresent()
                && att.getSuperSet().get().equals(attribute));
            attributes.add(attribute);
        }
    }

    public boolean removeAttribute(Attribute attribute) {
        return attributes.remove(attribute);
    }

    public Stream<Attribute> getAllAttributes() {
        return attributes.stream();
    }

    public <T extends Attribute> T getAttribute(AttributeType type) {
        return (T) getAttributes(type)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("Non-existent attribute type " + type));
    }

    public <T extends Attribute> Stream<T> getAttributes(AttributeType type, Class<T> attrClass) {
        return getAllAttributes()
            .filter(type::isTypeOfAttribute)
            .map(attr -> (T) attr);
    }

    public <T extends Attribute> Stream<T> getAttributes(AttributeType type) {
        assert type != null;
        return getAllAttributes()
            .filter(type::isTypeOfAttribute)
            .map(attr -> (T) attr);
    }

    public boolean hasAttribute(AttributeType type) {
        return getAttributes(type).findAny().isPresent();
    }

    public boolean hasAttribute(Attribute attribute) {
        return getAllAttributes().anyMatch(attribute::equals)
            || (attribute.getSuperSet().isPresent() && hasAttribute(attribute.getSuperSet().get()));
    }

    public boolean removeAttributesOfType(AttributeType type) {
        return attributes.removeIf(type::isTypeOfAttribute);
    }
}
