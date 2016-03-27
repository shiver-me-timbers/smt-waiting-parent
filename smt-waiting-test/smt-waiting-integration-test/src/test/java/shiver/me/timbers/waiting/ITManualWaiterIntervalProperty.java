package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterIntervalProperty extends AbstractITWaiterIntervalProperty {

    @Override
    public WaitingDefaults defaults() {
        return new ManualWaitingDefaults();
    }

    @Override
    protected WaitingInterval overrideInterval(final long duration, final TimeUnit unit) {
        return new ManualWaitingInterval(duration, unit);
    }
}
