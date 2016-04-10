package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingForNotNull;

import java.util.concurrent.TimeUnit;

public abstract class AbstractITAspectWaiterWaitForNotNullProperty extends AbstractITWaiterWaitForNotNullProperty
    implements WaitingForNotNullFactoryAware, WaitingDefaultsFactoryAware {

    @Override
    public WaitingDefaults defaults() {
        return defaultsFactory().create();
    }

    @Override
    protected WaitingForNotNull overrideWaitForNotNull(long duration, TimeUnit unit, boolean isNotNull) {
        return waitForNotNullFactory().create(duration, unit, isNotNull);
    }
}
