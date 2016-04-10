package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanIgnoreWaitingExcludeMethod;
import shiver.me.timbers.waiting.execution.CannotIgnoreWaitingExcludeMethod;

@Component
public class AutoProxyWaitingExcludeMethodFactory extends WaitingExcludeMethodFactory {

    @Autowired
    public AutoProxyWaitingExcludeMethodFactory(
        CannotIgnoreWaitingExcludeMethod cannotIgnoreWaitingExcludeMethod,
        CanIgnoreWaitingExcludeMethod canIgnoreWaitingExcludeMethod
    ) {
        super(cannotIgnoreWaitingExcludeMethod, canIgnoreWaitingExcludeMethod);
    }
}
