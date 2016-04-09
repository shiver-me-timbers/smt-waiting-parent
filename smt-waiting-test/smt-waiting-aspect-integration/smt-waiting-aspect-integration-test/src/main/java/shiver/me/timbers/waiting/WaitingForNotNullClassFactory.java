package shiver.me.timbers.waiting;

public class WaitingForNotNullClassFactory extends WaitingForNotNullFactory {

    public WaitingForNotNullClassFactory() {
        this(new CanWaitUntilWaitingForNotNullClass(), new CanWaitUntilTimeoutWaitingForNotNullClass());
    }

    public WaitingForNotNullClassFactory(
        CanWaitUntilWaitingForNotNullClass canWaitUntilWaitingForNotNullClass,
        CanWaitUntilTimeoutWaitingForNotNullClass canWaitUntilTimeoutWaitingForNotNullClass
    ) {
        super(canWaitUntilWaitingForNotNullClass, canWaitUntilTimeoutWaitingForNotNullClass);
    }
}
