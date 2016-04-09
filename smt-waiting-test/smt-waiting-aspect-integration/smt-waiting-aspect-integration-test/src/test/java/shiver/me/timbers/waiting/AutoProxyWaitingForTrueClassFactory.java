package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoProxyWaitingForTrueClassFactory extends WaitingForTrueClassFactory {

    @Autowired
    public AutoProxyWaitingForTrueClassFactory(
        CanWaitUntilWaitingForTrueClass canWaitUntilWaitingForTrueClass,
        CanWaitUntilTimeoutWaitingForTrueClass canWaitUntilTimeoutWaitingForTrueClass
    ) {
        super(canWaitUntilWaitingForTrueClass, canWaitUntilTimeoutWaitingForTrueClass);
    }
}
