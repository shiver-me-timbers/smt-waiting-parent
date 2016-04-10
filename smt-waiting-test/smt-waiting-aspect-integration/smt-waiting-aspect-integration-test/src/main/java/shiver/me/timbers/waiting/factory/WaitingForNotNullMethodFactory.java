package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForNotNullMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForNotNullMethod;

public class WaitingForNotNullMethodFactory extends WaitingForNotNullFactory {

    public WaitingForNotNullMethodFactory() {
        this(new CanWaitUntilWaitingForNotNullMethod(), new CanWaitUntilTimeoutWaitingForNotNullMethod());
    }

    public WaitingForNotNullMethodFactory(
        CanWaitUntilWaitingForNotNullMethod canWaitUntilWaitingForNotNullMethod,
        CanWaitUntilTimeoutWaitingForNotNullMethod canWaitUntilTimeoutWaitingForNotNullMethod
    ) {
        super(canWaitUntilWaitingForNotNullMethod, canWaitUntilTimeoutWaitingForNotNullMethod);
    }
}
