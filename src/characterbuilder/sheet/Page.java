package characterbuilder.sheet;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.attribute.AttributeType.NAME;
import java.util.List;
import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

public abstract class Page {

    protected final Character character;
    protected final PageBuilder builder = new PageBuilder();

    public Page(Character character) {
        this.character = character;
    }

    public abstract Stream<PageBuilder.Container> getPages();

    protected PageBuilder.Component name() {
        return builder.borderedSection(0, 0, 28, 8)
            .with(builder.caption("Name", 14, 6, PageBuilder.Align.BOTTOM_CENTRE))
            .with(builder.value(attr(NAME), 14, 3, PageBuilder.Align.CENTRE));
    }

    protected static String html(String value) {
        return "<html>" + value + "</html>";
    }

    protected String table(List<String> rows, int width) {
        StringBuilder text = new StringBuilder();
        text.append("<html>").append(String.format("<table width='%dpx'>", width));
        rows.forEach(text::append);
        text.append("</table>").append("</html>");
        return text.toString();
    }

    protected String table(List<String> rows) {
        StringBuilder text = new StringBuilder();
        text.append("<html>").append("<table>");
        rows.forEach(text::append);
        text.append("</table>").append("</html>");
        return text.toString();
    }

    protected String row(String tag, String... cells) {
        StringBuilder text = new StringBuilder();
        text.append("<tr>");
        for (String cell : cells) {
            text.append("<").append(tag).append(">")
                .append(cell)
                .append("</").append(tag).append(">");
        }
        text.append("</tr>");
        return text.toString();
    }

    protected String elements(String tag, Stream<String> values) {
        return values.map(v -> element(tag, v)).collect(joining());
    }

    protected static String element(String tag, String value) {
        return "<" + tag + ">" + value + "</" + tag + ">";
    }

    protected String attr(AttributeType attr) {
        return character.getStringAttribute(attr);
    }

    protected String attrHTML(AttributeType type) {
        return character.getAllAttributes()
            .filter(type::isTypeOfAttribute)
            .map(Attribute::toString)
            .collect(joining("<br>", "<html><p>", "</p></html>"));
    }

    protected static String nbsp(Object object) {
        return object.toString().replace(" ", "&nbsp;");
    }
}
