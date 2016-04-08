package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoProxyWaitingIncludesWithExcludesFactory extends WaitingIncludesWithExcludesFactory {

    @Autowired
    public AutoProxyWaitingIncludesWithExcludesFactory(
        CanIgnoreIncludeExcludeWaitingIncludeClass canIgnoreIncludeExcludeWaitingIncludeClass
    ) {
        super(canIgnoreIncludeExcludeWaitingIncludeClass);
    }
}
