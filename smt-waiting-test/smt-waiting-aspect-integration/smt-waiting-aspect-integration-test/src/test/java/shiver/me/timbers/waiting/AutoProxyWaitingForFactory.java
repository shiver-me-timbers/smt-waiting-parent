package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

@Component
public class AutoProxyWaitingForFactory extends WaitingForFactory {

    @Autowired
    public AutoProxyWaitingForFactory(
        CanWaitUntilValidWaitingForClass canWaitUntilValidWaitingForClass,
        CanWaitUntilTimeoutWaitingForClass canWaitUntilTimeoutWaitingForClass
    ) {
        final ValidResult validator = new ValidResult();
        add(canWaitUntilValidWaitingForClass, 500L, MILLISECONDS, validator);
        add(canWaitUntilTimeoutWaitingForClass, 200L, MILLISECONDS, validator);
    }
}
