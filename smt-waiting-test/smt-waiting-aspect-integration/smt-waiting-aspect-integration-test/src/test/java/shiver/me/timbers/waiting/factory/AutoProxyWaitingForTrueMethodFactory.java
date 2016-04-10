package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanAddExtraWaitingForTrueMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilTimeoutWaitingForTrueMethod;
import shiver.me.timbers.waiting.execution.CanWaitUntilWaitingForTrueMethod;

@Component
public class AutoProxyWaitingForTrueMethodFactory extends WaitingForTrueMethodFactory {

    @Autowired
    public AutoProxyWaitingForTrueMethodFactory(
        CanWaitUntilWaitingForTrueMethod canWaitUntilWaitingForTrueMethod,
        CanWaitUntilTimeoutWaitingForTrueMethod canWaitUntilTimeoutWaitingForTrueMethod,
        CanAddExtraWaitingForTrueMethod canAddExtraWaitingForTrueMethod
    ) {
        super(
            canWaitUntilWaitingForTrueMethod,
            canWaitUntilTimeoutWaitingForTrueMethod,
            canAddExtraWaitingForTrueMethod
        );
    }
}
