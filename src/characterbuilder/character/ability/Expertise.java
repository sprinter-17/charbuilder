package characterbuilder.character.ability;

import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import java.util.Objects;

public class Expertise implements Attribute {

    private final Attribute attribute;

    public Expertise(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.EXPERTISE;
    }

    public boolean isFor(Attribute attribute) {
        return this.attribute.equals(attribute);
    }

    @Override
    public String toString() {
        return attribute.toString();
    }

    @Override
    public String encode() {
        return attribute.getType().name() + ":" + attribute.encode();
    }

    public static Expertise valueOf(String code) {
        String[] components = code.split(":");
        AttributeType type = AttributeType.valueOf(components[0]);
        return new Expertise(type.decode(components[1]));
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.attribute);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass())
            return false;
        else
            return this.attribute.equals(((Expertise) obj).attribute);
    }

}
