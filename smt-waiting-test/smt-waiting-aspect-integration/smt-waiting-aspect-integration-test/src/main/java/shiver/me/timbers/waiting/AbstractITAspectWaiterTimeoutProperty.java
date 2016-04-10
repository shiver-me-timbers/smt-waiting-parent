package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingTimeout;

import java.util.concurrent.TimeUnit;

public abstract class AbstractITAspectWaiterTimeoutProperty extends AbstractITWaiterTimeoutProperty
    implements WaitingTimeoutFactoryAware, WaitingDefaultsFactoryAware {

    @Override
    protected WaitingTimeout overrideTimeout(long duration, TimeUnit unit) {
        return timeoutFactory().create(duration, unit);
    }

    @Override
    public WaitingDefaults defaults() {
        return defaultsFactory().create();
    }
}
