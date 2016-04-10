package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanAddExtraWaitingForClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilValidWaitingForClass;

public class WaitingForClassFactory extends WaitingForFactory {

    public WaitingForClassFactory() {
        this(
            new CanWaitUntilValidWaitingForClass(),
            new CanWaitUntilTimeoutWaitingForClass(),
            new CanAddExtraWaitingForClass()
        );
    }

    public WaitingForClassFactory(
        CanWaitUntilValidWaitingForClass canWaitUntilValidWaitingForClass,
        CanWaitUntilTimeoutWaitingForClass canWaitUntilTimeoutWaitingForClass,
        CanAddExtraWaitingForClass canAddExtraWaitingForClass
    ) {
        super(canWaitUntilValidWaitingForClass, canWaitUntilTimeoutWaitingForClass, canAddExtraWaitingForClass);
    }
}
