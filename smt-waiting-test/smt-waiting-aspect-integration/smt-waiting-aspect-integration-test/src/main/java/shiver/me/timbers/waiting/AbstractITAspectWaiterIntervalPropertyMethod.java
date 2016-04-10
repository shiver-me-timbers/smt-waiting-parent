package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingIntervalMethodFactory;

public abstract class AbstractITAspectWaiterIntervalPropertyMethod extends AbstractITAspectWaiterIntervalProperty {

    @Override
    public WaitingDefaultsMethodFactory defaultsFactory() {
        return new WaitingDefaultsMethodFactory();
    }

    @Override
    public WaitingIntervalMethodFactory intervalFactory() {
        return new WaitingIntervalMethodFactory();
    }
}
