package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.ClearWaitingIncludeClassFactory;
import shiver.me.timbers.waiting.factory.WaitingDefaultsClassFactory;
import shiver.me.timbers.waiting.factory.WaitingIncludeClassFactory;

public abstract class AbstractITAspectWaiterIncludePropertyClass extends AbstractITAspectWaiterIncludeProperty {

    @Override
    public WaitingDefaultsClassFactory defaultsFactory() {
        return new WaitingDefaultsClassFactory();
    }

    @Override
    public WaitingIncludeClassFactory includesFactory() {
        return new WaitingIncludeClassFactory();
    }

    @Override
    public ClearWaitingIncludeClassFactory clearIncludesFactory() {
        return new ClearWaitingIncludeClassFactory();
    }
}
