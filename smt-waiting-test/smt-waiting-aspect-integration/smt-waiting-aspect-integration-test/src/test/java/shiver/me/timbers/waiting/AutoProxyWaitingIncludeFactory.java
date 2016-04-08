package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoProxyWaitingIncludeFactory extends WaitingIncludeFactory {

    @Autowired
    public AutoProxyWaitingIncludeFactory(
        CanIgnoreWaitingIncludeClass canIgnoreWaitingIncludeClass,
        CanIgnoreAllWaitingIncludeClass canIgnoreAllWaitingIncludeClass
    ) {
        super(canIgnoreWaitingIncludeClass, canIgnoreAllWaitingIncludeClass);
    }
}
