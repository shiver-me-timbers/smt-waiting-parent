package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.SpringManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.SpringManualWaitingForTrue;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingForTrue;

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
