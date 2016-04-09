package shiver.me.timbers.waiting;

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
