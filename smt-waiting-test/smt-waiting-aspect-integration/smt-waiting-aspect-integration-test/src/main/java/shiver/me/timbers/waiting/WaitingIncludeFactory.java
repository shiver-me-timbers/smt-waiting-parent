package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static shiver.me.timbers.waiting.Classes.toClasses;

public class WaitingIncludeFactory {

    private final LookupFactory<WaitingInclude> lookupFactory;

    public WaitingIncludeFactory() {
        this(new MapLookupFactory<WaitingInclude>());
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
