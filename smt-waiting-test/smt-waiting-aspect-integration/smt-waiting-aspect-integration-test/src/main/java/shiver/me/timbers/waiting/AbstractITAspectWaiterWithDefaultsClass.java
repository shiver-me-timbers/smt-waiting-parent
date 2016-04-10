package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingWithDefaultsClassFactory;

public abstract class AbstractITAspectWaiterWithDefaultsClass extends AbstractITAspectWaiterWithDefaults {

    @Override
    public WaitingWithDefaultsClassFactory withDefaultsFactory() {
        return new WaitingWithDefaultsClassFactory();
    }
}
