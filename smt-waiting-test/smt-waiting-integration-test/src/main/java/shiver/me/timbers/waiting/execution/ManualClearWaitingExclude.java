package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.Until;
import shiver.me.timbers.waiting.Waiter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ManualClearWaitingExclude<O extends Options, W extends Waiter> extends WaiterCreator<O, W>
    implements WaitingExclude {

    private final long duration;
    private final TimeUnit unit;
    private final boolean clearInclude;
    private final Throwable exclude;

    public ManualClearWaitingExclude(long duration, TimeUnit unit, boolean clearInclude, Throwable exclude) {
        this.duration = duration;
        this.unit = unit;
        this.clearInclude = clearInclude;
        this.exclude = exclude;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T excludeMethod(final Callable<T> callable) {
        return waiter((O) options().withTimeout(duration, unit).clearExcludes(clearInclude).excludes(exclude.getClass()))
            .wait(new Until<T>() {
                @Override
                public T success() throws Throwable {
                    return callable.call();
                }
            });
    }
}
