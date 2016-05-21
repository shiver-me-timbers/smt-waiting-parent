package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanAddExtraWaitingForMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilValidWaitingForMethod;

public class WaitingForMethodFactory extends WaitingForFactory {

    public WaitingForMethodFactory() {
        this(
            new CanWaitUntilValidWaitingForMethod(),
            new CanWaitUntilTimeoutWaitingForMethod(),
            new CanAddExtraWaitingForMethod()
        );
    }

    public WaitingForMethodFactory(
        CanWaitUntilValidWaitingForMethod canWaitUntilValidWaitingForMethod,
        CanWaitUntilTimeoutWaitingForMethod canWaitUntilTimeoutWaitingForMethod,
        CanAddExtraWaitingForMethod canAddExtraWaitingForMethod
    ) {
        super(canWaitUntilValidWaitingForMethod, canWaitUntilTimeoutWaitingForMethod, canAddExtraWaitingForMethod);
    }
}
