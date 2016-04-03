package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

public class WaitingForTrueFactory {

    private final LookupFactory<WaitingForTrue> lookupFactory;

    public WaitingForTrueFactory() {
        this(new MapLookupFactory<WaitingForTrue>());
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
