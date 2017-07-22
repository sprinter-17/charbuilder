package characterbuilder.utils;

import characterbuilder.character.Character;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.joining;

public class StringUtils {

    public static class UnitBuilder {

        private final int amount;
        private final String text;

        public UnitBuilder(int amount) {
            this.amount = amount;
            this.text = amount == 0 ? "-" : "";
        }

        private UnitBuilder(int amount, String text) {
            this.amount = amount;
            this.text = amount == 0 ? text : text + " ";
        }

        public UnitBuilder withUnit(int divisor, String name) {
            int units = amount / divisor;
            if (units == 0)
                return this;
            else
                return new UnitBuilder(amount % divisor, text + units + name);
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static String capitaliseEnumName(String text) {
        return Arrays.stream(text.split("_"))
            .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase())
            .collect(joining(" "));
    }

    public static String expand(String text, Character character) {
        final Pattern expressionPattern = Pattern.compile("\\[([^]]*)\\]");
        Matcher expressionMatcher = expressionPattern.matcher(text);
        StringBuffer result = new StringBuffer();
        String value = null;
        while (expressionMatcher.find()) {
            value = ExpressionParser.eval(expressionMatcher.group(1), character, value);
            expressionMatcher.appendReplacement(result, value);
        }
        expressionMatcher.appendTail(result);
        return result.toString();
    }
}
