package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class AutoProxyWaitingTimeoutFactory extends WaitingTimeoutFactory {

    @Autowired
    public AutoProxyWaitingTimeoutFactory(
        CanChangeWaitingTimeoutClass canChangeWaitingTimeoutClass,
        CanWaitUntilExceptionWaitingTimeoutClass canWaitUntilExceptionWaitingTimeoutClass,
        ShortWaitingTimeoutClass shortWaitingTimeoutClass
    ) {
        super(canChangeWaitingTimeoutClass, canWaitUntilExceptionWaitingTimeoutClass, shortWaitingTimeoutClass);
    }
}
