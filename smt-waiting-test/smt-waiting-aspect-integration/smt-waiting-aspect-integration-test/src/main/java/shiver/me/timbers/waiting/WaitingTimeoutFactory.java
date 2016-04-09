package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WaitingTimeoutFactory {

    private final LookupFactory<WaitingTimeout> lookupFactory;

    public WaitingTimeoutFactory(
        WaitingTimeout canChangeWaitingTimeoutClass,
        WaitingTimeout canWaitUntilExceptionWaitingTimeoutClass,
        WaitingTimeout shortWaitingTimeoutClass
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
