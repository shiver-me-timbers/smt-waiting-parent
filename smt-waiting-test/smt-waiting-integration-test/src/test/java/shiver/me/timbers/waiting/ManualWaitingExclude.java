package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static shiver.me.timbers.waiting.Excludes.addExcludes;

class ManualWaitingExclude implements WaitingExclude {

    private final long duration;
    private final TimeUnit unit;
    private final Throwable[] excludes;

    public ManualWaitingExclude(long duration, TimeUnit unit, Throwable... excludes) {
        this.duration = duration;
        this.unit = unit;
        this.excludes = excludes;
    }

    @Override
    public <T> T excludeMethod(final Callable<T> callable) {
        return new Waiter(addExcludes(new Options(), excludes).withTimeout(duration, unit)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
