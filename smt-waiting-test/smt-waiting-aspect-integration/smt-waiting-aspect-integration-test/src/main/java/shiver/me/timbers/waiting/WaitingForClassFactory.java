package shiver.me.timbers.waiting;

public class WaitingForClassFactory extends WaitingForFactory {

    public WaitingForClassFactory() {
        this(new CanWaitUntilValidWaitingForClass(), new CanWaitUntilTimeoutWaitingForClass());
    }

    public WaitingForClassFactory(
        CanWaitUntilValidWaitingForClass canWaitUntilValidWaitingForClass,
        CanWaitUntilTimeoutWaitingForClass canWaitUntilTimeoutWaitingForClass
    ) {
        super(canWaitUntilValidWaitingForClass, canWaitUntilTimeoutWaitingForClass);
    }
}
