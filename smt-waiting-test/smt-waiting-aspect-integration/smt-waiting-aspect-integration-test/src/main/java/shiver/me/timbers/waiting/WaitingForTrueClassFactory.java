package shiver.me.timbers.waiting;

public class WaitingForTrueClassFactory extends WaitingForTrueFactory {

    public WaitingForTrueClassFactory() {
        this(new CanWaitUntilWaitingForTrueClass(), new CanWaitUntilTimeoutWaitingForTrueClass());
    }

    public WaitingForTrueClassFactory(
        CanWaitUntilWaitingForTrueClass canWaitUntilWaitingForTrueClass,
        CanWaitUntilTimeoutWaitingForTrueClass canWaitUntilTimeoutWaitingForTrueClass
    ) {
        super(canWaitUntilWaitingForTrueClass, canWaitUntilTimeoutWaitingForTrueClass);
    }
}
