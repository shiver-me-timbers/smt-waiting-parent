package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.Waiter;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ManualWaitingIncludeWithExclude<O extends Options, W extends Waiter>
    extends ManualWaitingIncludeAndExclude<O, W> implements WaitingInclude {

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
