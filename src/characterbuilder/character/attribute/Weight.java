package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Weight implements Comparable<Weight>, Attribute {

    private static final Pattern FORMAT
        = Pattern.compile("^((?<st>\\d+)st ?)?((?<lb>\\d+)lb ?)?((?<oz>\\d+)oz)?$");

    public static final Weight ZERO = new Weight(0);
    public static final Weight OZ = new Weight(1);
    public static final Weight LB = Weight.OZ.times(16);
    public static final Weight ST = Weight.LB.times(14);

    private static final Comparator<Weight> ORDER = Comparator.comparingInt(w -> w.ounces);
    private final int ounces;

    public static class WeightFormatException extends Exception {

        public WeightFormatException(String text) {
            super("\"" + text + "\" is not in correct Weight format");
        }
    }

    public static Weight oz(int count) {
        return OZ.times(count);
    }

    public static Weight lb(int count) {
        return LB.times(count);
    }

    public static Weight st(int count) {
        return ST.times(count);
    }

    private Weight(int ounces) {
        this.ounces = ounces;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.WEIGHT;
    }

    public Weight add(Weight other) {
        return new Weight(this.ounces + other.ounces);
    }

    public Weight times(float multiple) {
        return new Weight(Math.round(ounces * multiple));
    }

    public String toString() {
        return new StringUtils.UnitBuilder(ounces)
            .withUnit(ST.ounces, "st")
            .withUnit(LB.ounces, "lb")
            .withUnit(OZ.ounces, "oz")
            .toString();
    }

    public static Weight valueOf(String text) throws WeightFormatException {
        Matcher matcher = FORMAT.matcher(text);
        if (matcher.matches()) {
            Weight weight = ZERO;
            if (matcher.group("st") != null)
                weight = weight.add(ST.times(Integer.valueOf(matcher.group("st"))));
            if (matcher.group("lb") != null)
                weight = weight.add(LB.times(Integer.valueOf(matcher.group("lb"))));
            if (matcher.group("oz") != null)
                weight = weight.add(OZ.times(Integer.valueOf(matcher.group("oz"))));
            return weight;
        } else
            throw new WeightFormatException(text);
    }

    public String encode() {
        return String.valueOf(ounces);
    }

    public static Weight decode(String code) {
        return new Weight(Integer.valueOf(code));
    }

    @Override
    public int hashCode() {
        return this.ounces;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass()
            && ((Weight) obj).ounces == this.ounces;
    }

    @Override
    public int compareTo(Weight other) {
        return ORDER.compare(this, other);
    }
}
