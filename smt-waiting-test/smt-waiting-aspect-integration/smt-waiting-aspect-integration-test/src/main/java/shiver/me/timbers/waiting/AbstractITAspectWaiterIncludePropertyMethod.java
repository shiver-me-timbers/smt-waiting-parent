package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.ClearWaitingIncludeMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingIncludeMethodFactory;

public abstract class AbstractITAspectWaiterIncludePropertyMethod extends AbstractITAspectWaiterIncludeProperty {

    @Override
    public WaitingDefaultsMethodFactory defaultsFactory() {
        return new WaitingDefaultsMethodFactory();
    }

    @Override
    public WaitingIncludeMethodFactory includesFactory() {
        return new WaitingIncludeMethodFactory();
    }

    @Override
    public ClearWaitingIncludeMethodFactory clearIncludesFactory() {
        return new ClearWaitingIncludeMethodFactory();
    }
}
