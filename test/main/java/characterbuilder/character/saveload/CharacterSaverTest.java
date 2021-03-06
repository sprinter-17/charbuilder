package characterbuilder.character.saveload;

import characterbuilder.character.Character;
import static characterbuilder.character.ability.Proficiency.ACTOR;
import static characterbuilder.character.ability.RacialTalent.DARKVISION;
import characterbuilder.character.ability.Skill;
import characterbuilder.character.attribute.Alignment;
import static characterbuilder.character.attribute.AttributeType.*;
import characterbuilder.character.attribute.Background;
import characterbuilder.character.attribute.Height;
import characterbuilder.character.attribute.IntAttribute;
import characterbuilder.character.attribute.Race;
import characterbuilder.character.attribute.Sex;
import characterbuilder.character.attribute.StringAttribute;
import characterbuilder.character.attribute.Weight;
import characterbuilder.character.characterclass.CharacterClass;
import static characterbuilder.character.characterclass.CharacterClass.WIZARD;
import characterbuilder.character.characterclass.CharacterClassLevel;
import characterbuilder.character.characterclass.cleric.DivineDomain;
import static characterbuilder.character.equipment.AdventureGear.BASKET;
import characterbuilder.character.equipment.Token;
import static characterbuilder.character.equipment.Weapon.WHIP;
import characterbuilder.character.spell.Spell;
import characterbuilder.character.spell.SpellCasting;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

public class CharacterSaverTest {

	private CharacterSaver saver;
	private Character character;
	@SuppressWarnings("unused")
	private String xml;

	@BeforeEach
	public void setup() {
		try {
			saver = new CharacterSaver();
			character = new Character();
			character.addAttributes(Race.HUMAN, Background.ACOLYTE);
			character.addAttribute(new CharacterClassLevel(CharacterClass.CLERIC, 1));
		} catch (ParserConfigurationException ex) {
			Logger.getLogger(CharacterSaverTest.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Test
	public void testLoadedCharacterIsNotDirty() {
		saveAndLoad();
		assertFalse(character.isDirty());
	}

	@Test
	public void testPrimaryAttributes() {
		saveAndLoad();
		assertThat(character.getAttribute(RACE), is(Race.HUMAN));
		assertTrue(character.getCharacterClasses().anyMatch(CharacterClass.CLERIC::equals));
		assertThat(character.getAttribute(BACKGROUND), is(Background.ACOLYTE));
	}

	@Test
	public void testName() {
		character.addAttribute(new StringAttribute(NAME, "Fred"));
		saveAndLoad();
		assertThat(character.getAttribute(NAME).toString(), is("Fred"));
	}

	@Test
	public void testAttributes() {
		character.addAttribute(new IntAttribute(DEXTERITY, 2));
		saveAndLoad();
		assertThat(character.getIntAttribute(DEXTERITY), is(2));
	}

	@Test
	public void testHeightAndWeight() {
		character.addAttribute(Height.FOOT.times(20));
		character.addAttribute(Weight.ST.times(20));
		saveAndLoad();
		assertThat(character.getAttribute(HEIGHT), is(Height.FOOT.times(20)));
		assertThat(character.getAttribute(WEIGHT), is(Weight.ST.times(20)));
	}

	@Test
	public void testSex() {
		character.addAttribute(Sex.FEMALE);
		saveAndLoad();
		assertThat(character.getAttribute(SEX), is(Sex.FEMALE));
	}

	@Test
	public void testAlignment() {
		character.addAttribute(Alignment.LAWFUL_EVIL);
		saveAndLoad();
		assertThat(character.getAttribute(ALIGNMENT), is(Alignment.LAWFUL_EVIL));
	}

	@Test
	public void testAbilities() {
		character.addAttribute(DARKVISION);
		character.addAttribute(ACTOR);
		saveAndLoad();
		assertTrue(character.hasAttribute(DARKVISION));
		assertTrue(character.hasAttribute(ACTOR));
	}

	@Test
	public void testNonWeapon() {
		character.addEquipment(BASKET);
		saveAndLoad();
		assertTrue(character.hasEquipment(BASKET));
	}

	@Test
	public void testWeapon() {
		character.addEquipment(WHIP.makeSet(1));
		saveAndLoad();
		assertTrue(character.hasEquipment(WHIP));
	}

	@Test
	public void testEquipmentSet() {
		character.addEquipment(WHIP.makeSet(5));
		saveAndLoad();
		assertThat(character.getInventoryWeight(), is(WHIP.getWeight().times(5)));
	}

	@Test
	public void testToken() {
		character.addEquipment(new Token("Test Token"));
		saveAndLoad();
		assertTrue(character.hasEquipment(new Token("Test Token")));
	}

	@Test
	public void testLearntSpells() {
		SpellCasting casting = new SpellCasting("Spells", INTELLIGENCE, WIZARD, "All");
		casting.addPreparedSpell(Spell.ANTIMAGIC_FIELD);
		character.addAttribute(casting);
		saveAndLoad();
		casting = character.getSpellCasting("Spells");
		assertTrue(casting.hasLearntSpell(Spell.ANTIMAGIC_FIELD));
	}

	@Test
	public void testSkill() {
		character.addAttribute(Skill.ARCANA);
		saveAndLoad();
		assertTrue(character.hasAttribute(Skill.ARCANA));
	}

	@Test
	public void testDivineDomain() {
		character.addAttribute(DivineDomain.LIFE);
		saveAndLoad();
		assertTrue(character.hasAttribute(DivineDomain.LIFE));
	}

	private void saveAndLoad() {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			saver.save(character, out);
			xml = out.toString();
			ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
			character = saver.load(in);
		} catch (IOException | SAXException ex) {
			Assert.fail(ex.toString());
		}
	}

}
