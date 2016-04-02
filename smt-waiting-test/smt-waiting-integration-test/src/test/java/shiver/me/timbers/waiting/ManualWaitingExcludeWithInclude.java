package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class ManualWaitingExcludeWithInclude extends ManualWaitingIncludeAndExclude implements WaitingExclude {

    public ManualWaitingExcludeWithInclude(
        long duration,
        TimeUnit unit,
        List<Throwable> excludes,
        List<Throwable> includes
    ) {
        super(duration, unit, includes, excludes);
    }

    @Override
    public <T> T excludeMethod(Callable<T> callable) {
        return includeAndExcludeMethod(callable);
    }
}
