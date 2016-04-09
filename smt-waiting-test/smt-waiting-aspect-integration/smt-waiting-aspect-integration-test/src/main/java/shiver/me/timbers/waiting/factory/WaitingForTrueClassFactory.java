package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForTrueClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForTrueClass;

public class WaitingForTrueClassFactory extends WaitingForTrueFactory {

    public WaitingForTrueClassFactory() {
        this(new CanWaitUntilWaitingForTrueClass(), new CanWaitUntilTimeoutWaitingForTrueClass());
    }

    public WaitingForTrueClassFactory(
        CanWaitUntilWaitingForTrueClass canWaitUntilWaitingForTrueClass,
        CanWaitUntilTimeoutWaitingForTrueClass canWaitUntilTimeoutWaitingForTrueClass
    ) {
        super(canWaitUntilWaitingForTrueClass, canWaitUntilTimeoutWaitingForTrueClass);
    }
}
