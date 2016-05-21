package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanChangeWaitingTimeoutMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilExceptionWaitingTimeoutMethod;
import shiver.me.timbers.waiting.execution.ShortWaitingTimeoutMethod;

public class WaitingTimeoutMethodFactory extends WaitingTimeoutFactory {

    public WaitingTimeoutMethodFactory() {
        this(
            new CanChangeWaitingTimeoutMethod(),
            new CanWaitUntilExceptionWaitingTimeoutMethod(),
            new ShortWaitingTimeoutMethod()
        );
    }

    public WaitingTimeoutMethodFactory(
        CanChangeWaitingTimeoutMethod canChangeWaitingTimeoutMethod,
        CanWaitUntilExceptionWaitingTimeoutMethod canWaitUntilExceptionWaitingTimeoutMethod,
        ShortWaitingTimeoutMethod shortWaitingTimeoutMethod
    ) {
        super(canChangeWaitingTimeoutMethod, canWaitUntilExceptionWaitingTimeoutMethod, shortWaitingTimeoutMethod);
    }
}
