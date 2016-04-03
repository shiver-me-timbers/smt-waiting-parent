package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static shiver.me.timbers.waiting.Classes.toClasses;

public class WaitingExcludeFactory {

    private final LookupFactory<WaitingExclude> lookupFactory;

    public WaitingExcludeFactory() {
        this(new MapLookupFactory<WaitingExclude>());
    }

    public WaitingExcludeFactory(LookupFactory<WaitingExclude> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingExclude create(long duration, TimeUnit unit, Throwable... excludes) {
        return lookupFactory.find(duration, unit, toClasses(asList(excludes)));
    }

    public void add(WaitingExclude waitingExclude, Long duration, TimeUnit unit, Throwable... excludes) {
        lookupFactory.add(waitingExclude, duration, unit, toClasses(asList(excludes)));
    }
}
