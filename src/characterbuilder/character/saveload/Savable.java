package characterbuilder.character.saveload;

import java.util.stream.Stream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public interface Savable {

    Node save(Document doc);

    static int intAttribute(String attribute, Node node) {
        return Integer.valueOf(((Element) node).getAttribute(attribute));
    }

    static String text(Node node) {
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.TEXT_NODE)
                return child.getNodeValue();
        }
        throw new IllegalArgumentException("Attempt to get text from textless node");
    }

    static Stream<Element> children(Node node) {
        Stream.Builder<Element> builder = Stream.builder();
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE)
                builder.accept((Element) child);
        }
        return builder.build();
    }

    static Element child(Node node) {
        return children(node).findFirst()
            .orElseThrow(()
                -> new IllegalArgumentException("Attempt to get child of childless node"));
    }
}
