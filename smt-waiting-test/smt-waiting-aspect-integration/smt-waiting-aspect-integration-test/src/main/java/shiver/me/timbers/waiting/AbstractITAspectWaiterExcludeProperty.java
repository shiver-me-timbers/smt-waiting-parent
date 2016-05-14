package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingExclude;

import java.util.concurrent.TimeUnit;

public abstract class AbstractITAspectWaiterExcludeProperty extends AbstractITWaiterExcludeProperty
    implements WaitingExcludeFactoryAware, WaitingDefaultsFactoryAware, ClearWaitingExcludeFactoryAware {

    @Override
    public WaitingDefaults defaults() {
        return defaultsFactory().create();
    }

    @Override
    protected WaitingExclude addExclude(long duration, TimeUnit unit, Throwable exclude) {
        return excludesFactory().create(duration, unit, exclude);
    }

    @Override
    protected WaitingExclude clearThenAddExclude(long duration, TimeUnit unit, boolean clearExcludes, Throwable exclude) {
        return clearExcludesFactory().create(duration, unit, clearExcludes, exclude);
    }
}
