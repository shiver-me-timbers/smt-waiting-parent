package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.ClearAndAddIncludeWaitingForClass;

public class ClearWaitingIncludeClassFactory extends ClearWaitingIncludeFactory {

    public ClearWaitingIncludeClassFactory() {
        this(new ClearAndAddIncludeWaitingForClass());
    }

    public ClearWaitingIncludeClassFactory(ClearAndAddIncludeWaitingForClass clearAndAddIncludeWaitingForClass) {
        super(clearAndAddIncludeWaitingForClass);
    }
}
