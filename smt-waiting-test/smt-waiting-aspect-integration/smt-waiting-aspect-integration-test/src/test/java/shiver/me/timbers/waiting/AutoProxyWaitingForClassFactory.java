package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoProxyWaitingForClassFactory extends WaitingForClassFactory {

    @Autowired
    public AutoProxyWaitingForClassFactory(
        CanWaitUntilValidWaitingForClass canWaitUntilValidWaitingForClass,
        CanWaitUntilTimeoutWaitingForClass canWaitUntilTimeoutWaitingForClass
    ) {
        super(canWaitUntilValidWaitingForClass, canWaitUntilTimeoutWaitingForClass);
    }
}
