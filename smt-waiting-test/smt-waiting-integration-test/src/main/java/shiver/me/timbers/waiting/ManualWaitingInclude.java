package shiver.me.timbers.waiting;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static shiver.me.timbers.waiting.Includes.addIncludes;

public class ManualWaitingInclude<O extends Options, W extends Waiter> extends WaiterCreator<O, W>
    implements WaitingInclude {

    private final long duration;
    private final TimeUnit unit;
    private final Throwable[] includes;

    public ManualWaitingInclude(long duration, TimeUnit unit, Throwable... includes) {
        this.duration = duration;
        this.unit = unit;
        this.includes = includes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T includeMethod(final Callable<T> callable) {
        return waiter((O) addIncludes(options(), includes).withTimeout(duration, unit)).wait(new Until<T>() {
            @Override
            public T success() throws Throwable {
                return callable.call();
            }
        });
    }
}
