package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualWaiter extends AbstractITWaiter {

    @Override
    public WaitingInterval interval(final long duration, final TimeUnit unit) {
        return new ManualWaitingInterval(duration, unit);
    }

    @Override
    public WaitingTimeout timeout(final long duration, final TimeUnit unit) {
        return new ManualWaitingTimeout(duration, unit);
    }

    @Override
    public WaitingFor waitFor(final long duration, final TimeUnit unit, final ResultValidator validator) {
        return new ManualWaitingFor(duration, unit, validator);
    }

    @Override
    public WaitingForNotNull waitForNotNull(final long duration, final TimeUnit unit, final boolean isNotNull) {
        return new ManualWaitingForNotNull(duration, unit, isNotNull);
    }
}
