package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingInclude;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_THROWABLES;
import static shiver.me.timbers.waiting.util.Classes.toClasses;

public class WaitingIncludeFactory {

    private final LookupFactory<WaitingInclude> lookupFactory;

    public WaitingIncludeFactory(
        WaitingInclude canIgnoreWaitingInclude,
        WaitingInclude canIgnoreAllWaitingInclude,
        WaitingInclude canAddExtraWaitingInclude
    ) {
        this(new MapLookupFactory<WaitingInclude>());
        add(canIgnoreWaitingInclude, 500L, MILLISECONDS, SOME_THROWABLES);
        add(canIgnoreAllWaitingInclude, 500L, MILLISECONDS);
        add(canAddExtraWaitingInclude, 500L, MILLISECONDS, SOME_OTHER_THROWABLES[0]);
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
