package characterbuilder.character.attribute;

import characterbuilder.utils.StringUtils;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Height implements Comparable<Height>, Attribute {

    private static final Pattern FORMAT
        = Pattern.compile("^((?<ft>\\d+)' ?)?((?<in>\\d+)\")?$");

    private static final Comparator<Height> ORDER = Comparator.comparingInt(h -> h.inches);

    public static final Height ZERO = new Height(0);
    public static final Height INCH = new Height(1);
    public static final Height FOOT = INCH.times(12);

    private final int inches;

    public static class HeightFormatException extends Exception {

		private static final long serialVersionUID = -4322271088334486424L;

		public HeightFormatException(String txt) {
            super("\"" + txt + "\" is not a valid height value");
        }
    }

    private Height(int inches) {
        this.inches = inches;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.HEIGHT;
    }

    public Height add(Height other) {
        return new Height(this.inches + other.inches);
    }

    public Height times(int multiple) {
        return new Height(this.inches * multiple);
    }

    public String toString() {
        return new StringUtils.UnitBuilder(inches)
            .withUnit(12, "'").withUnit(1, "\"").toString();
    }

    public static Height valueOf(String text) throws HeightFormatException {
        Matcher matcher = FORMAT.matcher(text);
        if (matcher.matches()) {
            Height height = ZERO;
            if (matcher.group("ft") != null)
                height = height.add(FOOT.times(Integer.valueOf(matcher.group("ft"))));
            if (matcher.group("in") != null)
                height = height.add(INCH.times(Integer.valueOf(matcher.group("in"))));
            return height;
        } else
            throw new HeightFormatException(text);

    }

    @Override
    public int hashCode() {
        return inches;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null
            && getClass() == obj.getClass()
            && ((Height) obj).inches == this.inches;
    }

    @Override
    public Element save(Document doc) {
        Element element = getType().save(doc);
        element.setTextContent(String.valueOf(inches));
        return element;
    }

    public static Height load(Node node) {
        return new Height(Integer.valueOf(node.getTextContent()));
    }

    public String encode() {
        return String.valueOf(inches);
    }

    public static Height decode(String code) {
        return new Height(Integer.valueOf(code));
    }

    @Override
    public int compareTo(Height other) {
        return ORDER.compare(this, other);
    }
}
