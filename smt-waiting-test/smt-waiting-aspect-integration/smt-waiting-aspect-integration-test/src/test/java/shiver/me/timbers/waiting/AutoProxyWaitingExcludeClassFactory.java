package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
