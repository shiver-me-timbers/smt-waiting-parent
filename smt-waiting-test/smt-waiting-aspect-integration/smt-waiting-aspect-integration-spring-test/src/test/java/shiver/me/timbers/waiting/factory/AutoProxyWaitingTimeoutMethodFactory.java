package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanChangeWaitingTimeoutMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilExceptionWaitingTimeoutMethod;
import shiver.me.timbers.waiting.execution.ShortWaitingTimeoutMethod;

@Component
public class AutoProxyWaitingTimeoutMethodFactory extends WaitingTimeoutMethodFactory {

    @Autowired
    public AutoProxyWaitingTimeoutMethodFactory(
        CanChangeWaitingTimeoutMethod canChangeWaitingTimeoutMethod,
        CanWaitUntilExceptionWaitingTimeoutMethod canWaitUntilExceptionWaitingTimeoutMethod,
        ShortWaitingTimeoutMethod shortWaitingTimeoutMethod
    ) {
        super(canChangeWaitingTimeoutMethod, canWaitUntilExceptionWaitingTimeoutMethod, shortWaitingTimeoutMethod);
    }
}
