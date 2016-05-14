package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingInclude;

import java.util.concurrent.TimeUnit;

public abstract class AbstractITAspectWaiterIncludeProperty extends AbstractITWaiterIncludeProperty
    implements WaitingIncludeFactoryAware, WaitingDefaultsFactoryAware, ClearWaitingIncludeFactoryAware {

    @Override
    public WaitingDefaults defaults() {
        return defaultsFactory().create();
    }

    @Override
    protected WaitingInclude addInclude(long duration, TimeUnit unit, Throwable include) {
        return includesFactory().create(duration, unit, include);
    }

    @Override
    protected WaitingInclude clearThenAddInclude(long duration, TimeUnit unit, boolean clearIncludes, Throwable include) {
        return clearIncludesFactory().create(duration, unit, clearIncludes, include);
    }
}
