package shiver.me.timbers.waiting;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static shiver.me.timbers.waiting.Excludes.addExcludes;
import static shiver.me.timbers.waiting.Includes.addIncludes;

class ManualWaitingIncludeAndExclude {

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

    public <T> T includeAndExcludeMethod(final Callable<T> callable) {
        final Options options = new Options().withTimeout(duration, unit);
        addIncludes(options, includes);
        addExcludes(options, excludes);
        return new Waiter(options).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}