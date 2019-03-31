package characterbuilder.character.saveload;

import java.io.ByteArrayOutputStream;
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

public class TestDoc {

    public static Document doc() {
        try {
            DocumentBuilderFactory FACTORY = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = FACTORY.newDocumentBuilder();
            Document doc = builder.newDocument();
            return doc;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(TestDoc.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException(ex);
        }
    }

    public static String toSaveString(Savable savable) {
        try {
            Document doc = doc();
            doc.appendChild(savable.save(doc));
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            transformer.transform(source, new StreamResult(out));
            return out.toString();
        } catch (TransformerException ex) {
            Logger.getLogger(TestDoc.class.getName()).log(Level.SEVERE, null, ex);
            throw new IllegalStateException(ex);
        }
    }
}
