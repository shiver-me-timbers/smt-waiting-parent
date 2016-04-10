package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingForTrueMethodFactory;

public abstract class AbstractITAspectWaiterWaitForTruePropertyMethod extends AbstractITAspectWaiterWaitForTrueProperty {

    @Override
    public WaitingDefaultsMethodFactory defaultsFactory() {
        return new WaitingDefaultsMethodFactory();
    }

    @Override
    public WaitingForTrueMethodFactory waitForTrueFactory() {
        return new WaitingForTrueMethodFactory();
    }
}
