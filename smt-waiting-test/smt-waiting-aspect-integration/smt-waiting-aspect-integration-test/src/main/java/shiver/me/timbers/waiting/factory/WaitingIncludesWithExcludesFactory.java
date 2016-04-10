package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingInclude;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_THROWABLES;
import static shiver.me.timbers.waiting.util.Classes.toClasses;

public class WaitingIncludesWithExcludesFactory {

    private final LookupFactory<WaitingInclude> lookupFactory;

    public WaitingIncludesWithExcludesFactory(WaitingInclude canIgnoreIncludeExcludeWaitingInclude) {
        this(new MapLookupFactory<WaitingInclude>());
        add(
            canIgnoreIncludeExcludeWaitingInclude,
            500L, MILLISECONDS, asList(SOME_THROWABLES), asList(SOME_OTHER_THROWABLES)
        );
    }

    public WaitingIncludesWithExcludesFactory(LookupFactory<WaitingInclude> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingInclude create(long duration, TimeUnit unit, List<Throwable> includes, List<Throwable> excludes) {
        return lookupFactory.find(duration, unit, toClasses(includes), toClasses(excludes));
    }

    public void add(
        WaitingInclude waitingInclude,
        Long duration,
        TimeUnit unit,
        List<Throwable> includes,
        List<Throwable> excludes
    ) {
        lookupFactory.add(waitingInclude, duration, unit, toClasses(includes), toClasses(excludes));
    }
}
