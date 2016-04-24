package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingFor;

import java.util.concurrent.TimeUnit;

public abstract class AbstractITAspectWaiterWaitForProperty extends AbstractITWaiterWaitForProperty
    implements WaitingForFactoryAware, WaitingDefaultsFactoryAware, ClearWaitingForFactoryAware {

    @Override
    public WaitingDefaults defaults() {
        return defaultsFactory().create();
    }

    @Override
    protected WaitingFor addWaitFor(long duration, TimeUnit unit, ResultValidator validator) {
        return waitForFactory().create(duration, unit, validator);
    }

    @Override
    protected WaitingFor clearThenAddWaitFor(
        long duration,
        TimeUnit unit,
        boolean clearWaitFor,
        ResultValidator validator
    ) {
        return clearWaitForFactory().create(duration, unit, clearWaitFor, validator);
    }
}
