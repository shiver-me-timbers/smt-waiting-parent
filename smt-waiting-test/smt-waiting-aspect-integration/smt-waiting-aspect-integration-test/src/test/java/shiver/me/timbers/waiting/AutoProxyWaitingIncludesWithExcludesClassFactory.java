package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoProxyWaitingIncludesWithExcludesClassFactory extends WaitingIncludesWithExcludesClassFactory {

    @Autowired
    public AutoProxyWaitingIncludesWithExcludesClassFactory(
        CanIgnoreIncludeExcludeWaitingIncludeClass canIgnoreIncludeExcludeWaitingIncludeClass
    ) {
        super(canIgnoreIncludeExcludeWaitingIncludeClass);
    }
}
