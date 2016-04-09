package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WaitingForNotNullFactory {

    private final LookupFactory<WaitingForNotNull> lookupFactory;

    public WaitingForNotNullFactory(
        WaitingForNotNull canWaitUntilWaitingForNotNullClass,
        WaitingForNotNull canWaitUntilTimeoutWaitingForNotNullClass
    ) {
        this(new MapLookupFactory<WaitingForNotNull>());
        add(canWaitUntilWaitingForNotNullClass, 500L, MILLISECONDS, true);
        add(canWaitUntilTimeoutWaitingForNotNullClass, 200L, MILLISECONDS, true);
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
