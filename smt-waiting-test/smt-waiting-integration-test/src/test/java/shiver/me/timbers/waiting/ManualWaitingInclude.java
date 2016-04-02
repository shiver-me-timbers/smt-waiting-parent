package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static shiver.me.timbers.waiting.Includes.addIncludes;

class ManualWaitingInclude implements WaitingInclude {

    private final long duration;
    private final TimeUnit unit;
    private final Throwable[] includes;

    public ManualWaitingInclude(long duration, TimeUnit unit, Throwable... includes) {
        this.duration = duration;
        this.unit = unit;
        this.includes = includes;
    }

    @Override
    public <T> T includeMethod(final Callable<T> callable) {
        final Options options = new Options().withTimeout(duration, unit);
        addIncludes(options, includes);
        return new Waiter(options).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}