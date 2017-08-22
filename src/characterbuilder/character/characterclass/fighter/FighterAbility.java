package characterbuilder.character.characterclass.fighter;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.Attribute;
import characterbuilder.character.attribute.AttributeDelegate;
import static characterbuilder.character.attribute.AttributeDelegate.delegate;
import characterbuilder.character.attribute.AttributeType;
import characterbuilder.utils.StringUtils;
import java.util.stream.Stream;
import org.w3c.dom.Element;

public enum FighterAbility implements Attribute {
    EXTRA_ATTACK(delegate()
        .withDescription("Attack [max($level 5:two, 11:three, 20:four)] "
            + "times in each attack action.")),
    INDOMITABLE(delegate()
        .withDescription("Reroll [max($level 9:one, 13:two, 17:three)] "
            + "failed saving [plural(throw,throws)] between each rest.")),
    SECOND_WIND(delegate()
        .withDescription("Use a bonus action to regain 1d10+[$level] hit points.")
        .withDescription("Use once between each rest.")),
    ACTION_SURGE(delegate()
        .withDescription("Take an additional action [max($level 2:one time, 17:two times)] "
            + "between each rest.")),
    IMPROVED_CRITICAL(delegate()
        .withDescription("Score a critical on a roll of 19 or 20.")),
    SUPERIOR_CRITICAL(delegate()
        .withName("Improved Critical")
        .withDescription("Score a critical on a roll of 18, 19 or 20.")),
    SURVIVOR(delegate()
        .withDescription("Regain [5 + $con_mod] HP each turn "
            + "if no more than [$hp / 2] hit points remaining.")),
    COMBAT_SUPERIORITY(delegate()
        .withDescription("Can use one maneuver per attack.")
        .withDescription("[max($level 3:4,7:5,15:6)] [max($level 3:1d8,10:1d10,18:1d12)] "
            + "superiority dice.")
        .withDescription("Regain expended superiority dice after rest.")
        .withDescription("Maneuver save DC [8+$prof+max($str_mod,$dex_mod)].")),
    KNOW_YOUR_ENEMY(delegate()
        .withDescription("After 1 minute of observation, "
            + "know relative strength of two characteristics: "
            + "Strength, Dexterity, Consitution, AC, Current HP, Total Levels, Fighter Level.")),
    RELENTLESS(delegate()
        .withDescription("On initiative, regain 1 superiority die if none remaining.")),
    WAR_MAGIC(delegate()
        .withDescription("On casting a [max($level 7:cantrip,18:spell)], "
            + "make one weapon attack as a bonus action.")),
    ELDRITCH_STRIKE(delegate()
        .withDescription("On hitting with a weapon, "
            + "for one turn target has disadvantage on saves against spells.")),
    ARCANE_CHARGE(delegate()
        .withDescription("On Action Surge, teleport up to 30 feet.")),
    REMARKABLE_ATHLETE(delegate()
        .withDescription("Add [$prof / 2] to Str., Dex. and Con. checks. ")
        .withDescription("Add [$str_mod] feet to running long jump length.")),;

    private final AttributeDelegate delegate;

    private FighterAbility(AttributeDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.FIGHTER_ABILITY;
    }

    @Override
    public void generateInitialChoices(Character character) {
        delegate.generateChoices(character);
    }

    @Override
    public Stream<String> getDescription(Character character) {
        return delegate.getDescription(character);
    }

    @Override
    public String toString() {
        return delegate.getName().orElse(StringUtils.capitaliseEnumName(name()));
    }

    public static FighterAbility load(Element element) {
        return valueOf(element.getTextContent());
    }

}
