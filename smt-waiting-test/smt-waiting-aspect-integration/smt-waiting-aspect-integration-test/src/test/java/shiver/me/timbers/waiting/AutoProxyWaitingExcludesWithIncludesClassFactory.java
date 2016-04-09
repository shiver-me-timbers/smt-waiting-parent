package shiver.me.timbers.waiting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
