package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.WaitingForClassFactory;

public abstract class AbstractITAspectWaiterWaitForPropertyClass extends AbstractITAspectWaiterWaitForProperty {

    @Override
    public WaitingDefaultsClassFactory defaultsFactory() {
        return new WaitingDefaultsClassFactory();
    }

    @Override
    public WaitingForClassFactory waitForFactory() {
        return new WaitingForClassFactory();
    }
}
