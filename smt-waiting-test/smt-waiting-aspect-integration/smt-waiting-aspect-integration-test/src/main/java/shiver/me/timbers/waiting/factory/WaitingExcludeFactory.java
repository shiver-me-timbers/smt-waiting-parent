package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingExclude;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_THROWABLES;
import static shiver.me.timbers.waiting.util.Classes.toClasses;

public class WaitingExcludeFactory {

    private final LookupFactory<WaitingExclude> lookupFactory;

    public WaitingExcludeFactory(
        WaitingExclude cannotIgnoreWaitingExcludeClass,
        WaitingExclude canIgnoreWaitingExcludeClass
    ) {
        this(new MapLookupFactory<WaitingExclude>());
        add(
            cannotIgnoreWaitingExcludeClass,
            500L, MILLISECONDS, SOME_THROWABLES[0], SOME_OTHER_THROWABLES[0], SOME_THROWABLES[1]
        );
        add(
            canIgnoreWaitingExcludeClass,
            500L, MILLISECONDS, SOME_OTHER_THROWABLES
        );
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
