package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class ITManualSpringWaiterWaitForTrueProperty extends AbstractITSpringWaiterWaitForTrueProperty {

    @Override
    public WaitingDefaults defaults() {
        return new SpringManualWaitingDefaults();
    }

    @Override
    protected WaitingForTrue overrideWaitForTrue(long duration, TimeUnit unit, boolean isTrue) {
        return new SpringManualWaitingForTrue(duration, unit, isTrue);
    }
}
