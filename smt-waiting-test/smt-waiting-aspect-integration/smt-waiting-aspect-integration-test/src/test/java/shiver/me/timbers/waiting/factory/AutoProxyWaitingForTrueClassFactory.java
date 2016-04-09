package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForTrueClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForTrueClass;

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
