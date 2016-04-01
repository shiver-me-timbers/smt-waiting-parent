package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class ManualWaitingIncludeWithExclude extends ManualWaitingIncludeAndExclude implements WaitingInclude {

    public ManualWaitingIncludeWithExclude(
        long duration,
        TimeUnit unit,
        List<Throwable> includes,
        List<Throwable> excludes
    ) {
        super(duration, unit, includes, excludes);
    }

    @Override
    public <T> T includeMethod(final Callable<T> callable) {
        return includeAndExcludeMethod(callable);
    }
}
