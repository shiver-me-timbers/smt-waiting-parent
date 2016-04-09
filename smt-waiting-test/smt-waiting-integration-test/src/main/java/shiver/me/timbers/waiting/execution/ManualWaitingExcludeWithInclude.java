package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.Waiter;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ManualWaitingExcludeWithInclude<O extends Options, W extends Waiter>
    extends ManualWaitingIncludeAndExclude<O, W> implements WaitingExclude {

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
