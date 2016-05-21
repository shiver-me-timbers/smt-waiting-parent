package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.ClearAndWaitUntilSuccessWaitingForClass;

public class ClearWaitingForClassFactory extends ClearWaitingForFactory {

    public ClearWaitingForClassFactory() {
        this(new ClearAndWaitUntilSuccessWaitingForClass());
    }

    public ClearWaitingForClassFactory(
        ClearAndWaitUntilSuccessWaitingForClass clearAndWaitUntilSuccessWaitingForClass
    ) {
        super(clearAndWaitUntilSuccessWaitingForClass);
    }
}
