package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingTimeout;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WaitingTimeoutFactory {

    private final LookupFactory<WaitingTimeout> lookupFactory;

    public WaitingTimeoutFactory(
        WaitingTimeout canChangeWaitingTimeout,
        WaitingTimeout canWaitUntilExceptionWaitingTimeout,
        WaitingTimeout shortWaitingTimeout
    ) {
        this(new MapLookupFactory<WaitingTimeout>());
        add(canChangeWaitingTimeout, 200L, MILLISECONDS);
        add(canWaitUntilExceptionWaitingTimeout, 500L, MILLISECONDS);
        add(shortWaitingTimeout, 10L, MILLISECONDS);
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
