package characterbuilder.character.attribute;

import java.util.Optional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;

public abstract class Picture implements Attribute {

    public interface PictureLoader {

        Picture load(Element element) throws SAXParseException;
    }

    private static Optional<PictureLoader> loader = Optional.empty();

    public static void setLoader(PictureLoader loader) {
        Picture.loader = Optional.ofNullable(loader);
    }

    @Override
    public AttributeType getType() {
        return AttributeType.PICTURE;
    }

    @Override
    public abstract Element save(Document document);

    public static Picture load(Element element) throws SAXParseException {
        return loader.orElseThrow(IllegalStateException::new).load(element);
    }
}
