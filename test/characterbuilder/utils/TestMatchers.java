package characterbuilder.utils;

import characterbuilder.character.Character;
import characterbuilder.character.choice.Choice;
import static java.util.stream.Collectors.joining;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class TestMatchers {

    public static Matcher<Character> hasChoice(String choice) {
        return new TypeSafeDiagnosingMatcher<Character>() {
            @Override
            protected boolean matchesSafely(Character ch, Description desc) {
                if (ch.getChoiceCount() == 0) {
                    desc.appendText("has no choices");
                } else {
                    desc.appendText("has choices ")
                        .appendText(ch.getAllChoices().map(Choice::toString)
                            .collect(joining(",", "[", "]")));
                }
                return ch.hasChoice(choice);
            }

            @Override
            public void describeTo(Description desc) {
                desc.appendText("has choice ").appendValue(choice);
            }

        };
    }
}
