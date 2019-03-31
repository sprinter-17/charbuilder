package characterbuilder.character.spell;

import characterbuilder.character.attribute.AttributeType;
import characterbuilder.character.attribute.DamageType;
import characterbuilder.character.characterclass.wizard.MagicSchool;
import characterbuilder.character.equipment.Attack;
import characterbuilder.utils.TestCharacter;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpellDelegateTest {

    private TestCharacter character;
    private SpellDelegate delegate;

    @BeforeEach
    public void setup() {
        character = new TestCharacter();
    }

    @Test
    public void testAttack() {
        delegate = SpellDelegate.spell(MagicSchool.EVOCATION, 4)
            .name("Fred")
            .range("15")
            .attack("3d6", DamageType.COLD);
        Attack attack = delegate.getAttack(character, AttributeType.STRENGTH).get();
        assertThat(attack.getRange(), is("15"));
        assertThat(attack.getName(), is("Fred"));
        assertThat(attack.getDamage(), is("3d6"));
    }

}
