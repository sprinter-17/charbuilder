package characterbuilder.character.saveload;

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

    static Element child(Node node) {
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE)
                return (Element) child;
        }
        throw new IllegalArgumentException("Attempt to get element from childless node");
    }
}
