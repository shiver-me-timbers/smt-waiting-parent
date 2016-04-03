package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static shiver.me.timbers.waiting.Classes.toClasses;

public class WaitingIncludesWithExcludesFactory {

    private final LookupFactory<WaitingInclude> lookupFactory;

    public WaitingIncludesWithExcludesFactory() {
        this(new MapLookupFactory<WaitingInclude>());
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
