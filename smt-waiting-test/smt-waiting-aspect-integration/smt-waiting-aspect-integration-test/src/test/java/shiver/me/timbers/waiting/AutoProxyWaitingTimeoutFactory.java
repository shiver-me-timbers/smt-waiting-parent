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
        add(canChangeWaitingTimeoutClass, 200L, MILLISECONDS);
        add(canWaitUntilExceptionWaitingTimeoutClass, 500L, MILLISECONDS);
        add(shortWaitingTimeoutClass, 10L, MILLISECONDS);
    }
}
