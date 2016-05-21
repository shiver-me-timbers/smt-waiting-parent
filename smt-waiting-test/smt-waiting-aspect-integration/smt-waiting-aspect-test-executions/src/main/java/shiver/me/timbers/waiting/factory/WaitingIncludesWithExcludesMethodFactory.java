package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanIgnoreIncludeExcludeWaitingIncludeMethod;

public class WaitingIncludesWithExcludesMethodFactory extends WaitingIncludesWithExcludesFactory {

    public WaitingIncludesWithExcludesMethodFactory() {
        this(new CanIgnoreIncludeExcludeWaitingIncludeMethod());
    }

    public WaitingIncludesWithExcludesMethodFactory(
        CanIgnoreIncludeExcludeWaitingIncludeMethod canIgnoreIncludeExcludeWaitingIncludeMethod
    ) {
        super(canIgnoreIncludeExcludeWaitingIncludeMethod);
    }
}
