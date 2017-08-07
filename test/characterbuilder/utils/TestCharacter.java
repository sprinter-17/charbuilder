package characterbuilder.utils;

import characterbuilder.character.Character;
import characterbuilder.character.attribute.AbilityScore;

public class TestCharacter extends Character {

    public TestCharacter withScores(int value) {
        AbilityScore.SCORES.forEach(as -> addAttribute(new AbilityScore(as, value)));
        return this;
    }
}
