package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForTrueMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForTrueMethod;

public class WaitingForTrueMethodFactory extends WaitingForTrueFactory {

    public WaitingForTrueMethodFactory() {
        this(new CanWaitUntilWaitingForTrueMethod(), new CanWaitUntilTimeoutWaitingForTrueMethod());
    }

    public WaitingForTrueMethodFactory(
        CanWaitUntilWaitingForTrueMethod canWaitUntilWaitingForTrueMethod,
        CanWaitUntilTimeoutWaitingForTrueMethod canWaitUntilTimeoutWaitingForTrueMethod
    ) {
        super(canWaitUntilWaitingForTrueMethod, canWaitUntilTimeoutWaitingForTrueMethod);
    }
}
