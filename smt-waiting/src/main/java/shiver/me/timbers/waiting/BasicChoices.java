package shiver.me.timbers.waiting;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Karl Bennett
 */
class BasicChoices implements Choices {

    private final Long timeoutDuration;
    private final TimeUnit timeoutUnit;
    private final Long intervalDuration;
    private final TimeUnit intervalUnit;
    private final Boolean waitForTrue;
    private final Boolean waitForNotNull;
    private final List<ResultValidator> resultValidators;

    BasicChoices(
        Long timeoutDuration,
        TimeUnit timeoutUnit,
        Long intervalDuration,
        TimeUnit intervalUnit,
        Boolean waitForTrue,
        Boolean waitForNotNull,
        List<ResultValidator> resultValidators
    ) {
        this.timeoutDuration = timeoutDuration;
        this.timeoutUnit = timeoutUnit;
        this.intervalDuration = intervalDuration;
        this.intervalUnit = intervalUnit;
        this.waitForTrue = waitForTrue;
        this.waitForNotNull = waitForNotNull;
        this.resultValidators = resultValidators;
    }

    @Override
    public Long getTimeoutDuration() {
        return timeoutDuration;
    }

    @Override
    public TimeUnit getTimeoutUnit() {
        return timeoutUnit;
    }

    @Override
    public Long getIntervalDuration() {
        return intervalDuration;
    }

    @Override
    public TimeUnit getIntervalUnit() {
        return intervalUnit;
    }

    @Override
    public Boolean isWaitForTrue() {
        return waitForTrue;
    }

    @Override
    public Boolean isWaitForNotNull() {
        return waitForNotNull;
    }

    @Override
    public List<ResultValidator> getResultValidators() {
        return new ArrayList<>(resultValidators);
    }
}
