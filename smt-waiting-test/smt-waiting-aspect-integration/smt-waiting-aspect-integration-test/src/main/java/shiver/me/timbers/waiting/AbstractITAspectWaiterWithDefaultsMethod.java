package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingWithDefaultsMethodFactory;

public abstract class AbstractITAspectWaiterWithDefaultsMethod extends AbstractITAspectWaiterWithDefaults {

    @Override
    public WaitingWithDefaultsMethodFactory withDefaultsFactory() {
        return new WaitingWithDefaultsMethodFactory();
    }
}
