package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.WaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.WaitingIntervalClassFactory;

public abstract class AbstractITAspectWaiterIntervalPropertyClass extends AbstractITAspectWaiterIntervalProperty {

    @Override
    public WaitingDefaultsClassFactory defaultsFactory() {
        return new WaitingDefaultsClassFactory();
    }

    @Override
    public WaitingIntervalClassFactory intervalFactory() {
        return new WaitingIntervalClassFactory();
    }
}
