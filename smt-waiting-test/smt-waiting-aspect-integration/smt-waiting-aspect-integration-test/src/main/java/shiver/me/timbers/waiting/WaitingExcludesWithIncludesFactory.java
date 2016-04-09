package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.Classes.toClasses;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;

public class WaitingExcludesWithIncludesFactory {

    private final LookupFactory<WaitingExclude> lookupFactory;

    public WaitingExcludesWithIncludesFactory(
        WaitingExclude cannotIgnoreExcludeIncludeWaitingExcludeClass,
        WaitingExclude excludePrecedenceWaitingExcludeClass
    ) {
        this(new MapLookupFactory<WaitingExclude>());
        add(
            cannotIgnoreExcludeIncludeWaitingExcludeClass,
            500L, MILLISECONDS, asList(SOME_THROWABLES), asList(SOME_OTHER_THROWABLES)
        );
        add(
            excludePrecedenceWaitingExcludeClass,
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
