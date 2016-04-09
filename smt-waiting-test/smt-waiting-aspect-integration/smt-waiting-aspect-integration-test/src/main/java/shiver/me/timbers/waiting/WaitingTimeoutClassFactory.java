package shiver.me.timbers.waiting;

public class WaitingTimeoutClassFactory extends WaitingTimeoutFactory {

    public WaitingTimeoutClassFactory() {
        this(
            new CanChangeWaitingTimeoutClass(),
            new CanWaitUntilExceptionWaitingTimeoutClass(),
            new ShortWaitingTimeoutClass()
        );
    }

    public WaitingTimeoutClassFactory(
        CanChangeWaitingTimeoutClass canChangeWaitingTimeoutClass,
        CanWaitUntilExceptionWaitingTimeoutClass canWaitUntilExceptionWaitingTimeoutClass,
        ShortWaitingTimeoutClass shortWaitingTimeoutClass
    ) {
        super(canChangeWaitingTimeoutClass, canWaitUntilExceptionWaitingTimeoutClass, shortWaitingTimeoutClass);
    }
}
