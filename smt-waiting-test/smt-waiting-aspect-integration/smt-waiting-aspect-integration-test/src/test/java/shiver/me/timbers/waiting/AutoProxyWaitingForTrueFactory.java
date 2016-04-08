package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoProxyWaitingForTrueFactory extends WaitingForTrueFactory {

    @Autowired
    public AutoProxyWaitingForTrueFactory(
        CanWaitUntilWaitingForTrueClass canWaitUntilWaitingForTrueClass,
        CanWaitUntilTimeoutWaitingForTrueClass canWaitUntilTimeoutWaitingForTrueClass
    ) {
        super(canWaitUntilWaitingForTrueClass, canWaitUntilTimeoutWaitingForTrueClass);
    }
}
