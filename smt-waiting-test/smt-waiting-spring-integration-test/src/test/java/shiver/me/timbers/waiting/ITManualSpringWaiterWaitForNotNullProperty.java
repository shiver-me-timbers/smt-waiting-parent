package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualSpringWaiterWaitForNotNullProperty extends AbstractITSpringWaiterWaitForNotNullProperty {

    @Override
    public WaitingDefaults defaults() {
        return new SpringManualWaitingDefaults();
    }

    @Override
    protected WaitingForNotNull overrideWaitForNotNull(long duration, TimeUnit unit, boolean isNotNull) {
        return new SpringManualWaitingForNotNull(duration, unit, isNotNull);
    }
}
