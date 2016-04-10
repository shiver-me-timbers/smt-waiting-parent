package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanAddExtraWaitingIncludeMethod;
import shiver.me.timbers.waiting.execution.CanIgnoreAllWaitingIncludeMethod;
import shiver.me.timbers.waiting.execution.CanIgnoreWaitingIncludeMethod;

@Component
public class AutoProxyWaitingIncludeMethodFactory extends WaitingIncludeMethodFactory {

    @Autowired
    public AutoProxyWaitingIncludeMethodFactory(
        CanIgnoreWaitingIncludeMethod canIgnoreWaitingIncludeMethod,
        CanIgnoreAllWaitingIncludeMethod canIgnoreAllWaitingIncludeMethod,
        CanAddExtraWaitingIncludeMethod canAddExtraWaitingIncludeMethod
    ) {
        super(canIgnoreWaitingIncludeMethod, canIgnoreAllWaitingIncludeMethod, canAddExtraWaitingIncludeMethod);
    }
}
