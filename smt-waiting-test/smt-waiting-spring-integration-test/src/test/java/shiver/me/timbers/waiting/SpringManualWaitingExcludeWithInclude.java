package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SpringManualWaitingExcludeWithInclude extends ManualWaitingIncludeAndExclude<SpringOptions, SpringWaiter>
    implements WaitingExclude {

    public SpringManualWaitingExcludeWithInclude(
        long duration,
        TimeUnit unit,
        List<Throwable> excludes,
        List<Throwable> includes
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
    public <T> T excludeMethod(Callable<T> callable) {
        return includeAndExcludeMethod(callable);
    }
}
