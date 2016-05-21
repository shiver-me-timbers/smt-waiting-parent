package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.CanIgnoreIncludeExcludeWaitingIncludeClass;

public class WaitingIncludesWithExcludesClassFactory extends WaitingIncludesWithExcludesFactory {

    public WaitingIncludesWithExcludesClassFactory() {
        this(new CanIgnoreIncludeExcludeWaitingIncludeClass());
    }

    public WaitingIncludesWithExcludesClassFactory(
        CanIgnoreIncludeExcludeWaitingIncludeClass canIgnoreIncludeExcludeWaitingIncludeClass
    ) {
        super(canIgnoreIncludeExcludeWaitingIncludeClass);
    }
}
