package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualWaiter extends AbstractITWaiter {
    @Override
    public WaitingInterval interval(final long duration, final TimeUnit unit) {
        return new ManualWaitingInterval(duration, unit);
    }

    @Override
    public WaiterTimeout timeout(final long duration, final TimeUnit unit) {
        return new ManualWaiterTimeout(duration, unit);
    }

}
