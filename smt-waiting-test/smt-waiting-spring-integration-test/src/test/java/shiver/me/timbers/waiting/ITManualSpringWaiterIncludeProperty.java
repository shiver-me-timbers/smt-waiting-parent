package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.SpringManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.SpringManualWaitingInclude;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingInclude;

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
