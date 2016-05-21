package shiver.me.timbers.waiting.factory;

import shiver.me.timbers.waiting.execution.WaitingExclude;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.random.RandomExceptions.SOME_THROWABLES;
import static shiver.me.timbers.waiting.util.Classes.toClasses;

public class WaitingExcludesWithIncludesFactory {

    private final LookupFactory<WaitingExclude> lookupFactory;

    public WaitingExcludesWithIncludesFactory(
        WaitingExclude cannotIgnoreExcludeIncludeWaitingExclude,
        WaitingExclude excludePrecedenceWaitingExclude
    ) {
        this(new MapLookupFactory<WaitingExclude>());
        add(
            cannotIgnoreExcludeIncludeWaitingExclude,
            500L, MILLISECONDS, asList(SOME_THROWABLES), asList(SOME_OTHER_THROWABLES)
        );
        add(
            excludePrecedenceWaitingExclude,
            500L, MILLISECONDS, singletonList(SOME_THROWABLES[0]), singletonList(SOME_THROWABLES[0])
        );
    }

    public WaitingExcludesWithIncludesFactory(LookupFactory<WaitingExclude> lookupFactory) {
        this.lookupFactory = lookupFactory;
    }

    public WaitingExclude create(long duration, TimeUnit unit, List<Throwable> excludes, List<Throwable> includes) {
        return lookupFactory.find(duration, unit, toClasses(excludes), toClasses(includes));
    }

    public void add(
        WaitingExclude waitingExclude,
        Long duration,
        TimeUnit unit,
        List<Throwable> excludes,
        List<Throwable> includes
    ) {
        lookupFactory.add(waitingExclude, duration, unit, toClasses(excludes), toClasses(includes));
    }
}
