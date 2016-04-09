package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanIgnoreWaitingExcludeClass;
import shiver.me.timbers.waiting.execution.CannotIgnoreWaitingExcludeClass;

@Component
public class AutoProxyWaitingExcludeClassFactory extends WaitingExcludeClassFactory {

    @Autowired
    public AutoProxyWaitingExcludeClassFactory(
        CannotIgnoreWaitingExcludeClass cannotIgnoreWaitingExcludeClass,
        CanIgnoreWaitingExcludeClass canIgnoreWaitingExcludeClass
    ) {
        super(cannotIgnoreWaitingExcludeClass, canIgnoreWaitingExcludeClass);
    }
}
