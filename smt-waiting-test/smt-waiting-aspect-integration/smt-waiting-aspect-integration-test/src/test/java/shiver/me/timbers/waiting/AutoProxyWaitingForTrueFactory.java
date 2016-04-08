package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class AutoProxyWaitingForTrueFactory extends WaitingForTrueFactory {

    @Autowired
    public AutoProxyWaitingForTrueFactory(
        CanWaitUntilWaitingForTrueClass canWaitUntilWaitingForTrueClass,
        CanWaitUntilTimeoutWaitingForTrueClass canWaitUntilTimeoutWaitingForTrueClass
    ) {
        add(canWaitUntilWaitingForTrueClass, 500L, MILLISECONDS, true);
        add(canWaitUntilTimeoutWaitingForTrueClass, 200L, MILLISECONDS, true);
    }
}
