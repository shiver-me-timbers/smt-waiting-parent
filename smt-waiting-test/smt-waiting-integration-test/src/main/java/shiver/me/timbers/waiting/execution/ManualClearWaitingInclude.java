package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.Until;
import shiver.me.timbers.waiting.Waiter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ManualClearWaitingInclude<O extends Options, W extends Waiter> extends WaiterCreator<O, W>
    implements WaitingInclude {

    private final long duration;
    private final TimeUnit unit;
    private final boolean clearInclude;
    private final Throwable include;

    public ManualClearWaitingInclude(long duration, TimeUnit unit, boolean clearInclude, Throwable include) {
        this.duration = duration;
        this.unit = unit;
        this.clearInclude = clearInclude;
        this.include = include;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T includeMethod(final Callable<T> callable) {
        return waiter((O) options().withTimeout(duration, unit).clearIncludes(clearInclude).includes(include.getClass()))
            .wait(new Until<T>() {
                @Override
                public T success() throws Throwable {
                    return callable.call();
                }
            });
    }
}
