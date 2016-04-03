package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SpringManualWaitingIncludeWithExclude extends ManualWaitingIncludeAndExclude<SpringOptions, SpringWaiter>
    implements WaitingInclude {

    public SpringManualWaitingIncludeWithExclude(
        long duration,
        TimeUnit unit,
        List<Throwable> includes,
        List<Throwable> excludes
    ) {
        super(duration, unit, includes, excludes);
    }

    @Override
    public SpringOptions options() {
        return new SpringOptions();
    }

    @Override
    public SpringWaiter waiter(SpringOptions options) {
        return new SpringWaiter(options);
    }

    @Override
    public <T> T includeMethod(final Callable<T> callable) {
        return includeAndExcludeMethod(callable);
    }
}
