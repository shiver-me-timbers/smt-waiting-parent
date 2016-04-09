package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.Classes.toClasses;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;

public class WaitingIncludesWithExcludesFactory {

    private final LookupFactory<WaitingInclude> lookupFactory;

    public WaitingIncludesWithExcludesFactory(
        WaitingInclude canIgnoreIncludeExcludeWaitingIncludeClass
    ) {
        this(new MapLookupFactory<WaitingInclude>());
        add(
            canIgnoreIncludeExcludeWaitingIncludeClass,
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
