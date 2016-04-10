package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingForNotNullMethodFactory;

public abstract class AbstractITAspectWaiterWaitForNotNullPropertyMethod extends AbstractITAspectWaiterWaitForNotNullProperty {

    @Override
    public WaitingDefaultsMethodFactory defaultsFactory() {
        return new WaitingDefaultsMethodFactory();
    }

    @Override
    public WaitingForNotNullMethodFactory waitForNotNullFactory() {
        return new WaitingForNotNullMethodFactory();
    }
}
