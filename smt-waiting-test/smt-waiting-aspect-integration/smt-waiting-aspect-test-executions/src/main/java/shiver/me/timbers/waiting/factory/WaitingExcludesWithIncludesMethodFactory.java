package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CannotIgnoreExcludeIncludeWaitingExcludeMethod;
import shiver.me.timbers.waiting.execution.ExcludePrecedenceWaitingExcludeMethod;

public class WaitingExcludesWithIncludesMethodFactory extends WaitingExcludesWithIncludesFactory {

    public WaitingExcludesWithIncludesMethodFactory() {
        this(new CannotIgnoreExcludeIncludeWaitingExcludeMethod(), new ExcludePrecedenceWaitingExcludeMethod());
    }

    public WaitingExcludesWithIncludesMethodFactory(
        CannotIgnoreExcludeIncludeWaitingExcludeMethod cannotIgnoreExcludeIncludeWaitingExcludeMethod,
        ExcludePrecedenceWaitingExcludeMethod excludePrecedenceWaitingExcludeMethod
    ) {
        super(cannotIgnoreExcludeIncludeWaitingExcludeMethod, excludePrecedenceWaitingExcludeMethod);
    }
}
