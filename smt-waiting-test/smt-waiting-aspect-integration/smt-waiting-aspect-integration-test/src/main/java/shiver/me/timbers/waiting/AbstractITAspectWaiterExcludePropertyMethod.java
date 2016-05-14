package shiver.me.timbers.waiting;

import shiver.me.timbers.waiting.factory.ClearWaitingExcludeFactory;
import shiver.me.timbers.waiting.factory.ClearWaitingExcludeMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingDefaultsMethodFactory;
import shiver.me.timbers.waiting.factory.WaitingExcludeMethodFactory;

public abstract class AbstractITAspectWaiterExcludePropertyMethod extends AbstractITAspectWaiterExcludeProperty {

    @Override
    public WaitingDefaultsMethodFactory defaultsFactory() {
        return new WaitingDefaultsMethodFactory();
    }

    @Override
    public WaitingExcludeMethodFactory excludesFactory() {
        return new WaitingExcludeMethodFactory();
    }

    @Override
    public ClearWaitingExcludeFactory clearExcludesFactory() {
        return new ClearWaitingExcludeMethodFactory();
    }
}
