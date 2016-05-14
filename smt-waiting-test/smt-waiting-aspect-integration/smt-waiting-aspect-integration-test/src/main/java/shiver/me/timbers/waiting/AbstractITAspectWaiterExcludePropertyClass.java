package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.ClearWaitingExcludeClassFactory;
import shiver.me.timbers.waiting.factory.ClearWaitingExcludeFactory;
import shiver.me.timbers.waiting.factory.WaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.WaitingExcludeClassFactory;

public abstract class AbstractITAspectWaiterExcludePropertyClass extends AbstractITAspectWaiterExcludeProperty {

    @Override
    public WaitingDefaultsClassFactory defaultsFactory() {
        return new WaitingDefaultsClassFactory();
    }

    @Override
    public WaitingExcludeClassFactory excludesFactory() {
        return new WaitingExcludeClassFactory();
    }

    @Override
    public ClearWaitingExcludeFactory clearExcludesFactory() {
        return new ClearWaitingExcludeClassFactory();
    }
}
