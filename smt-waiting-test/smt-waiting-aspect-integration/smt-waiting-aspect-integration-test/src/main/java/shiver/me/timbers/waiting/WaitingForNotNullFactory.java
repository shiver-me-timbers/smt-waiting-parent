package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class WaitingForNotNullFactory {

    private final LookupFactory<WaitingForNotNull> lookupFactory;

    public WaitingForNotNullFactory() {
        this(new MapLookupFactory<WaitingForNotNull>());
    }

    public WaitingForNotNullFactory(LookupFactory<WaitingForNotNull> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingForNotNull create(long duration, TimeUnit unit, boolean isNotNull) {
        return lookupFactory.find(duration, unit, isNotNull);
    }

    public void add(WaitingForNotNull waitingForNotNull, Long duration, TimeUnit unit, boolean isNotNull) {
        lookupFactory.add(waitingForNotNull, duration, unit, isNotNull);
    }
}
