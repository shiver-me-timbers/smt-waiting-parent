package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanAddExtraWaitingForClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilValidWaitingForClass;

@Component
public class AutoProxyWaitingForClassFactory extends WaitingForClassFactory {

    @Autowired
    public AutoProxyWaitingForClassFactory(
        CanWaitUntilValidWaitingForClass canWaitUntilValidWaitingForClass,
        CanWaitUntilTimeoutWaitingForClass canWaitUntilTimeoutWaitingForClass,
        CanAddExtraWaitingForClass canAddExtraWaitingForClass
    ) {
        super(canWaitUntilValidWaitingForClass, canWaitUntilTimeoutWaitingForClass, canAddExtraWaitingForClass);
    }
}
