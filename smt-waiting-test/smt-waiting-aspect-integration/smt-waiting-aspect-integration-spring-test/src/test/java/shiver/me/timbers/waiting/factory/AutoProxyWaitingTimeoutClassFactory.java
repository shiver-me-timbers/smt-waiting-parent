package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanChangeWaitingTimeoutClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilExceptionWaitingTimeoutClass;
import shiver.me.timbers.waiting.execution.ShortWaitingTimeoutClass;

@Component
public class AutoProxyWaitingTimeoutClassFactory extends WaitingTimeoutClassFactory {

    @Autowired
    public AutoProxyWaitingTimeoutClassFactory(
        CanChangeWaitingTimeoutClass canChangeWaitingTimeoutClass,
        CanWaitUntilExceptionWaitingTimeoutClass canWaitUntilExceptionWaitingTimeoutClass,
        ShortWaitingTimeoutClass shortWaitingTimeoutClass
    ) {
        super(canChangeWaitingTimeoutClass, canWaitUntilExceptionWaitingTimeoutClass, shortWaitingTimeoutClass);
    }
}
