package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingInclude;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_THROWABLES;

public class ClearWaitingIncludeFactory {

    private final LookupFactory<WaitingInclude> lookupFactory;

    public ClearWaitingIncludeFactory(WaitingInclude clearAndAddIncludeWaitingFor) {
        this(new MapLookupFactory<WaitingInclude>());
        add(clearAndAddIncludeWaitingFor, 500L, MILLISECONDS, true, SOME_THROWABLES[0]);
    }

    public ClearWaitingIncludeFactory(LookupFactory<WaitingInclude> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingInclude create(long duration, TimeUnit unit, boolean clearInclude, Throwable include) {
        return lookupFactory.find(duration, unit, clearInclude, include.getClass());
    }

    public void add(WaitingInclude waitingFor, Long duration, TimeUnit unit, Boolean clearInclude, Throwable include) {
        lookupFactory.add(waitingFor, duration, unit, clearInclude, include.getClass());
    }
}
