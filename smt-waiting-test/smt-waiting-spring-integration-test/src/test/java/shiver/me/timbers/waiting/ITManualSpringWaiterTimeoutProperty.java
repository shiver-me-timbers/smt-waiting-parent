package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.SpringManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.SpringManualWaitingTimeout;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingTimeout;

import java.util.concurrent.TimeUnit;

public class ITManualSpringWaiterTimeoutProperty extends AbstractITSpringWaiterTimeoutProperty {

    @Override
    public WaitingDefaults defaults() {
        return new SpringManualWaitingDefaults();
    }

    @Override
    protected WaitingTimeout overrideTimeout(long duration, TimeUnit unit) {
        return new SpringManualWaitingTimeout(duration, unit);
    }
}
