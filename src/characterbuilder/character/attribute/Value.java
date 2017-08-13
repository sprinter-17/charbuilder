package characterbuilder.character.attribute;

import characterbuilder.character.saveload.Savable;
import static characterbuilder.character.saveload.Savable.text;
import characterbuilder.utils.StringUtils;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Value implements Savable {

    public static final Value ZERO = new Value(0);
    public static final Value CP = new Value(1);
    public static final Value SP = CP.times(10);
    public static final Value GP = SP.times(10);
    private final int cpAmount;

    public static class ValueFormatException extends Exception {

        public ValueFormatException(String code) {
            super("\"" + code + "\" is not in correct value format");
        }

    }

    public static Value cp(int count) {
        return CP.times(count);
    }

    public static Value sp(int count) {
        return SP.times(count);
    }

    public static Value gp(int count) {
        return GP.times(count);
    }

    private Value(int cpAmount) {
        this.cpAmount = cpAmount;
    }

    public Value add(Value other) {
        return new Value(this.cpAmount + other.cpAmount);
    }

    public Value subtract(Value other) {
        return new Value(this.cpAmount - other.cpAmount);
    }

    public Value times(int multiple) {
        return new Value(cpAmount * multiple);
    }

    public boolean isGreaterThan(Value other) {
        return cpAmount > other.cpAmount;
    }

    @Override
    public String toString() {
        return new StringUtils.UnitBuilder(cpAmount)
            .withUnit(GP.cpAmount, "GP")
            .withUnit(SP.cpAmount, "SP")
            .withUnit(CP.cpAmount, "CP")
            .toString();
    }

    public static Value valueOf(String code) throws ValueFormatException {
        if (code.equals("-"))
            return ZERO;
        final Pattern format
            = Pattern.compile("^((?<gp>\\d+)GP ?)?((?<sp>\\d+)SP ?)?((?<cp>\\d+)CP)?$");
        Matcher matcher = format.matcher(code);
        if (matcher.matches()) {
            return valueOf(matcher.group("gp"), GP)
                .add(valueOf(matcher.group("sp"), SP))
                .add(valueOf(matcher.group("cp"), CP));
        } else {
            throw new ValueFormatException(code);
        }
    }

    private static Value valueOf(String value, Value unit) {
        return Optional.ofNullable(value)
            .map(Integer::valueOf)
            .map(unit::times)
            .orElse(ZERO);
    }

    @Override
    public Element save(Document doc) {
        Element element = doc.createElement("value");
        element.setTextContent(String.valueOf(cpAmount));
        return element;
    }

    public static Value load(Node node) {
        return new Value(Integer.valueOf(text(node)));
    }

    @Override
    public int hashCode() {
        return cpAmount;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass()
            && ((Value) obj).cpAmount == cpAmount;
    }

    public int compareTo(Value other) {
        return this.cpAmount - other.cpAmount;
    }

}
