package characterbuilder.utils;

import characterbuilder.character.Character;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

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
            .map(StringUtils::capitaliseEnumWord)
            .collect(joining(" "));
    }

    private static String capitaliseEnumWord(String word) {
        if (Stream.of("OF", "THE", "AND", "TO", "WITH").anyMatch(word::equals))
            return word.toLowerCase();
        else
            return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    public static String expand(String text, Character character) {
        final Pattern expressionPattern = Pattern.compile("\\[([^]]*)\\]");
        Matcher expressionMatcher = expressionPattern.matcher(text);
        StringBuffer result = new StringBuffer();
        EvaluationContext context = new EvaluationContext();
        if (character != null)
            context.setCharacter(character);
        while (expressionMatcher.find()) {
            String value = ExpressionParser.eval(expressionMatcher.group(1), context);
            expressionMatcher.appendReplacement(result, value.replace('$', '#'));
            context.setPlural(!value.equals("1") && !value.equals("one"));
        }
        expressionMatcher.appendTail(result);
        return result.toString();
    }
}
