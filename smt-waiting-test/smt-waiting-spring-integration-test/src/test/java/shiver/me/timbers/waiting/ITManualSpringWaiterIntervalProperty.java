package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.SpringManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.SpringManualWaitingInterval;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingInterval;

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
