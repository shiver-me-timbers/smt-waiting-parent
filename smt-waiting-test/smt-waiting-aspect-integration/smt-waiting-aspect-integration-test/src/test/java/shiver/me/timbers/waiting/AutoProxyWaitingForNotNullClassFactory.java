package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoProxyWaitingForNotNullClassFactory extends WaitingForNotNullClassFactory {

    @Autowired
    public AutoProxyWaitingForNotNullClassFactory(
        CanWaitUntilWaitingForNotNullClass canWaitUntilWaitingForNotNullClass,
        CanWaitUntilTimeoutWaitingForNotNullClass canWaitUntilTimeoutWaitingForNotNullClass
    ) {
        super(canWaitUntilWaitingForNotNullClass, canWaitUntilTimeoutWaitingForNotNullClass);
    }
}
