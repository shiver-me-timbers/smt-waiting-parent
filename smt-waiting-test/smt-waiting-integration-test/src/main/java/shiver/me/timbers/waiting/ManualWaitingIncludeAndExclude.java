package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static shiver.me.timbers.waiting.Excludes.addExcludes;
import static shiver.me.timbers.waiting.Includes.addIncludes;

public class ManualWaitingIncludeAndExclude<O extends Options, W extends Waiter> extends WaiterCreater<O, W> {

    private final long duration;
    private final TimeUnit unit;
    private final List<Throwable> includes;
    private final List<Throwable> excludes;

    public ManualWaitingIncludeAndExclude(
        long duration,
        TimeUnit unit,
        List<Throwable> includes,
        List<Throwable> excludes
    ) {
        this.duration = duration;
        this.unit = unit;
        this.includes = includes;
        this.excludes = excludes;
    }

    @SuppressWarnings("unchecked")
    public <T> T includeAndExcludeMethod(final Callable<T> callable) {
        return waiter((O) addExcludes(addIncludes(options(), includes), excludes).withTimeout(duration, unit))
            .wait(new Until<T>() {
                @Override
                public T success() throws Throwable {
                    return callable.call();
                }
            });
    }
}
