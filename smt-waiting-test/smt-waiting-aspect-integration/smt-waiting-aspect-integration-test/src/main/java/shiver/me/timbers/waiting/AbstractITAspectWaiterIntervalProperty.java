package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingInterval;

import java.util.concurrent.TimeUnit;

public abstract class AbstractITAspectWaiterIntervalProperty extends AbstractITWaiterIntervalProperty
    implements WaitingIntervalFactoryAware, WaitingDefaultsFactoryAware {

    @Override
    protected WaitingInterval overrideInterval(long duration, TimeUnit unit) {
        return intervalFactory().create(duration, unit);
    }

    @Override
    public WaitingDefaults defaults() {
        return defaultsFactory().create();
    }
}
