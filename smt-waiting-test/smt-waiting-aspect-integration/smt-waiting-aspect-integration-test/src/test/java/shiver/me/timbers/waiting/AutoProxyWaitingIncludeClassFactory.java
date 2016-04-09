package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoProxyWaitingIncludeClassFactory extends WaitingIncludeClassFactory {

    @Autowired
    public AutoProxyWaitingIncludeClassFactory(
        CanIgnoreWaitingIncludeClass canIgnoreWaitingIncludeClass,
        CanIgnoreAllWaitingIncludeClass canIgnoreAllWaitingIncludeClass
    ) {
        super(canIgnoreWaitingIncludeClass, canIgnoreAllWaitingIncludeClass);
    }
}
