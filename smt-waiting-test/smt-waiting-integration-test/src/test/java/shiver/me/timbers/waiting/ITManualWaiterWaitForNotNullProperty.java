package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterWaitForNotNullProperty extends AbstractITWaiterWaitForNotNullProperty {

    @Override
    public WaitingDefaults defaults() {
        return new ManualWaitingDefaults();
    }

    @Override
    protected WaitingForNotNull overrideWaitForNotNull(long duration, TimeUnit unit, boolean isNotNull) {
        return new ManualWaitingForNotNull(duration, unit, isNotNull);
    }
}
