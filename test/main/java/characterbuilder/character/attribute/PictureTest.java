package characterbuilder.character.attribute;

import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXParseException;

public class PictureTest {

	private Picture picture;

	@BeforeEach
	public void setup() {
		this.picture = new Picture() {
			@Override
			public Element save(Document document) {
				return getType().save(document);
			}
		};
		Picture.setLoader(el -> {
			return picture;
		});
	}

	@Test
	public void testGetType() {
		assertThat(picture.getType(), is(AttributeType.PICTURE));
	}

	@Test
	public void testLoadBeforeLoaderSet() throws SAXParseException {
		Picture.setLoader(null);
		assertThrows(IllegalStateException.class, () -> Picture.load(TestDoc.doc().createElement("picture")));
	}

	@Test
	public void testSaveAndLoad() throws SAXParseException {
		assertThat(AttributeType.load(picture.save(TestDoc.doc())), is(picture));
	}

}
