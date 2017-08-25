package characterbuilder.character.characterclass.ranger;

import characterbuilder.character.attribute.AttributeType;
import static characterbuilder.character.saveload.TestDoc.doc;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class FavouredEnemyTest {

    @Test
    public void testSaveAndLoad() {
        FavouredEnemy favouredEnemy = new FavouredEnemy();
        favouredEnemy.addEnemy(FavouredEnemy.Enemy.FEY);
        favouredEnemy.addEnemy(FavouredEnemy.Enemy.FIENDS);
        assertThat(AttributeType.load(favouredEnemy.save(doc())), is(favouredEnemy));
    }

}
