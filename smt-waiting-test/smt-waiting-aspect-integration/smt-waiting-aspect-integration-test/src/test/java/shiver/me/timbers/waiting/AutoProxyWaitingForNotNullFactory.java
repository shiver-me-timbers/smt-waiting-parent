package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class AutoProxyWaitingForNotNullFactory extends WaitingForNotNullFactory {

    @Autowired
    public AutoProxyWaitingForNotNullFactory(
        CanWaitUntilWaitingForNotNullClass canWaitUntilWaitingForNotNullClass,
        CanWaitUntilTimeoutWaitingForNotNullClass canWaitUntilTimeoutWaitingForNotNullClass
    ) {
        super(canWaitUntilWaitingForNotNullClass, canWaitUntilTimeoutWaitingForNotNullClass);
    }
}
