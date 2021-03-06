package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.ClearWaitingForFactory;
import shiver.me.timbers.waiting.factory.ClearWaitingForMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingForMethodFactory;

public abstract class AbstractITAspectWaiterWaitForPropertyMethod extends AbstractITAspectWaiterWaitForProperty {

    @Override
    public WaitingDefaultsMethodFactory defaultsFactory() {
        return new WaitingDefaultsMethodFactory();
    }

    @Override
    public WaitingForMethodFactory waitForFactory() {
        return new WaitingForMethodFactory();
    }

    @Override
    public ClearWaitingForFactory clearWaitForFactory() {
        return new ClearWaitingForMethodFactory();
    }
}
