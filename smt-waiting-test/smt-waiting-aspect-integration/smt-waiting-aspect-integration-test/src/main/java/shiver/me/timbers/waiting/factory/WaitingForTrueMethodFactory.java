package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanAddExtraWaitingForTrueMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForTrueMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForTrueMethod;

public class WaitingForTrueMethodFactory extends WaitingForTrueFactory {

    public WaitingForTrueMethodFactory() {
        this(
            new CanWaitUntilWaitingForTrueMethod(),
            new CanWaitUntilTimeoutWaitingForTrueMethod(),
            new CanAddExtraWaitingForTrueMethod()
        );
    }

    public WaitingForTrueMethodFactory(
        CanWaitUntilWaitingForTrueMethod canWaitUntilWaitingForTrueMethod,
        CanWaitUntilTimeoutWaitingForTrueMethod canWaitUntilTimeoutWaitingForTrueMethod,
        CanAddExtraWaitingForTrueMethod canAddExtraWaitingForTrueMethod
    ) {
        super(
            canWaitUntilWaitingForTrueMethod,
            canWaitUntilTimeoutWaitingForTrueMethod,
            canAddExtraWaitingForTrueMethod
        );
    }
}
