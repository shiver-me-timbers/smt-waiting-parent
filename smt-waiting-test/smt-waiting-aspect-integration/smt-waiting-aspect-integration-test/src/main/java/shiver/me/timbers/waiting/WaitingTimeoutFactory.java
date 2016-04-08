package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WaitingTimeoutFactory {

    private final LookupFactory<WaitingTimeout> lookupFactory;

    public WaitingTimeoutFactory() {
        this(
            new CanChangeWaitingTimeoutClass(),
            new CanWaitUntilExceptionWaitingTimeoutClass(),
            new ShortWaitingTimeoutClass()
        );
    }

    public WaitingTimeoutFactory(
        CanChangeWaitingTimeoutClass canChangeWaitingTimeoutClass,
        CanWaitUntilExceptionWaitingTimeoutClass canWaitUntilExceptionWaitingTimeoutClass,
        ShortWaitingTimeoutClass shortWaitingTimeoutClass
    ) {
        this(new MapLookupFactory<WaitingTimeout>());
        add(canChangeWaitingTimeoutClass, 200L, MILLISECONDS);
        add(canWaitUntilExceptionWaitingTimeoutClass, 500L, MILLISECONDS);
        add(shortWaitingTimeoutClass, 10L, MILLISECONDS);
    }

    public WaitingTimeoutFactory(LookupFactory<WaitingTimeout> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingTimeout create(long duration, TimeUnit unit) {
        return lookupFactory.find(duration, unit);
    }

    public void add(WaitingTimeout waitingTimeout, Long duration, TimeUnit unit) {
        lookupFactory.add(waitingTimeout, duration, unit);
    }
}
