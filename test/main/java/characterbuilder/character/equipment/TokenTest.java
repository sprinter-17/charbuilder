package characterbuilder.character.equipment;

import characterbuilder.character.saveload.TestDoc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TokenTest {

    private Token token;

    @BeforeEach
    public void setup() {
        token = new Token("Foobar");
    }

    @Test
    public void testEquals() {
        assertThat(token, is(new Token("Foobar")));
    }

    @Test
    public void testSaveAndLoad() {
        assertThat(EquipmentCategory.load(token.save(TestDoc.doc())), is(token));
    }
}
