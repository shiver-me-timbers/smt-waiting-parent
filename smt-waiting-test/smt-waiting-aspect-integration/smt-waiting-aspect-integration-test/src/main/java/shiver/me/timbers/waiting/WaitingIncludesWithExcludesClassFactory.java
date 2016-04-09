package shiver.me.timbers.waiting;

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
