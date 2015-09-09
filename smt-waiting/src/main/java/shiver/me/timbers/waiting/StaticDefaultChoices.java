package shiver.me.timbers.waiting;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Karl Bennett
 */
class StaticDefaultChoices implements DefaultChoices {

    private static final long DEFAULT_TIMEOUT_DURATION = 10L;
    private static final TimeUnit DEFAULT_TIMEOUT_UNIT = SECONDS;
    private static final long DEFAULT_INTERVAL_DURATION = 100L;
    private static final TimeUnit DEFAULT_INTERVAL_UNIT = MILLISECONDS;

    @Override
    public Choices create() {
        return new BasicChoices(
            DEFAULT_TIMEOUT_DURATION,
            DEFAULT_TIMEOUT_UNIT,
            DEFAULT_INTERVAL_DURATION,
            DEFAULT_INTERVAL_UNIT,
            false,
            false,
            new ArrayList<ResultValidator>()
        );
    }
}
