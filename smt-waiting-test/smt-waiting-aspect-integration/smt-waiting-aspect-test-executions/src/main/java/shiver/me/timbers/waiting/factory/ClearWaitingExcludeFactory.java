package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingExclude;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_OTHER_THROWABLES;

public class ClearWaitingExcludeFactory {

    private final LookupFactory<WaitingExclude> lookupFactory;

    public ClearWaitingExcludeFactory(WaitingExclude clearAndAddExcludeWaitingFor) {
        this(new MapLookupFactory<WaitingExclude>());
        add(clearAndAddExcludeWaitingFor, 500L, MILLISECONDS, true, SOME_OTHER_THROWABLES[0]);
    }

    public ClearWaitingExcludeFactory(LookupFactory<WaitingExclude> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingExclude create(long duration, TimeUnit unit, boolean clearExcludes, Throwable exclude) {
        return lookupFactory.find(duration, unit, clearExcludes, exclude.getClass());
    }

    public void add(WaitingExclude waitingExclude, Long duration, TimeUnit unit, Boolean clearExcludes, Throwable include) {
        lookupFactory.add(waitingExclude, duration, unit, clearExcludes, include.getClass());
    }
}
