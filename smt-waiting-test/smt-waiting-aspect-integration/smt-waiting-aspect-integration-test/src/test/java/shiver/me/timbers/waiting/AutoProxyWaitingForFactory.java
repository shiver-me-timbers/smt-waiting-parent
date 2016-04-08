package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoProxyWaitingForFactory extends WaitingForFactory {

    @Autowired
    public AutoProxyWaitingForFactory(
        CanWaitUntilValidWaitingForClass canWaitUntilValidWaitingForClass,
        CanWaitUntilTimeoutWaitingForClass canWaitUntilTimeoutWaitingForClass
    ) {
        super(canWaitUntilValidWaitingForClass, canWaitUntilTimeoutWaitingForClass);
    }
}
