package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.execution.WaitingDefaults;

public abstract class AbstractITAspectWaiterWithDefaults extends AbstractITWaiterWithDefaults
    implements WaitingWithDefaultsFactoryAware {

    @Override
    public WaitingDefaults withDefaults(boolean defaults) {
        return withDefaultsFactory().create(defaults);
    }
}
