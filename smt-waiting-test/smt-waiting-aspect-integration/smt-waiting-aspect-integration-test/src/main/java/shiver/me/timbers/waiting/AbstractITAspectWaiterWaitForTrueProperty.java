package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingDefaults;
import shiver.me.timbers.waiting.execution.WaitingForTrue;

import java.util.concurrent.TimeUnit;

public abstract class AbstractITAspectWaiterWaitForTrueProperty extends AbstractITWaiterWaitForTrueProperty
    implements WaitingForTrueFactoryAware, WaitingDefaultsFactoryAware {

    @Override
    public WaitingDefaults defaults() {
        return defaultsFactory().create();
    }

    @Override
    protected WaitingForTrue overrideWaitForTrue(long duration, TimeUnit unit, boolean isTrue) {
        return waitForTrueFactory().create(duration, unit, isTrue);
    }
}
