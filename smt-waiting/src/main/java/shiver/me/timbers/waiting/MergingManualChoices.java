package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Karl Bennett
 */
class MergingManualChoices implements ManualChoices {

    @Override
    public Choices apply(Choices currentChoices, Choices manualChoices) {
        return new BasicChoices(
            findTimeoutDuration(currentChoices, manualChoices),
            findTimeoutUnit(currentChoices, manualChoices),
            findIntervalDuration(currentChoices, manualChoices),
            findIntervalUnit(currentChoices, manualChoices),
            findWaitForTrue(currentChoices, manualChoices),
            findWaitForNotNull(currentChoices, manualChoices),
            findResultValidators(currentChoices, manualChoices)
        );
    }

    private Long findTimeoutDuration(Choices currentChoices, Choices manualChoices) {
        final Long timeoutDuration = manualChoices.getTimeoutDuration();
        return timeoutDuration == null ? currentChoices.getTimeoutDuration() : timeoutDuration;
    }

    private TimeUnit findTimeoutUnit(Choices currentChoices, Choices manualChoices) {
        final TimeUnit timeoutUnit = manualChoices.getTimeoutUnit();
        return timeoutUnit == null ? currentChoices.getTimeoutUnit() : timeoutUnit;
    }

    private Long findIntervalDuration(Choices currentChoices, Choices manualChoices) {
        final Long intervalDuration = manualChoices.getIntervalDuration();
        return intervalDuration == null ? currentChoices.getIntervalDuration() : intervalDuration;
    }

    private TimeUnit findIntervalUnit(Choices currentChoices, Choices manualChoices) {
        final TimeUnit intervalUnit = manualChoices.getIntervalUnit();
        return intervalUnit == null ? currentChoices.getIntervalUnit() : intervalUnit;
    }

    private Boolean findWaitForTrue(Choices currentChoices, Choices manualChoices) {
        final Boolean waitForTrue = manualChoices.isWaitForTrue();
        return waitForTrue == null ? currentChoices.isWaitForTrue() : waitForTrue;
    }

    private Boolean findWaitForNotNull(Choices currentChoices, Choices manualChoices) {
        final Boolean waitForNotNull = manualChoices.isWaitForNotNull();
        return waitForNotNull == null ? currentChoices.isWaitForNotNull() : waitForNotNull;
    }

    private List<ResultValidator> findResultValidators(Choices currentChoices, Choices manualChoices) {
        final List<ResultValidator> resultValidators = currentChoices.getResultValidators();
        resultValidators.addAll(manualChoices.getResultValidators());
        return resultValidators;
    }
}
