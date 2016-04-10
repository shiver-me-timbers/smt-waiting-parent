package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanAddExtraWaitingForNotNullClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForNotNullClass;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForNotNullClass;

@Component
public class AutoProxyWaitingForNotNullClassFactory extends WaitingForNotNullClassFactory {

    @Autowired
    public AutoProxyWaitingForNotNullClassFactory(
        CanWaitUntilWaitingForNotNullClass canWaitUntilWaitingForNotNullClass,
        CanWaitUntilTimeoutWaitingForNotNullClass canWaitUntilTimeoutWaitingForNotNullClass,
        CanAddExtraWaitingForNotNullClass canAddExtraWaitingForNotNullClass
    ) {
        super(
            canWaitUntilWaitingForNotNullClass,
            canWaitUntilTimeoutWaitingForNotNullClass,
            canAddExtraWaitingForNotNullClass
        );
    }
}
