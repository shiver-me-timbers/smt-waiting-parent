package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanAddExtraWaitingForTrueClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForTrueClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForTrueClass;

public class WaitingForTrueClassFactory extends WaitingForTrueFactory {

    public WaitingForTrueClassFactory() {
        this(
            new CanWaitUntilWaitingForTrueClass(),
            new CanWaitUntilTimeoutWaitingForTrueClass(),
            new CanAddExtraWaitingForTrueClass()
        );
    }

    public WaitingForTrueClassFactory(
        CanWaitUntilWaitingForTrueClass canWaitUntilWaitingForTrueClass,
        CanWaitUntilTimeoutWaitingForTrueClass canWaitUntilTimeoutWaitingForTrueClass,
        CanAddExtraWaitingForTrueClass canAddExtraWaitingForTrueClass
    ) {
        super(canWaitUntilWaitingForTrueClass, canWaitUntilTimeoutWaitingForTrueClass, canAddExtraWaitingForTrueClass);
    }
}
