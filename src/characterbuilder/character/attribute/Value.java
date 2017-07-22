package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;

public class Value {

    public static final Value ZERO = new Value(0);
    public static final Value CP = new Value(1);
    public static final Value SP = CP.times(10);
    public static final Value GP = SP.times(10);
    private final int cpAmount;

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
