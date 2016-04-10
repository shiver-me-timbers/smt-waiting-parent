package shiver.me.timbers.waiting;

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
}
