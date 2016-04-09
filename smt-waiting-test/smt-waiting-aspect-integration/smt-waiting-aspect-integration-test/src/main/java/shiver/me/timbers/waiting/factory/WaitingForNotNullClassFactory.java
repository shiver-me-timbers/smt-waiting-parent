package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForNotNullClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForNotNullClass;

public class WaitingForNotNullClassFactory extends WaitingForNotNullFactory {

    public WaitingForNotNullClassFactory() {
        this(new CanWaitUntilWaitingForNotNullClass(), new CanWaitUntilTimeoutWaitingForNotNullClass());
    }

    public WaitingForNotNullClassFactory(
        CanWaitUntilWaitingForNotNullClass canWaitUntilWaitingForNotNullClass,
        CanWaitUntilTimeoutWaitingForNotNullClass canWaitUntilTimeoutWaitingForNotNullClass
    ) {
        super(canWaitUntilWaitingForNotNullClass, canWaitUntilTimeoutWaitingForNotNullClass);
    }
}
