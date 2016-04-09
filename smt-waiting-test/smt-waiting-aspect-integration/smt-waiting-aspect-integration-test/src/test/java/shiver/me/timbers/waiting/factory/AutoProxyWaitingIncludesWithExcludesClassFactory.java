package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanIgnoreIncludeExcludeWaitingIncludeClass;

@Component
public class AutoProxyWaitingIncludesWithExcludesClassFactory extends WaitingIncludesWithExcludesClassFactory {

    @Autowired
    public AutoProxyWaitingIncludesWithExcludesClassFactory(
        CanIgnoreIncludeExcludeWaitingIncludeClass canIgnoreIncludeExcludeWaitingIncludeClass
    ) {
        super(canIgnoreIncludeExcludeWaitingIncludeClass);
    }
}
