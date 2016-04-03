package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static shiver.me.timbers.waiting.Classes.toClasses;

public class WaitingExcludesWithIncludesFactory {

    private final LookupFactory<WaitingExclude> lookupFactory;

    public WaitingExcludesWithIncludesFactory() {
        this(new MapLookupFactory<WaitingExclude>());
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
