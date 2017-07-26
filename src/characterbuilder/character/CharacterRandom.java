package characterbuilder.character;

import characterbuilder.character.attribute.Value;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharacterRandom {

    private static final Pattern DICE_PATTERN = Pattern.compile("(?<count>\\d+)(d(?<die>4|6|8|10|12|20))?");
    private final Random random = new Random();

    public int nextAbilityScore() {
        return random.ints(4, 1, 7).sorted().skip(1).sum();
    }

    public int nextHitPoints(int max) {
        return random.ints(2, 1, max + 1).max().getAsInt();
    }

    public Value nextStartingWealth(int factor) {
        return Value.GP.times(random.ints(factor, 1, 5).sum() * 10);
    }

    public int roll(String dice) {
        Matcher matcher = DICE_PATTERN.matcher(dice);
        if (matcher.matches()) {
            int count = Integer.valueOf(matcher.group("count"));
            if (matcher.group("die") != null) {
                int die = Integer.valueOf(matcher.group(3));
                return random.ints(count, 1, die + 1).sum();
            } else {
                return count;
            }
        } else {
            throw new IllegalArgumentException("Dice " + dice + " is not a valid pattern");
        }
    }
}
