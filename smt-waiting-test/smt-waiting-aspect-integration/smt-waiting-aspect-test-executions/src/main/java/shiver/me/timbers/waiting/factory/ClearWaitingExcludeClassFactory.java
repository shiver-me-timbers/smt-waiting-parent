package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.ClearAndAddExcludeWaitingForClass;

public class ClearWaitingExcludeClassFactory extends ClearWaitingExcludeFactory {

    public ClearWaitingExcludeClassFactory() {
        this(new ClearAndAddExcludeWaitingForClass());
    }

    public ClearWaitingExcludeClassFactory(ClearAndAddExcludeWaitingForClass clearAndAddExcludeWaitingForClass) {
        super(clearAndAddExcludeWaitingForClass);
    }
}
