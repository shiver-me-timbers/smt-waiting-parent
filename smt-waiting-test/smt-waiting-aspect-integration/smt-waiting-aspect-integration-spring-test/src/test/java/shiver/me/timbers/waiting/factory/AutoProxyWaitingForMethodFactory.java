package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanAddExtraWaitingForMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilValidWaitingForMethod;

@Component
public class AutoProxyWaitingForMethodFactory extends WaitingForMethodFactory {

    @Autowired
    public AutoProxyWaitingForMethodFactory(
        CanWaitUntilValidWaitingForMethod canWaitUntilValidWaitingForMethod,
        CanWaitUntilTimeoutWaitingForMethod canWaitUntilTimeoutWaitingForMethod,
        CanAddExtraWaitingForMethod canAddExtraWaitingForMethod
    ) {
        super(canWaitUntilValidWaitingForMethod, canWaitUntilTimeoutWaitingForMethod, canAddExtraWaitingForMethod);
    }
}
