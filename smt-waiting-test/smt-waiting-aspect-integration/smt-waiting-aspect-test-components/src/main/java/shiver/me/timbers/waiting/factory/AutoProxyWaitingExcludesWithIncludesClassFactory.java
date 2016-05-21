package shiver.me.timbers.waiting.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shiver.me.timbers.waiting.execution.CannotIgnoreExcludeIncludeWaitingExcludeClass;
import shiver.me.timbers.waiting.execution.ExcludePrecedenceWaitingExcludeClass;

@Component
public class AutoProxyWaitingExcludesWithIncludesClassFactory extends WaitingExcludesWithIncludesClassFactory {

    @Autowired
    public AutoProxyWaitingExcludesWithIncludesClassFactory(
        CannotIgnoreExcludeIncludeWaitingExcludeClass cannotIgnoreExcludeIncludeWaitingExcludeClass,
        ExcludePrecedenceWaitingExcludeClass excludePrecedenceWaitingExcludeClass
    ) {
        super(cannotIgnoreExcludeIncludeWaitingExcludeClass, excludePrecedenceWaitingExcludeClass);
    }
}
