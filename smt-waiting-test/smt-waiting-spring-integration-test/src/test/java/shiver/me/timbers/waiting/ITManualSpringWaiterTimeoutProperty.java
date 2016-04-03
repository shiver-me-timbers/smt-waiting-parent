package shiver.me.timbers.waiting;

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
