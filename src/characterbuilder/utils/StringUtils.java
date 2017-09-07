package characterbuilder.utils;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AttributeType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.joining;
import java.util.stream.IntStream;
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

    public static Path path(Character character, String extension) throws IOException {
        if (Files.notExists(Paths.get("characters")))
            Files.createDirectory(Paths.get("characters"));
        String fileName = character.getAttribute(AttributeType.NAME).toString()
            .toLowerCase().replaceAll("\\s+", "_").replaceAll("\\W", "") + "." + extension;
        return Paths.get("characters", fileName);
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

    public static class Row {

        private final Function<Integer, String> tagGenerator;
        private final List<String> values = new ArrayList<>();

        private Row(Function<Integer, String> tagGenerator) {
            this.tagGenerator = tagGenerator;
        }

        @Override
        public String toString() {
            return element("tr", IntStream.range(0, values.size())
                .mapToObj(n -> element(tagGenerator.apply(n), values.get(n)))
                .collect(joining()));
        }
    }

    public static Row header(String... values) {
        Row row = new Row(n -> "th");
        Arrays.stream(values).forEach(row.values::add);
        return row;
    }

    public static Row row(String... values) {
        Row row = new Row(n -> "td");
        Arrays.stream(values).forEach(row.values::add);
        return row;
    }

    public static Row namedRow(String... values) {
        Row row = new Row(n -> n == 0 ? "th" : "td");
        Arrays.stream(values).forEach(row.values::add);
        return row;
    }

    public static String table(Row... rows) {
        return element("table", Arrays.stream(rows).map(Row::toString).collect(joining()));
    }

    public static String ol(String... items) {
        return element("ol", Arrays.stream(items).map(it -> element("li", it)).collect(joining()));
    }

    public static String element(String tag, String value) {
        return "<" + tag + ">" + value + "</" + tag + ">";
    }
}
