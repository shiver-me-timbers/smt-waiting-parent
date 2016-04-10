package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.WaitingTimeoutClassFactory;

public abstract class AbstractITAspectWaiterTimeoutPropertyClass extends AbstractITAspectWaiterTimeoutProperty {

    @Override
    public WaitingDefaultsClassFactory defaultsFactory() {
        return new WaitingDefaultsClassFactory();
    }

    @Override
    public WaitingTimeoutClassFactory timeoutFactory() {
        return new WaitingTimeoutClassFactory();
    }
}
