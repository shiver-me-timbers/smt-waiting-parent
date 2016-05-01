package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.ClearAndAddIncludeWaitingForMethod;

public class ClearWaitingIncludeMethodFactory extends ClearWaitingIncludeFactory {

    public ClearWaitingIncludeMethodFactory() {
        this(new ClearAndAddIncludeWaitingForMethod());
    }

    public ClearWaitingIncludeMethodFactory(ClearAndAddIncludeWaitingForMethod clearAndAddIncludeWaitingForMethod) {
        super(clearAndAddIncludeWaitingForMethod);
    }
}
