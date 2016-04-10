package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingInclude;

import java.util.concurrent.TimeUnit;

public abstract class AbstractITAspectWaiterIncludeProperty extends AbstractITWaiterIncludeProperty
    implements WaitingIncludeFactoryAware, WaitingDefaultsFactoryAware {

    @Override
    public WaitingDefaults defaults() {
        return defaultsFactory().create();
    }

    @Override
    protected WaitingInclude addInclude(long duration, TimeUnit unit, Throwable exclude) {
        return includesFactory().create(duration, unit, exclude);
    }
}
