package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualWaiterWaitForTrueProperty extends AbstractITWaiterWaitForTrueProperty {

    @Override
    public WaitingDefaults defaults() {
        return new ManualWaitingDefaults();
    }

    @Override
    protected WaitingForTrue overrideWaitForTrue(long duration, TimeUnit unit, boolean isTrue) {
        return new ManualWaitingForTrue(duration, unit, isTrue);
    }
}
