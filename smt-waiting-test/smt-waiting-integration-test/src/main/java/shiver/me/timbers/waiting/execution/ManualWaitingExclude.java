package shiver.me.timbers.waiting.execution;

import shiver.me.timbers.waiting.Options;
import shiver.me.timbers.waiting.Until;
import shiver.me.timbers.waiting.Waiter;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static shiver.me.timbers.waiting.util.Excludes.addExcludes;

public class ManualWaitingExclude<O extends Options, W extends Waiter> extends WaiterCreator<O, W>
    implements WaitingExclude {

    private final long duration;
    private final TimeUnit unit;
    private final Throwable[] excludes;

    public ManualWaitingExclude(long duration, TimeUnit unit, Throwable... excludes) {
        this.duration = duration;
        this.unit = unit;
        this.excludes = excludes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T excludeMethod(final Callable<T> callable) {
        return waiter((O) addExcludes(options(), excludes).withTimeout(duration, unit)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
