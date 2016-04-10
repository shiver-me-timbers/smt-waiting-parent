package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingForTrue;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class WaitingForTrueFactory {

    private final LookupFactory<WaitingForTrue> lookupFactory;

    public WaitingForTrueFactory(
        WaitingForTrue canWaitUntilWaitingForTrue,
        WaitingForTrue canWaitUntilTimeoutWaitingForTrue,
        WaitingForTrue canAddExtraWaitingForTrue
    ) {
        this(new MapLookupFactory<WaitingForTrue>());
        add(canWaitUntilWaitingForTrue, 500L, MILLISECONDS, true);
        add(canWaitUntilTimeoutWaitingForTrue, 200L, MILLISECONDS, true);
        add(canAddExtraWaitingForTrue, 500L, MILLISECONDS, false);
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
