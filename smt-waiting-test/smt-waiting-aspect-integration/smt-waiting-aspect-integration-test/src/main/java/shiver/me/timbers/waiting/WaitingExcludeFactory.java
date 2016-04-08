package shiver.me.timbers.waiting;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static shiver.me.timbers.waiting.Classes.toClasses;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_OTHER_THROWABLES;
import static shiver.me.timbers.waiting.RandomExceptions.SOME_THROWABLES;

public class WaitingExcludeFactory {

    private final LookupFactory<WaitingExclude> lookupFactory;

    public WaitingExcludeFactory() {
        this(new CannotIgnoreWaitingExcludeClass(), new CanIgnoreWaitingExcludeClass());
    }

    public WaitingExcludeFactory(
        CannotIgnoreWaitingExcludeClass cannotIgnoreWaitingExcludeClass,
        CanIgnoreWaitingExcludeClass canIgnoreWaitingExcludeClass
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
