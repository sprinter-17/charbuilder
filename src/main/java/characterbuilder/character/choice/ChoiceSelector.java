package characterbuilder.character.choice;

import characterbuilder.character.attribute.AbilityScore;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface ChoiceSelector {

    <T extends Option> void chooseOption(Stream<T> options, Consumer<T> followUp);

    void generateAbilityScores(Consumer<Stream<AbilityScore>> consumer);

    default void choiceMade() {

    }

    default ChoiceSelector withActions(Runnable preAction, Runnable postAction) {
        return new ChoiceSelector() {
            @Override
            public <T extends Option> void chooseOption(Stream<T> options, Consumer<T> followUp) {
                ChoiceSelector.this.chooseOption(options, opt -> {
                    preAction.run();
                    followUp.accept(opt);
                    choiceMade();
                });
            }

            @Override
            public void generateAbilityScores(Consumer<Stream<AbilityScore>> consumer) {
                ChoiceSelector.this.generateAbilityScores(sa -> {
                    preAction.run();
                    consumer.accept(sa);
                    choiceMade();
                });
            }

            @Override
            public void choiceMade() {
                ChoiceSelector.this.choiceMade();
                postAction.run();
            }
        };
    }
}
