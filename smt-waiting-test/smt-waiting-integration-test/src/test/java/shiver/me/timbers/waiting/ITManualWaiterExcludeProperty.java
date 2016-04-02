package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterExcludeProperty extends AbstractITWaiterExcludeProperty {

    @Override
    public WaitingDefaults defaults() {
        return new ManualWaitingDefaults();
    }

    @Override
    protected WaitingExclude addExclude(long duration, TimeUnit unit, Throwable exclude) {
        return new ManualWaitingExclude(duration, unit, exclude);
    }
}
