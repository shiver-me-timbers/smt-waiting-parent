package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.SpringManualWaitingDefaults;
import shiver.me.timbers.waiting.execution.SpringManualWaitingForNotNull;
import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingForNotNull;

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
