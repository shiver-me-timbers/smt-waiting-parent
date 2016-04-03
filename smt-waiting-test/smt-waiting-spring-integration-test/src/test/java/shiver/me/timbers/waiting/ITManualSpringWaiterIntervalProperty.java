package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualSpringWaiterIntervalProperty extends AbstractITSpringWaiterIntervalProperty {

    @Override
    public WaitingDefaults defaults() {
        return new SpringManualWaitingDefaults();
    }

    @Override
    protected WaitingInterval overrideInterval(final long duration, final TimeUnit unit) {
        return new SpringManualWaitingInterval(duration, unit);
    }
}
