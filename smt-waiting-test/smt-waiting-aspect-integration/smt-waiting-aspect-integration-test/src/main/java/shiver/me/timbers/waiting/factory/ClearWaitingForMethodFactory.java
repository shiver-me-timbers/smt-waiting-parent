package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.ClearAndWaitUntilSuccessWaitingForMethod;

public class ClearWaitingForMethodFactory extends ClearWaitingForFactory {

    public ClearWaitingForMethodFactory() {
        this(new ClearAndWaitUntilSuccessWaitingForMethod());
    }

    public ClearWaitingForMethodFactory(
        ClearAndWaitUntilSuccessWaitingForMethod clearAndWaitUntilSuccessWaitingForMethod
    ) {
        super(clearAndWaitUntilSuccessWaitingForMethod);
    }
}
