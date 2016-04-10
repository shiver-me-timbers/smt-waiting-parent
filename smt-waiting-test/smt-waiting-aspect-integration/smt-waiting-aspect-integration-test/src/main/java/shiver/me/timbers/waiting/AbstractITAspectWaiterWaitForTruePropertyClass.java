package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.WaitingForTrueClassFactory;

public abstract class AbstractITAspectWaiterWaitForTruePropertyClass extends AbstractITAspectWaiterWaitForTrueProperty {

    @Override
    public WaitingDefaultsClassFactory defaultsFactory() {
        return new WaitingDefaultsClassFactory();
    }

    @Override
    public WaitingForTrueClassFactory waitForTrueFactory() {
        return new WaitingForTrueClassFactory();
    }
}
