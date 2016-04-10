package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.WaitingForNotNullClassFactory;

public abstract class AbstractITAspectWaiterWaitForNotNullPropertyClass extends AbstractITAspectWaiterWaitForNotNullProperty {

    @Override
    public WaitingDefaultsClassFactory defaultsFactory() {
        return new WaitingDefaultsClassFactory();
    }

    @Override
    public WaitingForNotNullClassFactory waitForNotNullFactory() {
        return new WaitingForNotNullClassFactory();
    }
}
