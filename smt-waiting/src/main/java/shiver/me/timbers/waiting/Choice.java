package shiver.me.timbers.waiting;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Karl Bennett
 */
class Choice {

    private final Sleeper sleeper;

    private final Long timeoutDuration;
    private final TimeUnit timeoutUnit;
    private final List<ResultValidator> resultValidators;
    private final Long intervalDuration;
    private final TimeUnit intervalUnit;

    Choice(
        Sleeper sleeper,
        Long timeoutDuration,
        TimeUnit timeoutUnit,
        List<ResultValidator> resultValidators,
        Long intervalDuration,
        TimeUnit intervalUnit
    ) {
        this.sleeper = sleeper;
        this.timeoutDuration = timeoutDuration;
        this.timeoutUnit = timeoutUnit;
        this.resultValidators = resultValidators;
        this.intervalDuration = intervalDuration;
        this.intervalUnit = intervalUnit;
    }

    Timer startTimer() {
        return new Timer(timeoutDuration, timeoutUnit, new Start(new Date()));
    }

    @SuppressWarnings("unchecked")
    boolean isValid(Object result) throws Throwable {
        for (ResultValidator resultValidator : resultValidators) {
            if (!resultValidator.isValid(result)) {
                return false;
            }
        }
        return true;
    }

    void interval() {
        try {
            sleeper.sleep(intervalUnit.toMillis(intervalDuration));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
