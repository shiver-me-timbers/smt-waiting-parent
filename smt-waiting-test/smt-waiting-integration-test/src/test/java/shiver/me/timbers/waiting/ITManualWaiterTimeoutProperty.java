package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterTimeoutProperty extends AbstractITWaiterTimeoutProperty {

    @Override
    public WaitingDefaults defaults() {
        return new ManualWaitingDefaults();
    }

    @Override
    protected WaiterTimeout overrideTimeout(long duration, TimeUnit unit) {
        return new ManualWaiterTimeout(duration, unit);
    }
}
