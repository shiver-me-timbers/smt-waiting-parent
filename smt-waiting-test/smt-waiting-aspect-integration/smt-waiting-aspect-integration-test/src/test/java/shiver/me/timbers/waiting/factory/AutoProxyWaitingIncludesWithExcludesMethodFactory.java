package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CanIgnoreIncludeExcludeWaitingIncludeMethod;

@Component
public class AutoProxyWaitingIncludesWithExcludesMethodFactory extends WaitingIncludesWithExcludesMethodFactory {

    @Autowired
    public AutoProxyWaitingIncludesWithExcludesMethodFactory(
        CanIgnoreIncludeExcludeWaitingIncludeMethod canIgnoreIncludeExcludeWaitingIncludeMethod
    ) {
        super(canIgnoreIncludeExcludeWaitingIncludeMethod);
    }
}
