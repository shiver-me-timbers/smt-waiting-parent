package shiver.me.timbers.waiting;

import static shiver.me.timbers.waiting.Decision.UNDECIDED;
import static shiver.me.timbers.waiting.Decision.YES;

/**
 * @author Karl Bennett
 */
class WaitOptionsConfigurer implements OptionsConfigurer<Wait> {

    @Override
    public OptionsService apply(OptionsService options, Wait wait) {
        final TimeOut timeOut = wait.value();
        if (timeOut.duration() > -1) {
            options.withTimeOut(timeOut.duration(), timeOut.unit());
        }

        final Interval interval = wait.interval();
        if (interval.duration() > -1) {
            options.withInterval(interval.duration(), interval.unit());
        }

        final Class<? extends ResultValidator>[] waitFor = wait.waitFor();
        for (Class<? extends ResultValidator> validator : waitFor) {
            options.waitFor(newInstance(validator));
        }

        if (!UNDECIDED.equals(wait.waitForTrue())) {
            options.willWaitForTrue(YES.equals(wait.waitForTrue()));
        }
        if (!UNDECIDED.equals(wait.waitForNotNull())) {
            options.willWaitForNotNull(YES.equals(wait.waitForNotNull()));
        }

        return options;
    }

    private <T> T newInstance(Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
