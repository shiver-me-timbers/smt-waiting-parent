package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilValidWaitingForMethod;

public class WaitingForMethodFactory extends WaitingForFactory {

    public WaitingForMethodFactory() {
        this(new CanWaitUntilValidWaitingForMethod(), new CanWaitUntilTimeoutWaitingForMethod());
    }

    public WaitingForMethodFactory(
        CanWaitUntilValidWaitingForMethod canWaitUntilValidWaitingForMethod,
        CanWaitUntilTimeoutWaitingForMethod canWaitUntilTimeoutWaitingForMethod
    ) {
        super(canWaitUntilValidWaitingForMethod, canWaitUntilTimeoutWaitingForMethod);
    }
}
