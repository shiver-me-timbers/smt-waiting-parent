package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.ClearAndAddExcludeWaitingForMethod;

public class ClearWaitingExcludeMethodFactory extends ClearWaitingExcludeFactory {

    public ClearWaitingExcludeMethodFactory() {
        this(new ClearAndAddExcludeWaitingForMethod());
    }

    public ClearWaitingExcludeMethodFactory(ClearAndAddExcludeWaitingForMethod clearAndAddExcludeWaitingForMethod) {
        super(clearAndAddExcludeWaitingForMethod);
    }
}
