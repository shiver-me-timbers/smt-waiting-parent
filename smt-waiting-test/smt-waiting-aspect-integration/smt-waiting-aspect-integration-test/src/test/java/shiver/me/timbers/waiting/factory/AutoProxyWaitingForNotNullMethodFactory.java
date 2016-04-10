package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForNotNullMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForNotNullMethod;

@Component
public class AutoProxyWaitingForNotNullMethodFactory extends WaitingForNotNullMethodFactory {

    @Autowired
    public AutoProxyWaitingForNotNullMethodFactory(
        CanWaitUntilWaitingForNotNullMethod canWaitUntilWaitingForNotNullMethod,
        CanWaitUntilTimeoutWaitingForNotNullMethod canWaitUntilTimeoutWaitingForNotNullMethod
    ) {
        super(canWaitUntilWaitingForNotNullMethod, canWaitUntilTimeoutWaitingForNotNullMethod);
    }
}
