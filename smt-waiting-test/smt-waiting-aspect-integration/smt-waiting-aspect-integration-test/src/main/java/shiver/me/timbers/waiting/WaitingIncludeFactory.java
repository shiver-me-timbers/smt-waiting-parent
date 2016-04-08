package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.Classes.toClasses;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;

public class WaitingIncludeFactory {

    private final LookupFactory<WaitingInclude> lookupFactory;

    public WaitingIncludeFactory() {
        this(new CanIgnoreWaitingIncludeClass(), new CanIgnoreAllWaitingIncludeClass());
    }

    public WaitingIncludeFactory(
        CanIgnoreWaitingIncludeClass canIgnoreWaitingIncludeClass,
        CanIgnoreAllWaitingIncludeClass canIgnoreAllWaitingIncludeClass
    ) {
        this(new MapLookupFactory<WaitingInclude>());
        add(canIgnoreWaitingIncludeClass, 500L, MILLISECONDS, SOME_THROWABLES);
        add(canIgnoreAllWaitingIncludeClass, 500L, MILLISECONDS);
    }

    public WaitingIncludeFactory(LookupFactory<WaitingInclude> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingInclude create(long duration, TimeUnit unit, Throwable... includes) {
        return lookupFactory.find(duration, unit, toClasses(asList(includes)));
    }

    public void add(WaitingInclude waitingInclude, Long duration, TimeUnit unit, Throwable... includes) {
        lookupFactory.add(waitingInclude, duration, unit, toClasses(asList(includes)));
    }
}
