package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingTimeoutMethodFactory;

public abstract class AbstractITAspectWaiterTimeoutPropertyMethod extends AbstractITAspectWaiterTimeoutProperty {

    @Override
    public WaitingDefaultsMethodFactory defaultsFactory() {
        return new WaitingDefaultsMethodFactory();
    }

    @Override
    public WaitingTimeoutMethodFactory timeoutFactory() {
        return new WaitingTimeoutMethodFactory();
    }
}
