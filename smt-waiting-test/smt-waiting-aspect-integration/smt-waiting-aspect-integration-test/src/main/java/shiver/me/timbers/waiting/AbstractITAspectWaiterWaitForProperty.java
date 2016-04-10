package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingFor;
import shiver.me.timbers.waiting.validation.SuccessResult;

import java.util.concurrent.TimeUnit;

public abstract class AbstractITAspectWaiterWaitForProperty extends AbstractITWaiterWaitForProperty
    implements WaitingForFactoryAware, WaitingDefaultsFactoryAware {

    @Override
    public WaitingDefaults defaults() {
        return defaultsFactory().create();
    }

    @Override
    protected WaitingFor addWaitFor(long duration, TimeUnit unit, SuccessResult successResult) {
        return waitForFactory().create(duration, unit, successResult);
    }
}
