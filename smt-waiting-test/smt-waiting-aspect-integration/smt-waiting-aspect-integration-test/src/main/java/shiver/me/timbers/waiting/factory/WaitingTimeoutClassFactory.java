package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanChangeWaitingTimeoutClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilExceptionWaitingTimeoutClass;
import shiver.me.timbers.waiting.execution.ShortWaitingTimeoutClass;

public class WaitingTimeoutClassFactory extends WaitingTimeoutFactory {

    public WaitingTimeoutClassFactory() {
        this(
            new CanChangeWaitingTimeoutClass(),
            new CanWaitUntilExceptionWaitingTimeoutClass(),
            new ShortWaitingTimeoutClass()
        );
    }

    public WaitingTimeoutClassFactory(
        CanChangeWaitingTimeoutClass canChangeWaitingTimeoutClass,
        CanWaitUntilExceptionWaitingTimeoutClass canWaitUntilExceptionWaitingTimeoutClass,
        ShortWaitingTimeoutClass shortWaitingTimeoutClass
    ) {
        super(canChangeWaitingTimeoutClass, canWaitUntilExceptionWaitingTimeoutClass, shortWaitingTimeoutClass);
    }
}
