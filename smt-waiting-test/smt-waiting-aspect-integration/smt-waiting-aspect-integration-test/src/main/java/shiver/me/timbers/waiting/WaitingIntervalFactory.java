package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WaitingIntervalFactory {

    private final LookupFactory<WaitingInterval> lookupFactory;

    public WaitingIntervalFactory(WaitingInterval canChangeWaitingIntervalClass) {
        this(new MapLookupFactory<WaitingInterval>());
        add(canChangeWaitingIntervalClass, 200L, MILLISECONDS);
    }

    public WaitingIntervalFactory(LookupFactory<WaitingInterval> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingInterval create(long duration, TimeUnit unit) {
        return lookupFactory.find(duration, unit);
    }

    public void add(WaitingInterval waitingInterval, Long duration, TimeUnit unit) {
        lookupFactory.add(waitingInterval, duration, unit);
    }
}
