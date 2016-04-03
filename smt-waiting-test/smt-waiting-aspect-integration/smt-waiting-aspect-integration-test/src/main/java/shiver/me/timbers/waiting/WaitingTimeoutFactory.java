package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class WaitingTimeoutFactory {

    private final LookupFactory<WaitingTimeout> lookupFactory;

    public WaitingTimeoutFactory() {
        this(new MapLookupFactory<WaitingTimeout>());
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
