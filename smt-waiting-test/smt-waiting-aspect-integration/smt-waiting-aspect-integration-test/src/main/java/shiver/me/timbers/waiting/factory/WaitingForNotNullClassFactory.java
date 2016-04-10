package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanAddExtraWaitingForNotNullClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForNotNullClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForNotNullClass;

public class WaitingForNotNullClassFactory extends WaitingForNotNullFactory {

    public WaitingForNotNullClassFactory() {
        this(
            new CanWaitUntilWaitingForNotNullClass(),
            new CanWaitUntilTimeoutWaitingForNotNullClass(),
            new CanAddExtraWaitingForNotNullClass()
        );
    }

    public WaitingForNotNullClassFactory(
        CanWaitUntilWaitingForNotNullClass canWaitUntilWaitingForNotNullClass,
        CanWaitUntilTimeoutWaitingForNotNullClass canWaitUntilTimeoutWaitingForNotNullClass,
        CanAddExtraWaitingForNotNullClass canAddExtraWaitingForNotNullClass
    ) {
        super(
            canWaitUntilWaitingForNotNullClass,
            canWaitUntilTimeoutWaitingForNotNullClass,
            canAddExtraWaitingForNotNullClass
        );
    }
}
