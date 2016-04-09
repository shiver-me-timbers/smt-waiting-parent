package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
