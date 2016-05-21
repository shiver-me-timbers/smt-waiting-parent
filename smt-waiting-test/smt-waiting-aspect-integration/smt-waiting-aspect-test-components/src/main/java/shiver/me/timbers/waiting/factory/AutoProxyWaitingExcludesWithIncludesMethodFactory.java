package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CannotIgnoreExcludeIncludeWaitingExcludeMethod;
import shiver.me.timbers.waiting.execution.ExcludePrecedenceWaitingExcludeMethod;

@Component
public class AutoProxyWaitingExcludesWithIncludesMethodFactory extends WaitingExcludesWithIncludesMethodFactory {

    @Autowired
    public AutoProxyWaitingExcludesWithIncludesMethodFactory(
        CannotIgnoreExcludeIncludeWaitingExcludeMethod cannotIgnoreExcludeIncludeWaitingExcludeMethod,
        ExcludePrecedenceWaitingExcludeMethod excludePrecedenceWaitingExcludeMethod
    ) {
        super(cannotIgnoreExcludeIncludeWaitingExcludeMethod, excludePrecedenceWaitingExcludeMethod);
    }
}
