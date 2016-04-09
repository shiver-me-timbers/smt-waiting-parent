package shiver.me.timbers.waiting;

public class WaitingIncludeClassFactory extends WaitingIncludeFactory {

    public WaitingIncludeClassFactory() {
        this(new CanIgnoreWaitingIncludeClass(), new CanIgnoreAllWaitingIncludeClass());
    }

    public WaitingIncludeClassFactory(
        CanIgnoreWaitingIncludeClass canIgnoreWaitingIncludeClass,
        CanIgnoreAllWaitingIncludeClass canIgnoreAllWaitingIncludeClass
    ) {
        super(canIgnoreWaitingIncludeClass, canIgnoreAllWaitingIncludeClass);
    }
}
