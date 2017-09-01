package characterbuilder.character.saveload;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.equipment.Equipment;
import characterbuilder.character.equipment.EquipmentCategory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXParseException;

public class CharacterSaver {

    private static final DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();
    private final DocumentBuilder builder;
    private Document doc;

    public CharacterSaver() throws ParserConfigurationException {
        this.builder = FACTORY.newDocumentBuilder();
    }

    public void save(Character character, OutputStream output) {
        doc = builder.newDocument();
        Node root = doc.appendChild(doc.createElement("character"));
        saveAttributes(character, root.appendChild(doc.createElement("attributes")));
        saveEquipment(character, root.appendChild(doc.createElement("inventory")));
        writeDocument(doc, output);
    }

    private void saveAttributes(Character character, Node node) {
        character.getAllAttributes()
            .sorted(Comparator.comparing(Attribute::getType).thenComparing(Attribute::toString))
            .forEach(attr -> node.appendChild(attr.save(doc)));
    }

    private void saveEquipment(Character character, Node node) {
        character.getInventory()
            .sorted(Comparator.comparing(Equipment::getCategory).thenComparing(Equipment::toString))
            .map(eq -> eq.save(doc))
            .forEach(node::appendChild);
    }

    private void writeDocument(Document doc, OutputStream output) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, new StreamResult(output));
        } catch (TransformerException ex) {
            Logger.getLogger(CharacterSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Character load(InputStream input) throws SAXException, IOException {
        doc = builder.parse(input);
        Element root = (Element) doc.getFirstChild();
        Node node = root.getFirstChild();
        Character character = new Character();

        try {
            while (node != null) {
                switch (node.getNodeName()) {
                    case "attributes":
                        loadAttributes(character, node);
                        break;
                    case "inventory":
                        loadEquipment(character, node);
                        break;
                    case "#text":
                        break;
                    default:
                        throw new SAXNotRecognizedException("Unrecognised root element "
                            + node.getNodeName());
                }
                node = node.getNextSibling();
            }
        } catch (IllegalArgumentException ex) {
            throw new SAXException(ex);
        }
        character.clearDirty();
        return character;
    }

    private void loadAttributes(Character character, Node attributesNode) throws SAXParseException {
        for (Node child = attributesNode.getFirstChild(); child != null;
            child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                character.addAttribute(AttributeType.load((Element) child));
            }
        }
    }

    private void loadEquipment(Character character, Node equipmentNode) {
        for (Node child = equipmentNode.getFirstChild(); child != null;
            child = child.getNextSibling()) {
            if (child.getNodeType() == Node.ELEMENT_NODE)
                character.addEquipment(EquipmentCategory.load(child));
        }
    }
}
