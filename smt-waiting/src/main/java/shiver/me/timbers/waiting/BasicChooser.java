package shiver.me.timbers.waiting;

import java.util.List;

/**
 * @author Karl Bennett
 */
class BasicChooser implements Chooser {

    private final Sleeper sleeper;

    public BasicChooser(Sleeper sleeper) {
        this.sleeper = sleeper;
    }

    @Override
    public Choice choose(Choices choices) {
        final List<ResultValidator> resultValidators = choices.getResultValidators();

        if (choices.isWaitForTrue()) {
            resultValidators.add(new TrueResult());
        }
        if (choices.isWaitForNotNull()) {
            resultValidators.add(new NotNullResult());
        }

        return new Choice(
            sleeper,
            choices.getTimeoutDuration(),
            choices.getTimeoutUnit(),
            choices.getIntervalDuration(),
            choices.getIntervalUnit(),
            resultValidators
        );
    }
}
