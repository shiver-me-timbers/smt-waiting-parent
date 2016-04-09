package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CannotIgnoreExcludeIncludeWaitingExcludeClass;
import shiver.me.timbers.waiting.execution.ExcludePrecedenceWaitingExcludeClass;

public class WaitingExcludesWithIncludesClassFactory extends WaitingExcludesWithIncludesFactory {

    public WaitingExcludesWithIncludesClassFactory() {
        this(new CannotIgnoreExcludeIncludeWaitingExcludeClass(), new ExcludePrecedenceWaitingExcludeClass());
    }

    public WaitingExcludesWithIncludesClassFactory(
        CannotIgnoreExcludeIncludeWaitingExcludeClass cannotIgnoreExcludeIncludeWaitingExcludeClass,
        ExcludePrecedenceWaitingExcludeClass excludePrecedenceWaitingExcludeClass
    ) {
        super(cannotIgnoreExcludeIncludeWaitingExcludeClass, excludePrecedenceWaitingExcludeClass);
    }
}
