package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanAddExtraWaitingForNotNullMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForNotNullMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForNotNullMethod;

public class WaitingForNotNullMethodFactory extends WaitingForNotNullFactory {

    public WaitingForNotNullMethodFactory() {
        this(
            new CanWaitUntilWaitingForNotNullMethod(),
            new CanWaitUntilTimeoutWaitingForNotNullMethod(),
            new CanAddExtraWaitingForNotNullMethod()
        );
    }

    public WaitingForNotNullMethodFactory(
        CanWaitUntilWaitingForNotNullMethod canWaitUntilWaitingForNotNullMethod,
        CanWaitUntilTimeoutWaitingForNotNullMethod canWaitUntilTimeoutWaitingForNotNullMethod,
        CanAddExtraWaitingForNotNullMethod canAddExtraWaitingForNotNullMethod
    ) {
        super(
            canWaitUntilWaitingForNotNullMethod,
            canWaitUntilTimeoutWaitingForNotNullMethod,
            canAddExtraWaitingForNotNullMethod
        );
    }
}
