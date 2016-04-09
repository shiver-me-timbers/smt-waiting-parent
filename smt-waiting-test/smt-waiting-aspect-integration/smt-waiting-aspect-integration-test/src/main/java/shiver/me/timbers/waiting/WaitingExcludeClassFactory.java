package shiver.me.timbers.waiting;

public class WaitingExcludeClassFactory extends WaitingExcludeFactory {

    public WaitingExcludeClassFactory() {
        this(new CannotIgnoreWaitingExcludeClass(), new CanIgnoreWaitingExcludeClass());
    }

    public WaitingExcludeClassFactory(
        CannotIgnoreWaitingExcludeClass cannotIgnoreWaitingExcludeClass,
        CanIgnoreWaitingExcludeClass canIgnoreWaitingExcludeClass
    ) {
        super(cannotIgnoreWaitingExcludeClass, canIgnoreWaitingExcludeClass);
    }
}
