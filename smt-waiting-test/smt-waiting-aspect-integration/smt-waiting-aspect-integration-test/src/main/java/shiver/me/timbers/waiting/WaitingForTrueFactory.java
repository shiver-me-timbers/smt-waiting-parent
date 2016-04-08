package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WaitingForTrueFactory {

    private final LookupFactory<WaitingForTrue> lookupFactory;

    public WaitingForTrueFactory() {
        this(new CanWaitUntilWaitingForTrueClass(), new CanWaitUntilTimeoutWaitingForTrueClass());
    }

    public WaitingForTrueFactory(
        CanWaitUntilWaitingForTrueClass canWaitUntilWaitingForTrueClass,
        CanWaitUntilTimeoutWaitingForTrueClass canWaitUntilTimeoutWaitingForTrueClass
    ) {
        this(new MapLookupFactory<WaitingForTrue>());
        add(canWaitUntilWaitingForTrueClass, 500L, MILLISECONDS, true);
        add(canWaitUntilTimeoutWaitingForTrueClass, 200L, MILLISECONDS, true);
    }

    public WaitingForTrueFactory(LookupFactory<WaitingForTrue> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingForTrue create(long duration, TimeUnit unit, boolean isTrue) {
        return lookupFactory.find(duration, unit, isTrue);
    }

    public void add(WaitingForTrue waitingForTrue, Long duration, TimeUnit unit, boolean isTrue) {
        lookupFactory.add(waitingForTrue, duration, unit, isTrue);
    }
}
