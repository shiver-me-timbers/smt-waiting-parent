package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualSpringWaiterIncludeProperty extends AbstractITSpringWaiterIncludeProperty {

    @Override
    public WaitingDefaults defaults() {
        return new SpringManualWaitingDefaults();
    }

    @Override
    protected WaitingInclude addInclude(long duration, TimeUnit unit, Throwable include) {
        return new SpringManualWaitingInclude(duration, unit, include);
    }
}
